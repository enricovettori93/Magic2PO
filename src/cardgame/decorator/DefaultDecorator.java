
package cardgame.decorator;

import cardgame.Creature;
import cardgame.Effect;
import cardgame.Player;
import cardgame.creaturestrategy.CreatureInflictDamageStrategy;
import java.util.List;

public class DefaultDecorator extends AbstractDecorator{

    public DefaultDecorator(Object l,Creature c) {
        super(l);
        setCreature(c);
    }

    @Override
    public int getPower() {
        return creature.getPower();
    }

    @Override
    public int getToughness() {
        return creature.getToughness();  
    }

    @Override
    public List<Effect> effects() {
        return creature.effects();
    }

    @Override
    public List<Effect> avaliableEffects() {
        return creature.avaliableEffects();
    }

    @Override
    public String name() {
        return "DefaultDecorator:"+creature.name();
    }

    @Override
    public int getPowerDecorated() {
        return getPower();
    }

    @Override
    public int getToughnessDecorated() {
        return getToughness();
    }

    @Override
    public int getDamageLeft() {
        return creature.getDamageLeft();
    }
    
    public void increaseDamageLeft(int val){
        resetDamage();
        creature.increaseDamageLeft(val);
    }

    @Override
    public void setOwner(Player owner) {
       creature.setOwner(owner);
    }

    @Override
    public Player getOwner() {
        return creature.getOwner();
    }

    @Override
    public void setCids(CreatureInflictDamageStrategy cids) {
        creature.setCids(cids);
    }

    @Override
    public void decreasePowerLeft(int val) {
        creature.decreasePowerLeft(val);
    }

    @Override
    public int getPowerLeft() {
        return creature.getPowerLeft();
    }
}
