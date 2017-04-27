/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.ArrayList;

/**
 *
 * @author giaco
 */
public class PreventAllDamageStrategy implements ExecuteBattleStrategy{

    @Override
    public void executeBattle(DefaultCombatPhase combat, ArrayList<DefaultCombatPhase.Scontro> scontri) {
        int i, j;
        for(i=0;i<scontri.size();i++){
            //scontri.get(i).getAttaccante().attack(scontri.get(i).getDifensore());
            if(scontri.get(i).nessunDif){
                //System.out.println(scontri.get(i).getAttaccante().name()+" Attacca l'avversario");
                scontri.get(i).getAttaccante().attack();
            }else{
                j = 0;
                int atk = scontri.get(i).getAttaccante().getPower();
                for(j=0;j<scontri.get(i).getDifensore().size()||scontri.get(i).getAttaccante().getPower()<=0;j++){
                    System.out.println("Mostro " + scontri.get(i).getAttaccante().name() + " sta attaccando "+scontri.get(i).getDifensore().get(j).name()+" [PREVENT ALL DAMAGED ACTIVATED]");
                    scontri.get(i).getDifensore().get(j).inflictDamage(0); //Danni al difensore
                    atk = atk - scontri.get(i).getDifensore().get(j).getToughnessDecorated();
                    }
                }
            }  
    }    
}
