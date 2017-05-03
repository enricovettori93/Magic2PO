
package cardgame.cards;


import cardgame.*;
import cardgame.decorator.PowerUpDecorator;
import cardgame.target.TargetManager;
import java.util.List;

public class AncestralMask implements Card{
    
    //BEGIN EFFECT CARD
    private class AncestralMaskEffect extends AbstractEnchantmentCardEffect{
            
        AbstractCreature CreatureTarget;
        
        AncestralMaskEffect(Player p, Card c) { 
            super(p,c);             
        }

        @Override
        protected Enchantment createEnchantment() { return new AncestralMaskEnchantment(owner); }
       
        public void setTarget() {
            CreatureTarget=(AbstractCreature)CardGame.instance.getTargetManager().getTarget(TargetManager.CREATURE_ON_FIELD_TARGET).getTarget();
        }
                
        @Override
        public boolean play() {
            setTarget();
            return super.play();
        }
        
        public void resolve(){
            super.resolve();
            List<Enchantment> temp=CardGame.instance.getCurrentAdversary().getEnchantments();
            temp.addAll(CardGame.instance.getCurrentPlayer().getEnchantments());
            
            for(Enchantment e :temp){
                if(!e.equals(createEnchantment())){
                    CreatureTarget.addDecorator(new PowerUpDecorator(this, 2, 2));
                }
            }
            System.out.println(CreatureTarget.valueOfCreature());
        }
        
        //CLASSE ANONIMA PER IL TRIGGER
        private class AncestralMaskEnchantment extends AbstractEnchantment {
            public AncestralMaskEnchantment(Player owner) {
                super(owner);
            }

            private final TriggerAction AncestralMaskTrigger = new TriggerAction() {
                    @Override
                    public void execute(Object args) {
                        CreatureTarget.addDecorator(new PowerUpDecorator(AncestralMaskTrigger, 2, 2));
                        System.out.println(CreatureTarget.valueOfCreature());
                    }
                };

            @Override
            public void insert() {
                super.insert();
                //setTarget();
                CardGame.instance.getTriggers().register(Triggers.ENTER_ENCHANTMENT_FILTER,AncestralMaskTrigger);
            }

            @Override
            public void remove() {
                super.remove();
                CreatureTarget.removeDecorator(AncestralMaskTrigger);
                System.out.println(CreatureTarget.valueOfCreature());
                CardGame.instance.getTriggers().deregister(AncestralMaskTrigger);
            }

            @Override
            public String name() { return "Ancestral Mask"; }
        }
    }
    //END EFFECT CLASS
    
    @Override
    public Effect getEffect(Player owner) {
        return new AncestralMaskEffect(owner, this);
    }

    @Override
    public String name() {
        return "Ancestral Mask";
    }

    @Override
    public String type() {
        return "Enchantment";
    }

    @Override
    public String ruleText() {
        return "Enchanted creature gets +2/+2 for each other enchantment on the battlefield.";
    }

    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    
    @Override
    public boolean isInstant() {
        return false;
    }
    
}
