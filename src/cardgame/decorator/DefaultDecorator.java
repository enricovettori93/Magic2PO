/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.decorator;

import cardgame.Creature;
import cardgame.Effect;
import cardgame.Player;
import cardgame.creaturestrategy.CreatureInflictDamageStrategy;
import java.util.List;

/**
 *
 * @author Utente
 */
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Player getOwner() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCids(CreatureInflictDamageStrategy cids) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
