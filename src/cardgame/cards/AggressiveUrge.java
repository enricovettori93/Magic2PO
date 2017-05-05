
package cardgame.cards;

import cardgame.*;
import cardgame.decorator.PowerUpDecorator;
import cardgame.target.TargetManager;

public class AggressiveUrge implements Card {
    private class AggressiveUrgeEffect extends AbstractCardEffectTarget {
        
        AbstractCreature CreatureTarget;
        PowerUpDecorator pd;
        
        public AggressiveUrgeEffect(Player p, Card c) { 
            super(p,c); 
        }
        /**
         * AGGIUNGE UN DECORATORE +1 / +1  AL TARGET
         */
        @Override
        public void resolve() {
            CreatureTarget=(AbstractCreature)targets.remove(0).getTarget();
            pd = new PowerUpDecorator(AggressiveUrgeTrigger, 1, 1); //creo decoratore
            CreatureTarget.addDecorator(pd); //aggiungo decorator
            insert();
        }
        
        @Override
        public boolean play() {
            try{
                setTarget(); //setto il target
                return super.play();
            }
            catch(Exception e){
                System.out.println("[AGGRESSIVE URGE] No target avaiable.");
                return false;
            }
        }

        @Override
        public void setTarget() throws Exception{
            try{
                targets.add(CardGame.instance.getTargetManager().getTarget(TargetManager.CREATURE_ON_FIELD_TARGET));
            }catch(Exception e){
                throw new Exception(e.getMessage());
            }
        }
        /**
         * TRIGGER rimuove il decoratore alla fine del turno
         */
        private TriggerAction AggressiveUrgeTrigger=new TriggerAction() {
            @Override
            public void execute(Object args) {
                CreatureTarget.removeDecorator(this);
                remove();
            }
        };

        public void insert() {
            CardGame.instance.getTriggers().register(Triggers.END_FILTER, AggressiveUrgeTrigger);
        }

        public void remove() {            
            CardGame.instance.getTriggers().deregister(AggressiveUrgeTrigger);
        }
    }

    @Override
    public Effect getEffect(Player owner) { 
        return new AggressiveUrgeEffect(owner, this); 
    }
    
    @Override
    public String name() { return "Aggressive Urge"; }
    @Override
    public String type() { return "Instant"; }
    @Override
    public String ruleText() { return name() + " Target creature gets +1/+1 untile end of turn"; }
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    @Override
    public boolean isInstant() { return true; }
}
