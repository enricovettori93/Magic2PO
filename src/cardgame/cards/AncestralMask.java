
package cardgame.cards;


import cardgame.*;
import cardgame.decorator.PowerUpDecorator;
import cardgame.target.TargetManager;
import java.util.List;

public class AncestralMask implements Card{
    
    //BEGIN EFFECT CARD
    private class AncestralMaskEffect extends AbstractEnchantmentCardEffect{
            
        AbstractCreature CreatureTarget;
        AncestralMaskEnchantment effetto_ancestral_mask;
                
        AncestralMaskEffect(Player p, Card c) { 
            super(p,c);             
        }

        @Override
        protected Enchantment createEnchantment() {
            effetto_ancestral_mask = new AncestralMaskEnchantment(owner);
            return effetto_ancestral_mask; 
        }
       
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
            Enchantment e;
            for(int i = 0; i<temp.size();i++){
                e=temp.get(i);
                if(e.hashCode()!=effetto_ancestral_mask.hashCode()){
                    System.out.print("[ANCESTRAL MASK] -> " + CreatureTarget.valueOfCreature());
                    CreatureTarget.addDecorator(new PowerUpDecorator(effetto_ancestral_mask, 2, 2));
                    System.out.println(" power up +2/+2 -> " + CreatureTarget.valueOfCreature());
                }
            }
            temp.clear();
            //System.out.println(CreatureTarget.valueOfCreature());
        }
        
        //CLASSE ANONIMA PER IL TRIGGER
        private class AncestralMaskEnchantment extends AbstractEnchantment {
            public AncestralMaskEnchantment(Player owner) {
                super(owner);
            }

            private final TriggerAction AncestralMaskTrigger = new TriggerAction() {
                    @Override
                    public void execute(Object args) {
                        System.out.print("[ANCESTRAL MASK] Get triggered, entered enchantment on field -> " + CreatureTarget.valueOfCreature());
                        CreatureTarget.addDecorator(new PowerUpDecorator(effetto_ancestral_mask, 2, 2));
                        System.out.println(" power up +2/+2 -> " + CreatureTarget.valueOfCreature());
                    }
                };
            
            private final TriggerAction AncestralMaskTriggerRemoveEnchantmentFromField = new TriggerAction(){
                @Override
                public void execute(Object args) {
                    System.out.print("[ANCESTRAL MASK] Get triggered, removed enchantment on field -> " + CreatureTarget.valueOfCreature());
                    CreatureTarget.addDecorator(new PowerUpDecorator(effetto_ancestral_mask, -2, -2));
                    System.out.println(" power up -2/-2 -> " + CreatureTarget.valueOfCreature());
                }   
            };
            
            @Override
            public void insert() {
                super.insert();
                //setTarget();
                CardGame.instance.getTriggers().register(Triggers.ENTER_ENCHANTMENT_FILTER,AncestralMaskTrigger);
                CardGame.instance.getTriggers().register(Triggers.EXIT_ENCHANTMENT_FILTER,AncestralMaskTriggerRemoveEnchantmentFromField);
            }

            @Override
            public void remove() {
                super.remove();
                CreatureTarget.removeDecorator(effetto_ancestral_mask);
                //System.out.println("GAVINO " + CreatureTarget.valueOfCreature());
                CardGame.instance.getTriggers().deregister(AncestralMaskTrigger);
                CardGame.instance.getTriggers().deregister(AncestralMaskTriggerRemoveEnchantmentFromField);
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
