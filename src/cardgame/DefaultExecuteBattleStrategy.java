
package cardgame;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class DefaultExecuteBattleStrategy implements ExecuteBattleStrategy {
    @Override
    public void executeBattle(DefaultCombatPhase combat,LinkedHashMap<Creature, ArrayList<Creature>> fight) {
        for (Map.Entry<Creature,ArrayList<Creature>> entry : fight.entrySet()) {
            if(entry.getValue().isEmpty()){
                /*attacco l'avversario*/
                System.out.println("["+entry.getKey().name()+"] attack the rival!");
                entry.getKey().attack();
            }
            else{
               int attack = entry.getKey().getPowerDecorated(); /*attacco della creatura*/
                for(Creature defender : entry.getValue()){
                    if(attack>0){
                        System.out.println("["+entry.getKey().name()+"] attack the defender "+defender.name());
                        defender.inflictDamage(attack); /*attacca creatura*/
                        attack-=defender.getToughnessDecorated();
                    }
                }
        }
    }
    
   }
}
