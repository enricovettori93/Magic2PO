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
