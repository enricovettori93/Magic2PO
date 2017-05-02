
package cardgame;

import cardgame.creaturestrategy.CreatureDefaultInflictDamage;
import cardgame.creaturestrategy.CreatureInflictDamageStrategy;
import cardgame.decorator.AbstractDecorator;
import cardgame.decorator.DecoratorManagementSystem;

public abstract class AbstractCreature implements Creature {
    
    protected Player owner;
    protected boolean isTapped=false;
    public int damageLeft = getToughness();
    protected DecoratorManagementSystem dms=new DecoratorManagementSystem(this);
    protected CreatureInflictDamageStrategy cids = new CreatureDefaultInflictDamage();
    protected AbstractCreature(Player owner) { this.owner=owner; }
        
    @Override
        public boolean tap() { 
            if (isTapped) {
                System.out.println("creature " + name() + " already tapped");
                return false;
            }
            
            System.out.println("tapping creature " + name());
            isTapped=true; 
            return true; 
        }
        
    @Override
        public boolean untap() { 
            if (!isTapped) {
                System.out.println("creature " + name() + " not tapped");
                return false;
            }
            
            System.out.println("untapping creature " + name());
            isTapped=false; 
            return true; 
        }
        
    @Override
        public boolean isTapped() { return isTapped; }
    @Override
        public void attack() {
            CardGame.instance.getCurrentAdversary().inflictDamage(this.getPowerDecorated());
        }
    @Override
        public void defend(Creature c) {} // to do in assignment 2
    @Override
        public void inflictDamage(int dmg) { 
               cids.inflictDamage(this, dmg);
        }
     @Override
        public void defaultInflictDamage(int dmg){
            damageLeft -= dmg; 
            if (damageLeft<=0)
                owner.destroy(this);  
        }
    @Override
        public void resetDamage() { damageLeft = getToughnessDecorated(); }
    
    @Override
        public void insert() {
            CardGame.instance.getTriggers().trigger(Triggers.ENTER_CREATURE_FILTER,this);
        }
    
    @Override
        public void remove() {
            owner.getCreatures().remove(this);
            CardGame.instance.getTriggers().trigger(Triggers.EXIT_CREATURE_FILTER,this);
        }
    
    @Override
        public String toString() {
            return name() + " (Creature)";
        }
        
    public void addDecorator(AbstractDecorator d){
        dms.addDecorator(d);
    }
    
    public void removeDecorator(Object l){
        dms.removeDecorator(l);
    }
    
    @Override
    public int getPowerDecorated(){
        return dms.peek().getPowerDecorated();
    }
    
    @Override
    public int getToughnessDecorated(){
        return dms.peek().getToughnessDecorated();
    }
    
    public int getDamageLeft(){
        return damageLeft;
    }
    
    public void init(){
        damageLeft=getToughness();
    }
    
    public void increaseDamageLeft(int val){
        damageLeft+=val;
    }

    public String valueOfCreature(){
        return name()+" "+getPowerDecorated()+"/"+getDamageLeft();
    }
    public void setCids(CreatureInflictDamageStrategy cids){
        this.cids = cids;
        
    }
    public Player getOwner(){
        return owner;
    }
    public void setOwner(Player owner){
        this.owner = owner;
    }
}
