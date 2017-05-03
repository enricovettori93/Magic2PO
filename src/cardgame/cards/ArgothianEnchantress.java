
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
        return "ArgothianEnchantress";
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
            all_effects.add(new AbstractEffect() {
                private TriggerAction spell_played;
                @Override
                public void resolve() {
                    insert();
                }
                @Override
                public String toString() 
                    { return "" + ruleText(); }
                @Override
                public boolean play(){
                    CardGame.instance.getStack().add(this);
                    spell_played = new TriggerAction() {
                            @Override
                            public void execute(Object args) {
                                Enchantment c = (Enchantment)args;
                                List <Enchantment> app = CardGame.instance.getCurrentPlayer().getEnchantments();
                                if(app.contains(c)){
                                    System.out.println("[ARGOTHIAN ENCHANTRESS] Current player " + CardGame.instance.getCurrentPlayer().name() + " pesching a card because cast an enchantment.");
                                    CardGame.instance.getCurrentPlayer().draw();
                                }
                            }
                        };

                    return true;
                }
                
                public void insert() {
                    CardGame.instance.getTriggers().register(Triggers.ENTER_ENCHANTMENT_FILTER, spell_played);
                }

                public void remove() {
                    CardGame.instance.getTriggers().deregister(spell_played);
                }
            });
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
            return "ArgothianEnchantress";
        }
        
        @Override
        public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    }
}
