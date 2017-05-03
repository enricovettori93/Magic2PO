
package cardgame;

import java.util.ArrayList;

public class OneTimeExecuteBattleStrategy implements ExecuteBattleStrategy{
    ExecuteBattleStrategy old, cur;
    public OneTimeExecuteBattleStrategy(ExecuteBattleStrategy old, ExecuteBattleStrategy cur){
        this.old=old;
        this.cur=cur;
    }
    @Override
    public void executeBattle(DefaultCombatPhase combat, ArrayList<DefaultCombatPhase.Scontro> scontri) {
        cur.executeBattle(combat, scontri);
        combat.setEbs(old);
    }
    
}
