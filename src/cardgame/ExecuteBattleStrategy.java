
package cardgame;

import java.util.ArrayList;
import java.util.LinkedHashMap;

interface ExecuteBattleStrategy {
    public void executeBattle(DefaultCombatPhase combat, LinkedHashMap<Creature, ArrayList<Creature>> fight);
}
