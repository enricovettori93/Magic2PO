
package cardgame.cards;


import cardgame.*;
import cardgame.decorator.PowerUpDecorator;
import cardgame.target.TargetManager;
import java.util.ArrayList;
import java.util.List;

public class AncestralMask implements Card{
    
    //BEGIN EFFECT CARD
    private class AncestralMaskEffect extends AbstractEnchantmentCardEffectTarget{
            
        AbstractCreature CreatureTarget;
        AncestralMaskEnchantment effetto_ancestral_mask;
        int hashcode;
        
        AncestralMaskEffect(Player p, Card c) { 
            super(p,c);   
            hashcode = this.hashCode();
        }

        @Override
        protected Enchantment createEnchantment() {
            effetto_ancestral_mask = new AncestralMaskEnchantment(owner);
            return effetto_ancestral_mask; 
        }
       
        public void setTarget() throws Exception {
            try{
                CreatureTarget=(AbstractCreature)CardGame.instance.getTargetManager().getTarget(TargetManager.CREATURE_ON_FIELD_TARGET).getTarget();
            }catch(Exception e){
                throw new Exception(e.getMessage());
            }
        }
                
        @Override
        public boolean play() {
            try{
                setTarget(); //provo a settare il target
                return super.play();
            }
            catch(Exception e){
                System.out.println("[ANCESTRAL MASK] No target avaiable.");
                return false;
            }
        }
        
        /**
         * RESOLVE -> La creature target riceve +2 / +2 per ogni incantesimo che entra nel campo
         *              HashCode -> 
         */
        @Override
        public void resolve(){
            super.resolve();
            List<Enchantment> temp = new ArrayList<Enchantment>();
            temp.addAll(CardGame.instance.getCurrentAdversary().getEnchantments());
            temp.addAll(CardGame.instance.getCurrentPlayer().getEnchantments());
            Enchantment e;
            for(int i = 0; i<temp.size();i++){
                e=temp.get(i);
                if(e.hashCode()!=effetto_ancestral_mask.hashCode()){
                    System.out.print("[ANCESTRAL MASK] -> " + CreatureTarget.valueOfCreature());
                    CreatureTarget.addDecorator(new PowerUpDecorator(hashcode, 2, 2));
                    System.out.println(" power up +2/+2 -> " + CreatureTarget.valueOfCreature());
                }
            }
        }
        
        //CLASSE ANONIMA PER IL TRIGGER
        private class AncestralMaskEnchantment extends AbstractEnchantment {
            public AncestralMaskEnchantment(Player owner) {
                super(owner);
            }
            /**
             * TRIGGER ANCESTRALMASKTRIGGER -> Se è entrata una creatura aggiungo un decoratore al target
             */
            private final TriggerAction AncestralMaskTrigger = new TriggerAction() {
                    @Override
                    public void execute(Object args) {
                        System.out.print("[ANCESTRAL MASK] Get triggered, entered enchantment on field -> " + CreatureTarget.valueOfCreature());
                        CreatureTarget.addDecorator(new PowerUpDecorator(hashcode, 2, 2));
                        System.out.println(" power up +2/+2 -> " + CreatureTarget.valueOfCreature());
                    }
                };
            /**
             * TRIGGER -> questo trigger, quando viene rimosso un incantesimo, tolgo -2 / -2 al target
             */
            private final TriggerAction AncestralMaskTriggerRemoveEnchantmentFromField = new TriggerAction(){
                @Override
                public void execute(Object args) {
                    System.out.print("[ANCESTRAL MASK] Get triggered, removed enchantment on field -> " + CreatureTarget.valueOfCreature());
                    CreatureTarget.addDecorator(new PowerUpDecorator(hashcode, -2, -2));
                    System.out.println(" power up -2/-2 -> " + CreatureTarget.valueOfCreature());
                }   
            };
            
            @Override
            public void insert() {
                super.insert();
                //inserisco i 2 trigger
                CardGame.instance.getTriggers().register(Triggers.ENTER_ENCHANTMENT_FILTER,AncestralMaskTrigger);
                CardGame.instance.getTriggers().register(Triggers.EXIT_ENCHANTMENT_FILTER,AncestralMaskTriggerRemoveEnchantmentFromField);
            }

            @Override
            public void remove() {
                super.remove();
                CreatureTarget.removeDecorator(hashcode);
                System.out.println("[ANCESTRAL MASK] Destroyed, remove effect from card  " + CreatureTarget.valueOfCreature());
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
