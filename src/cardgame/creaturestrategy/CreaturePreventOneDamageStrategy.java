
package cardgame.creaturestrategy;

import cardgame.Creature;

public class CreaturePreventOneDamageStrategy implements CreatureInflictDamageStrategy{
    /**
     * INFLICT DAMAGE -> previeni 1 danno
     * @param c
     * @param dmg 
     */
    @Override
    public void inflictDamage(Creature c, int dmg) {
        if(dmg>0) //se il danno è maggiore di 0, allora togli 1
            dmg--;
        c.defaultInflictDamage(dmg); //infliggi il danno
    }
    
}
