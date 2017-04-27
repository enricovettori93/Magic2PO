/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.decorator.AbstractDecorator;
import java.util.List;

/**
 *
 * @author atorsell
 */
public interface Creature extends Permanent {
    
    boolean tap();
    boolean untap();
    boolean isTapped();
    void attack();
    void defend(Creature c);
    void inflictDamage(int dmg);
    void defaultInflictDamage(int dmg);
    void resetDamage();
    int getPower();
    int getToughness();
    int getPowerDecorated();
    int getToughnessDecorated();
    
    // returns all the effects associated to this creature
    List<Effect> effects();
    
    // returns only the effects that can be played currently
    // depending on state, e.g., tapped/untapped
    List<Effect> avaliableEffects();

    public void increaseDamageLeft(int val);
    
    public int getDamageLeft();
    
    public void addDecorator(AbstractDecorator d);
    
    public void removeDecorator(Object l);
}
