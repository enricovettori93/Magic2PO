
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
    public boolean play() {
            try{
                setTarget();
                return super.play();
            }
            catch(Exception e){
                System.out.println("[AFFLICT] No target avaiable.");
                return false;
            }
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
                PuD = new PowerUpDecorator(AfflictTrigger, -1, -1);
                CreatureTarget.addDecorator(PuD);
                insert();
            }
        }
        
        public void setTarget()throws Exception{
            try{
                targets.add(CardGame.instance.getTargetManager().getTarget(TargetManager.CREATURE_ON_FIELD_TARGET));
            }catch(Exception e){
                throw new Exception(e.getMessage());
            }
        }
        
        private TriggerAction AfflictTrigger=new TriggerAction() {
            @Override
            public void execute(Object args) {
                CreatureTarget.removeDecorator(this);
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
