
package cardgame.cards;

import cardgame.*;
import java.util.ArrayList;
import java.util.List;

public class ArgothianEnchantress implements Card{

    private class ArgothianEnchantressEffect extends AbstractCreatureCardEffect{
        
        public ArgothianEnchantressEffect(Player p, Card c){
            super(p,c);
        }
        
        @Override
        protected Creature createCreature() {
            return new ArgothianEnchantressCreature(owner);
        }        
    }
    
    @Override
    public Effect getEffect(Player owner) {
        return new ArgothianEnchantressEffect(owner,this);
    }

    @Override
    public String name() {
        return "Argothian Enchantress";
    }

    @Override
    public String type() {
        return "Creature";
    }

    @Override
    public String ruleText() {
        return "Put in play a creature ArgothianEnchantress(0/1) with effects: Shroud(this creature can't be a target) , Whenever you cast an enchantment spell,draw a card";
    }

    @Override
    public boolean isInstant() {
        return false;
    }    
    
    @Override
    public String toString() { 
        return name() + " (" + type() + ") [" + ruleText() +"]";
    }
    
    private class ArgothianEnchantressCreature extends AbstractCreature{

        ArrayList<Effect> all_effects= new ArrayList<>();
        ArrayList<Effect> tap_effects= new ArrayList<>();
        
        public ArgothianEnchantressCreature(Player owner){
            super(owner);
        }
        
        private final TriggerAction ArgothianEnchantressTrigger = new TriggerAction(){
            @Override
            public void execute(Object args) {
                Enchantment app = (Enchantment) args;
                if(CardGame.instance.getCurrentPlayer() == owner){
                    System.out.println("[ARGOTHIAN ENCANTRESS] Current player spell an enchantment -> Current player draw a card.");
                    CardGame.instance.getCurrentAdversary().draw();
                }
            }   
        };

        @Override
        public void insert() {
            super.insert();
            //setTarget();
            CardGame.instance.getTriggers().register(Triggers.ENTER_ENCHANTMENT_FILTER,ArgothianEnchantressTrigger);
        }

        @Override
        public void remove() {
            super.remove();
            CardGame.instance.getTriggers().deregister(ArgothianEnchantressTrigger);
        }
        
        @Override
        public boolean shroud(){
            return true;
        }
        
        @Override
        public int getPower() {
            return 0;
        }

        @Override
        public int getToughness() {
            return 1;
        }

        @Override
        public List<Effect> effects() {
            return all_effects;
        }

        @Override
        public List<Effect> avaliableEffects() {
            return all_effects;
        }

        @Override
        public String name() {
            return "Argothian Enchantress";
        }
        
        @Override
        public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    }
}
