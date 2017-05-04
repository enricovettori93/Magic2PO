
package cardgame;

import cardgame.creaturestrategy.*;
import cardgame.decorator.*;

public abstract class AbstractCreature implements Creature {
    
    protected Player owner;
    protected boolean isTapped=false;
    private int damageLeft = getToughness();
    private int powerLeft = getPower();
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
            System.out.println("["+name()+"] attacking rival... "+this.getPowerLeft());
            CardGame.instance.getCurrentAdversary().inflictDamage(this.getPowerLeft());
        }
    @Override
        public void defend(Creature c) {
            inflictDamage(c.getPowerLeft());
            c.decreasePowerLeft(this.getToughnessDecorated());
        }
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
        public void resetDamage() { 
            damageLeft = getToughnessDecorated(); 
            powerLeft = getPowerDecorated();
        }
    
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
        
    @Override
    public void addDecorator(AbstractDecorator d){
        dms.addDecorator(d);
    }
    
    @Override
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
    
    @Override
    public int getDamageLeft(){
        return damageLeft;
    }
    @Override
    public int getPowerLeft(){
        return powerLeft;
    }
    @Override
    public void init(){
        damageLeft=getToughness();
        powerLeft=getPower();
    }
    
    @Override
    public void increaseDamageLeft(int val){
        damageLeft+=val;
    }

    public void decreasePowerLeft(int val){
        powerLeft-=val;
    }
    public String valueOfCreature(){
        return name()+" "+getPowerDecorated()+"/"+getDamageLeft();
    }
    
    @Override
    public void setCids(CreatureInflictDamageStrategy cids){
        this.cids = cids;
        
    }
    
    @Override
    public Player getOwner(){
        return owner;
    }
    
    @Override
    public void setOwner(Player owner){
        this.owner = owner;
    }
    
    //effetti particolari delle creature
    
    public boolean defender(){
        return false;
    }
    
    public boolean shroud(){
        return false;
    }
}
