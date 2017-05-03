
package cardgame.cards;

import cardgame.*;
import java.util.ArrayList;
import java.util.List;

public class NorwoodRanger implements Card{
    private class NorwoodRangerEffect extends AbstractCreatureCardEffect {
        public NorwoodRangerEffect(Player p, Card c) { super(p,c); }
        @Override
        protected Creature createCreature() { return new NorwoodRangerCreature(owner); }
    }
    @Override
    public Effect getEffect(Player p) { return new NorwoodRangerEffect(p,this); }
    
    
    private class NorwoodRangerCreature extends AbstractCreature {
        ArrayList<Effect> all_effects= new ArrayList<>();
        ArrayList<Effect> tap_effects= new ArrayList<>();
        
        NorwoodRangerCreature(Player owner) { 
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
                                        { return "tap: Norwood Ranger does nothing"; }
                                }
                ); 
        }
        
        @Override
        public String name() { return "Norwood Ranger"; }
        
        @Override
        public int getPower() { return 1; }
        @Override
        public int getToughness() { return 2; }

        @Override
        public List<Effect> effects() { return all_effects; }
        @Override
        public List<Effect> avaliableEffects() { return (isTapped)?tap_effects:all_effects; }
    }
    
    
    @Override
    public String name() { return "Norwood Ranger"; }
    @Override
    public String type() { return "Creature"; }
    @Override
    public String ruleText() { return "Put in play a creature Norwood Ranger(1/2) with tap: Norwood Ranger does nothing"; }
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    @Override
    public boolean isInstant() { return false; }
}
