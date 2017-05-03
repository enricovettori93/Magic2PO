
package cardgame.cards;

import cardgame.*;

public class AetherFlash implements Card{
    private class AetherFlashEffect extends AbstractEnchantmentCardEffect{
        public AetherFlashEffect(Player p, Card c) { 
            super(p,c);
        }        

        @Override
        protected Enchantment createEnchantment() {
            return new AetherFlashEnchantment(owner); 
        }
        
        
    }
    
    private class AetherFlashEnchantment extends AbstractEnchantment{
        private AetherFlashEnchantment(Player owner){
            super(owner);
        }
        private final TriggerAction AetherFlashAction = new TriggerAction() {
                @Override
                public void execute(Object args) {
                    if (args != null  && args instanceof Creature) {
                        Creature c = (Creature)args;
                        System.out.println("[AetherFlash] inflict 2 damage to "+c.name());
                        c.inflictDamage(2);
                    }
                }
            };
        
        @Override
        public void insert() {
            super.insert();
            CardGame.instance.getTriggers().register(Triggers.ENTER_CREATURE_FILTER, AetherFlashAction);
        }
        
        @Override
        public void remove() {
            super.remove();
            CardGame.instance.getTriggers().deregister(AetherFlashAction);
        }
        
        @Override
        public String name() {
            return "Aether Flash";
        }
    }
    
    @Override
    public Effect getEffect(Player owner) {
       return new AetherFlashEffect(owner,this);
    }
    
     @Override
    public String name() {
        return "AetherFlash";
    }
    
    @Override
    public String type() {
       return "Enchantment";
    }

    @Override
    public String ruleText() {
        return "Whenever a creature comes into play, AetherFlash deals 2 damage to it.";
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
