/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.creaturestrategy;

import cardgame.Creature;

/**
 *
 * @author giaco
 */
public class CreatureDefaultInflictDamage implements CreatureInflictDamageStrategy{

    @Override
    public void inflictDamage(Creature c,int dmg) {
        c.defaultInflictDamage(dmg);
    }
    
}
