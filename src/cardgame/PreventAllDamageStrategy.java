
package cardgame;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class PreventAllDamageStrategy implements ExecuteBattleStrategy{
    /**
     * EXECUTE BATTLE DI PREVENT ALL DAMAGE
     * Non viene attaccato nessuno, in quanto i danni vengono neutralizzati (è solo simulato)
     * @param combat
     * @param fight 
     */
    @Override
    public void executeBattle(DefaultCombatPhase combat,LinkedHashMap<Creature, ArrayList<Creature>> fight) {
        for (Map.Entry<Creature,ArrayList<Creature>> entry : fight.entrySet()) {
            if(!entry.getValue().isEmpty()){
                for(Creature defender : entry.getValue())
                        System.out.println("["+entry.getKey().name()+"] attack the defender "+defender.name()+" [PREVENT ALL DAMAGE ACTIVATED]");
            }
            else
                System.out.println("["+entry.getKey().name()+"] attack the rival! [PREVENT ALL DAMAGE ACTIVATED]");
        }
    }
}

