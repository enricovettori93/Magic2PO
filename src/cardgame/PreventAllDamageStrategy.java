
package cardgame;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class PreventAllDamageStrategy implements ExecuteBattleStrategy{
    @Override
    public void executeBattle(DefaultCombatPhase combat,LinkedHashMap<Creature, ArrayList<Creature>> fight) {
        for (Map.Entry<Creature,ArrayList<Creature>> entry : fight.entrySet()) {

                for(Creature defender : entry.getValue())
                        System.out.println("["+entry.getKey().name()+"] attack the defender "+defender.name()+" [PREVENT ALL DAMAGE ACTIVATED]");
                System.out.println("["+entry.getKey().name()+"] attack the rival! [PREVENT ALL DAMAGE ACTIVATED]");
        }
    }
}

