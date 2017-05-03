
package cardgame;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class OneTimeExecuteBattleStrategy implements ExecuteBattleStrategy{
    ExecuteBattleStrategy old, cur;
    public OneTimeExecuteBattleStrategy(ExecuteBattleStrategy old, ExecuteBattleStrategy cur){
        this.old=old;
        this.cur=cur;
    }
    @Override
   public void executeBattle(DefaultCombatPhase combat,LinkedHashMap<Creature, ArrayList<Creature>> fight) {
        cur.executeBattle(combat, fight);
        combat.setEbs(old);
    }
    
}
