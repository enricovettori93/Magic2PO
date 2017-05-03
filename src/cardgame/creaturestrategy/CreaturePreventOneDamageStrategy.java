
package cardgame.creaturestrategy;

import cardgame.Creature;

public class CreaturePreventOneDamageStrategy implements CreatureInflictDamageStrategy{

    @Override
    public void inflictDamage(Creature c, int dmg) {
        if(dmg>0)
            dmg--;
        c.defaultInflictDamage(dmg);
    }
    
}
