
package cardgame.cards;

import cardgame.*;
import cardgame.decorator.PowerUpDecorator;
import cardgame.target.TargetManager;

public class Afflict implements Card{
    
    private class AfflictEffect extends AbstractCardEffectTarget {
    
        AbstractCreature CreatureTarget;
        PowerUpDecorator PuD;     
        
        public AfflictEffect (Player p, Card c){
            super(p,c);
            //
        }
        
    @Override
        public boolean play(){
            setTarget();
            return super.play();

        }
        
    @Override
        public void resolve(){
            CreatureTarget=(AbstractCreature)targets.remove(0).getTarget();
            // Aggiungere decoratore al Target - nell'else perchè è quando si può eseguire tale azione
            if (CreatureTarget.getDamageLeft()== 1){
                CreatureTarget.remove();
                // rimuovo la carta perchè la vita va a 0
            }
            else {
                // la toughness della carta decorata cambia
                System.out.println(CreatureTarget.valueOfCreature());
                PuD = new PowerUpDecorator(AfflictTrigger, -1, -1);
                CreatureTarget.addDecorator(PuD);
                System.out.println(CreatureTarget.valueOfCreature());
                insert();
            }
        }
        
        public void setTarget(){
            targets.add(CardGame.instance.getTargetManager().getTarget(TargetManager.CREATURE_ON_FIELD_TARGET));
        }
        
        private TriggerAction AfflictTrigger=new TriggerAction() {
            @Override
            public void execute(Object args) {
                System.out.println(CreatureTarget.valueOfCreature());
                CreatureTarget.removeDecorator(this);
                System.out.println(CreatureTarget.valueOfCreature());
            }
        };

        public void insert() {
            CardGame.instance.getTriggers().register(Triggers.END_FILTER, AfflictTrigger);
        }

        public void remove() {            
            CardGame.instance.getTriggers().deregister(AfflictTrigger);
        }
    }

    @Override
    public Effect getEffect(Player owner) { 
        return new AfflictEffect(owner, this); 
    }    
    
    @Override
    public String name() {
       return "Afflict";
    }

    @Override
    public String type() {
        return "Instant";
    }

    @Override
    public String ruleText() {
        return name() + " Target creature gets -1/-1 until end of turn";
    }
    @Override
    public String toString(){
        return name() + " (" + type() + ") [" + ruleText() +"]";
    }
    @Override
    public boolean isInstant() {
        return true;
    }
    
}
