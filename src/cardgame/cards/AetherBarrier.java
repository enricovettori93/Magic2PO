
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
        
                private final TriggerAction AetherBarrierAction = new TriggerAction() {
                @Override
                public void execute(Object args) {
                    if (args != null  && args instanceof Creature) {
                        Target t;
                        PermanentTarget app;
                        try{
                            do{
                            
                                t = CardGame.instance.getTargetManager().getTarget(TargetManager.PERMANENT_TARGET, CardGame.instance.getCurrentPlayer());
                            
                            app = (PermanentTarget) t;
                            if(app.getTargetOwner() == CardGame.instance.getCurrentPlayer()){
                                ((Permanent)app.getTarget()).remove();
                                System.out.println("[AETHER BARRIER] Permanent destroyed");
                            }
                            else
                                System.out.println("[AETHER BARRIER] Please reselect.");
                        }while(app.getTargetOwner() != CardGame.instance.getCurrentPlayer());
                        }catch(Exception e){
                                System.out.println("You don't have a permanent!");
                            }
                    }
                }
            };
        @Override
        public void insert() {
            super.insert();
            CardGame.instance.getTriggers().register(Triggers.ENTER_CREATURE_FILTER, AetherBarrierAction);
        }
        
        @Override
        public void remove() {
            super.remove();
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
