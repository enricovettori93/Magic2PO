
package cardgame.cards;

import cardgame.*;
import java.util.ArrayList;
import java.util.List;

public class BronzeSable implements Card {
    private class BronzeSableEffect extends AbstractCreatureCardEffect {
        public BronzeSableEffect(Player p, Card c) { super(p,c); }
        @Override
        protected Creature createCreature() { return new BronzeSableCreature(owner); }
    }
    @Override
    public Effect getEffect(Player p) { return new BronzeSableEffect(p,this); }
    
    
    private class BronzeSableCreature extends AbstractCreature {
        ArrayList<Effect> all_effects= new ArrayList<>();
        ArrayList<Effect> tap_effects= new ArrayList<>();
        
        BronzeSableCreature(Player owner) { 
            super(owner);
            all_effects.add( new Effect() { 
                                    @Override
                                    public boolean play() { 
                                        CardGame.instance.getStack().add(this);
                                        return tap(); 
                                    }
                                    @Override
                                    public void resolve() {}
                                    @Override
                                    public String toString() 
                                        { return "tap: Bronze Sable does nothing"; }
                                }
                ); 
        }
        
        @Override
        public String name() { return "Bronze Sable"; }
        
        @Override
        public void attack() {}
        @Override
        public void defend(Creature c) {}
        @Override
        public int getPower() { return 2; }
        @Override
        public int getToughness() { return 1; }

        @Override
        public List<Effect> effects() { return all_effects; }
        @Override
        public List<Effect> avaliableEffects() { return (isTapped)?tap_effects:all_effects; }
    }
    
    
    @Override
    public String name() { return "Bronze Sable"; }
    @Override
    public String type() { return "Creature"; }
    @Override
    public String ruleText() { return "Put in play a creature Bronze Sable(2/1) with tap: Bronze Sable does nothing"; }
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    @Override
    public boolean isInstant() { return false; }
}
