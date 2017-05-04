
package cardgame;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class DefaultExecuteBattleStrategy implements ExecuteBattleStrategy {
    @Override
    public void executeBattle(DefaultCombatPhase combat,LinkedHashMap<Creature, ArrayList<Creature>> fight) {
        for (Map.Entry<Creature,ArrayList<Creature>> entry : fight.entrySet()) {

                for(Creature defender : entry.getValue()){
                    if(entry.getKey().getPowerLeft()>0){
                        System.out.println("["+entry.getKey().name()+"] attack the defender "+defender.name());
                        defender.defend(entry.getKey()); /*attacca creatura*/
                    }
                }
                if(entry.getKey().getPowerLeft()>0){
                    System.out.println("["+entry.getKey().name()+"] attack the rival!");
                    entry.getKey().attack();
                }
        }
    }
    
}
