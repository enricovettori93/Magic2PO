/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.playerstrategy;

import cardgame.Player;

/**
 *
 * @author giaco
 */
public class PreventOneDamageStrategy implements PlayerInflictDamageStrategy{

    @Override
    public void inflictDamage(Player player, int pts) {
        if(pts>0)
            pts--;
        player.defaultInflictDamage(pts);
    }
    
}
