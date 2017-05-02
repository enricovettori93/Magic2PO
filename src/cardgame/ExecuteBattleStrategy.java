
package cardgame;

import java.util.ArrayList;

interface ExecuteBattleStrategy {
    public void executeBattle(DefaultCombatPhase combat, ArrayList<DefaultCombatPhase.Scontro> scontri);
}
