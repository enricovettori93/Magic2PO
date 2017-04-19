/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author atorsell
 */
public class DefaultCombatPhase implements Phase {
    @Override
    public void execute() {
        Player currentPlayer = CardGame.instance.getCurrentPlayer();
        Player opponent=CardGame.instance.getCurrentAdversary();
        Scanner reader = CardGame.instance.getScanner();
        System.out.println(currentPlayer.name() + ": combat phase");
        int idx;
        ArrayList <Creature>idxAttaccanti=new ArrayList<Creature>();
        ArrayList <Creature>idxDifensore=new ArrayList<Creature>();
        ArrayList <Scontro> scontri= new ArrayList<Scontro>();
        CardGame.instance.getTriggers().trigger(Triggers.COMBAT_FILTER);
        
        if(!currentPlayer.getCreatures().isEmpty()){ //nel programma del prof., le creature giocate sono contenute in "Player"
            int i=0;
            //Dichiarazione degli attaccanti
            do{
                System.out.println(currentPlayer.name()+", select the creature that must attack, 0 to pass");
                for(Creature c:currentPlayer.getCreatures()){
                    if(!c.isTapped()){
                        if(!idxAttaccanti.contains(c)){
                            System.out.println((i+1)+")"+c.name());
                           
                        }
                         i++;
                    }
                }
                
                idx=reader.nextInt();
                //inserisco gli indici degli attaccanti in una lista, in modo da far scegliere all'avversario quale 
                //minion vuole bloccare
                
                if(idx!=0 && (idx-1)<currentPlayer.getCreatures().size() && !idxAttaccanti.contains(currentPlayer.getCreatures().get(idx-1))){
                    scontri.add(new Scontro(currentPlayer.getCreatures().get(idx-1)));
                    idxAttaccanti.add(currentPlayer.getCreatures().get(idx-1));
                   }
                i=0;
            }while(idx!=0);
            //TODO: Fase Effetti Stack e istantanei
            
            //Dichiarazione dei difensori 
            if(!scontri.isEmpty()){
                i=0;
                int j=0;
                do{
                    System.out.println(opponent.name()+", select the creature that must defend, 0 to pass");
                    for(Creature c:opponent.getCreatures()){
                        if(!c.isTapped()){
                            if(!idxDifensore.contains(c))
                                System.out.println((i+1)+")"+c.name());
                            }
                        i++;
                    }

                    idx=reader.nextInt();
                    
                    if(idx!=0 && (idx-1)<opponent.getCreatures().size()){
                        int idxDif;
                        System.out.println(opponent.name()+", select the creature from which you have to defend yourself");
                        System.out.println("Opponent's attackers:");
                        
                        while(j<scontri.size()){
                            System.out.println((j+1)+")"+scontri.get(j).getAttaccante().name());
                            j++;
                        }
                        //TODO: Fase Effetti Stack e istantanei sulla sungola creatura(Difensore)
                        
                        //definizione dei difensori rispetto agli attaccanti
                        idxDif=reader.nextInt();
                        if((idxDif-1)<scontri.size()){
                            idxDifensore.add(opponent.getCreatures().get(idx-1));
                            scontri.get(idxDif-1).addDifensore(opponent.getCreatures().get(idx-1));
                           
                        }else{
                            System.out.println("Error: there is any attacker in that position");
                        }
                    }
                    j=0;
                    i=0;
                }while(idx!=0);
            }
            //Esecuzione scontri
            i=0;    
            while(i<scontri.size()){
                //scontri.get(i).getAttaccante().attack(scontri.get(i).getDifensore());
                if(scontri.get(i).nessunDif){
                    //System.out.println(scontri.get(i).getAttaccante().name()+" Attacca l'avversario");
                    scontri.get(i).getAttaccante().attack();
                }else{
                    for(int j=0;j<scontri.get(i).getDifensore().size();j++){
                        //System.out.println(scontri.get(i).getAttaccante().name()+" Attacca "+scontri.get(i).getDifensore().get(j).name());
                        scontri.get(i).getDifensore().get(j).inflictDamage(scontri.get(i).getAttaccante().getPower()); //Danni al difensore
                        
                    }
                }
                i++;
            }            
        }
    }
    
    //Classe usata per tener conto degli attaccanti e dei difensori
    public class Scontro{
        public Creature attaccante;
        public ArrayList <Creature> difensore;
        public boolean nessunDif;
        public Scontro(Creature att){
            difensore=new ArrayList<Creature>();
            this.attaccante=att;
            nessunDif=true;
        }
        
        public void addDifensore(Creature dif){
            this.difensore.add(dif);
            nessunDif=false;
        }
        
        public Creature getAttaccante(){
            return this.attaccante;
        }
        
        public ArrayList <Creature> getDifensore(){
            return difensore;
        }
        
        public boolean isEmpty(){
            return nessunDif;
        }
    }
}
