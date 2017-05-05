
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
        }
        
        @Override
        public boolean play() {
                try{
                    setTarget(); //setto target
                    return super.play();
                }
                catch(Exception e){
                    System.out.println("[AFFLICT] No target avaiable.");
                    return false;
                }
        }
        /**
         * RESOLVE -> SE la vita del target è pari a 1, rimuovo la creatura (andrebbe a -1 quindi muore)
         *          Se invece è maggiore di -1, applico un Decoratore -1 / -1
         */
        @Override
        public void resolve(){
            CreatureTarget=(AbstractCreature)targets.remove(0).getTarget();
            // Aggiungere decoratore al Target - nell'else perchè è quando si può eseguire tale azione
            if (CreatureTarget.getDamageLeft()== 1){
                CreatureTarget.remove();
                // rimuovo la carta perchè la vita va a 0
            }
            else {
                //applico un decoratore al target
                PuD = new PowerUpDecorator(AfflictTrigger, -1, -1); 
                CreatureTarget.addDecorator(PuD);
                insert(); 
            }
        }
        
        public void setTarget()throws Exception{
            try{
                //i target sono le creature nel campo
                targets.add(CardGame.instance.getTargetManager().getTarget(TargetManager.CREATURE_ON_FIELD_TARGET));
            }catch(Exception e){
                throw new Exception(e.getMessage()); //vuol dire che non ci sono target
            }
        }
        
        private TriggerAction AfflictTrigger=new TriggerAction() {
            @Override
            public void execute(Object args) {
                CreatureTarget.removeDecorator(this); //rimuove il decoratore
            }
        };

        public void insert() {
            //inserisco il trigger, viene  eseguito alla fine del turno
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
