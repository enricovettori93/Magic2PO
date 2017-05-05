
package cardgame;

import cardgame.creaturestrategy.CreatureInflictDamageStrategy;
import cardgame.decorator.AbstractDecorator;
import java.util.List;

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
    public void decreasePowerLeft(int val);
    public int getDamageLeft();
    
    public int getPowerLeft();
    
    public void addDecorator(AbstractDecorator d);
    
    public void removeDecorator(Object l);
    
    public void setOwner(Player owner);

    public Player getOwner();

    public void setCids(CreatureInflictDamageStrategy cids);

    public void init();
    
    //Effetti particolari
    //defender -> true se la creatura è un difensore
    public boolean defender();
    //shroud -> true se la creatura non può essere un target
    public boolean shroud();
}
