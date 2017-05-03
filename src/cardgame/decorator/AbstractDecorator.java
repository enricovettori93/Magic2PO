
package cardgame.decorator;

import cardgame.Creature;

public abstract class AbstractDecorator implements Creature{
    
    public Creature creature;
    public Object launcher;
    
    public AbstractDecorator(Object l){
        launcher=l;
    }
    
    //Metodi per la gestione dei decorator
    
    public void setCreature(Creature c){
        creature=c;
    }
    
    public Creature getCreature(){
        return creature;
    }
    
    //Metodi della creatura
    @Override
    public boolean tap() { 
        return creature.tap();
    }

    @Override
    public boolean untap() { 
        return creature.untap();                
    }

    @Override
    public boolean isTapped() { return creature.isTapped(); }

    @Override
    public void attack() {creature.attack();} // to do in assignment 2

    @Override
    public void defend(Creature c) { creature.defend(c);} // to do in assignment 2

    @Override
    public void inflictDamage(int dmg) { 
        creature.inflictDamage(dmg);
    }
    
    @Override
    public void resetDamage() { creature.resetDamage(); }

    @Override
    public void insert() {
        creature.insert();
    }

    @Override
    public void remove() {
        creature.remove();
    }

    @Override
    public String toString() {
        return creature.name() + " (Creature)";
    }

    @Override
    public void increaseDamageLeft(int val){
        creature.increaseDamageLeft(val);
    }
    @Override
    public void addDecorator(AbstractDecorator d){
        creature.addDecorator(d);
    };
    
    @Override
    public void removeDecorator(Object l){
        creature.removeDecorator(l);
    };
    
    @Override
    public void defaultInflictDamage(int dmg) {
        creature.defaultInflictDamage(dmg);
    }
    
    public void init(){
        creature.init();
    }
}
