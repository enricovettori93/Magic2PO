
package cardgame.cards;

import cardgame.*;
import cardgame.target.PermanentTarget;
import cardgame.target.Target;
import cardgame.target.TargetManager;

public class AetherBarrier implements Card{

    private class AetherBarrierEffect extends AbstractEnchantmentCardEffect{
        public AetherBarrierEffect(Player p, Card c) { 
            super(p,c);
           
        }        

        @Override
        protected Enchantment createEnchantment() {
            return new AetherBarrierEnchantment(owner); 
        }        
        
    }
    
    private class AetherBarrierEnchantment extends AbstractEnchantment{
        private AetherBarrierEnchantment(Player owner){
            super(owner);
        }
        /**
         * TRIGGER DI AETHERBARRIER: quando viene eseguito questo Trigger,
         * il giocatore che ha appena pescato una carta (quindi il giocatore corrente)
         * deve scartare un Permanente. Se non ne ha, allora non succede nulla
         * 
         */
        private final TriggerAction AetherBarrierAction = new TriggerAction() {
            @Override
            public void execute(Object args) {
                if (args != null  && args instanceof Creature) {
                    Target t;
                    PermanentTarget app;
                    try{
                        //Il target è un  permanente del giocatore corrente
                        t = CardGame.instance.getTargetManager().getTarget(TargetManager.PERMANENT_CURRENT_TARGET);
                        app = (PermanentTarget) t;
                        ((Permanent)app.getTarget()).remove(); //rimuovo il permanente
                        System.out.println("[AETHER BARRIER] Permanent destroyed");                   
                    }catch(Exception e){
                            System.out.println("You don't have a permanent!");
                        }
                }
            }
        };
        @Override
        public void insert() {
            super.insert();
            //all'inserimento registro il Trigger di AetherBarrierAction
            CardGame.instance.getTriggers().register(Triggers.ENTER_CREATURE_FILTER, AetherBarrierAction);
        }
        
        @Override
        public void remove() {
            super.remove();
            //deregistro il Trigger
            CardGame.instance.getTriggers().deregister(AetherBarrierAction);
        }
        
        @Override
        public String name() {
            return "Aether Barrier"; //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    @Override
    public Effect getEffect(Player owner) {
       return new AetherBarrierEffect(owner,this);
    }
    
     @Override
    public String name() {
        return "AetherBarrier";
    }
    
    @Override
    public String type() {
       return "Enchantment";
    }

    @Override
    public String ruleText() {
        return "Whenever a player plays Creature Spell, that player sacrifices a permanent";
    }
    
    @Override
    public boolean isInstant() {
       return false;
    }
    
    @Override
    public String toString(){
        return name() + " (" + type() + ") [" + ruleText() +"]";
    }
    
}
