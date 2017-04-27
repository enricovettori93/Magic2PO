/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author atorsell
 */
public class DefaultCombatPhase implements Phase {
    Player currentPlayer;
    Player opponent;
    ExecuteBattleStrategy ebs = new DefaultExecuteBattleStrategy();
    ArrayList <Creature>idxAttaccanti=new ArrayList<Creature>();
    ArrayList <Creature>idxDifensore=new ArrayList<Creature>();
    ArrayList <Scontro> scontri= new ArrayList<Scontro>();
    private boolean playAvailableEffect(Player activePlayer, boolean isMain) {
        //collect and display available effects...
        ArrayList<Effect> availableEffects = new ArrayList<>();
        Scanner reader = CardGame.instance.getScanner();

        //...cards first
        System.out.println(activePlayer.name() + " select card/effect to play, 0 to pass");
        int i=0;
        for( Card c:activePlayer.getHand() ) {
            if ( isMain || c.isInstant() ) {
                availableEffects.add( c.getEffect(activePlayer) );
                System.out.println(Integer.toString(i+1)+") " + c );
                ++i;
            }
        }
        
        //...creature effects last
        for ( Creature c:activePlayer.getCreatures()) {
            for (Effect e:c.avaliableEffects()) {
                availableEffects.add(e);
                System.out.println(Integer.toString(i+1)+") " + c.name() + 
                    " ["+ e + "]" );
                ++i;
            }
        }
        
        //get user choice and play it
        int idx= reader.nextInt()-1;
        if (idx<0 || idx>=availableEffects.size()) return false;

        availableEffects.get(idx).play();
        return true;
    }
    
    private void declareAttacker(){
         Scanner reader = CardGame.instance.getScanner();
         int i=0, idx;
            //Dichiarazione degli attaccanti
            do{
                System.out.println(currentPlayer.name()+", select the creature that must attack, 0 to pass");
                for(Creature c:currentPlayer.getCreatures()){
                    if(!c.isTapped()){
                        if(!idxAttaccanti.contains(c))
                            System.out.println((i+1)+")"+c.name());
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
    }
    
    private void declareDefender(){
        Scanner reader = CardGame.instance.getScanner();
        int idx, i, j;
        //Dichiarazione dei difensori 
        if(!scontri.isEmpty()){
                i=0;
                j=0;
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
                        
                        for(j=0;j<scontri.size();j++)
                            System.out.println((j+1)+")"+scontri.get(j).getAttaccante().name());

                        //TODO: Fase Effetti Stack e istantanei sulla sungola creatura(Difensore)
                        playAvailableEffect(opponent,false);
            
                        //definizione dei difensori rispetto agli attaccanti
                        idxDif=reader.nextInt();
                        if((idxDif-1)<scontri.size()){
                            idxDifensore.add(opponent.getCreatures().get(idx-1));
                            scontri.get(idxDif-1).addDifensore(opponent.getCreatures().get(idx-1));
                           
                        }
                        else
                            System.out.println("Error: there isn't any attacker in that position");
                    }
                    j=0;
                    i=0;
                }while(idx!=0);
        }
    }
    
    private void executeBattle(){
        ebs.executeBattle(this, scontri);
    }
    
    @Override
    public void execute() {
        currentPlayer = CardGame.instance.getCurrentPlayer();
        opponent = CardGame.instance.getCurrentAdversary();
        System.out.println(currentPlayer.name() + ": combat phase");
       
        CardGame.instance.getTriggers().trigger(Triggers.COMBAT_FILTER);

        if(!currentPlayer.getCreatures().isEmpty()){ //nel programma del prof., le creature giocate sono contenute in "Player"
           declareAttacker();
           playAvailableEffect(opponent, false);
           CardGame.instance.getStack().resolve();
           playAvailableEffect(opponent, false);
           declareDefender();
           
           executeBattle();
           
        }
    }
    
    public void setEbs(ExecuteBattleStrategy ebs){
        this.ebs = ebs;
    }
    public ExecuteBattleStrategy getEbs(){
        return ebs;
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
