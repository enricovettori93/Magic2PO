
package cardgame.creaturestrategy;

import cardgame.Creature;

public class CreatureDefaultInflictDamage implements CreatureInflictDamageStrategy{

    @Override
    public void inflictDamage(Creature c,int dmg) {
        c.defaultInflictDamage(dmg); //richiama l'infliggi danno di default
    }
    
}
