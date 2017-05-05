package cardgame.cards;

import cardgame.AbstractCreature;
import cardgame.AbstractCreatureCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Player;
import cardgame.TriggerAction;
import cardgame.Triggers;
import cardgame.decorator.PowerUpDecorator;
import cardgame.playerstrategy.*;
import cardgame.target.Target;
import cardgame.target.TargetManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Enrico
 */
public class BenevolentAncestor implements Card{
    
    private class BenevolentAncestorEffect extends AbstractCreatureCardEffect {
        public BenevolentAncestorEffect(Player p, Card c) { super(p,c); }
        @Override
        protected Creature createCreature() { return new BenevolentAncestorCreature(owner); }
    }
    @Override
    public Effect getEffect(Player p) { return new BenevolentAncestorEffect(p,this); }
    
    
    private class BenevolentAncestorCreature extends AbstractCreature {
        ArrayList<Effect> all_effects= new ArrayList<>();
        ArrayList<Effect> tap_effects= new ArrayList<>();
        Target t;
        int type = 0; //2 = creature, 1 = player
        int playerchoice = 0; //2 = Adversary, 1 = Current player
        BenevolentAncestorCreature(Player owner) { 
            super(owner);
            all_effects.add( new Effect() { 
                                    @Override
                                    public boolean play() { 
                                        CardGame.instance.getStack().add(this);
                                        return tap(); 
                                    }
                                    /**
                                     * RESOLVE -> 1. scegli a chi prevenire 1 danno
                                     * 2.se hai scelto una creatura -> viene applicato un decoratore con +1 di vita.
                                     *  In questo modo, al prossimo attacco non viene inflitto 1 danno
                                     * 3. se hai scelto un player -> viene applicata una strategy che previene un danno
                                     */
                                    @Override
                                    public void resolve() {
                                        Scanner in = new Scanner(System.in);
                                        int choice=0;
                                        boolean err = false;
                                        System.out.println("Would you like to prevent 1 damage to player or creature?");
                                        do{
                                            System.out.println("1 - Player\n2 - Creature");
                                            choice = in.nextInt();
                                            type = choice;
                                        }while(choice == 0);
                                        do{
                                            err=false;
                                            if(choice == 2){
                                                try{
                                                     t = (Target)CardGame.instance.getTargetManager().getTarget(TargetManager.CREATURE_ON_FIELD_TARGET);
                                                }catch(Exception e){
                                                    System.out.println("There aren't creatures! Selecting player...");
                                                    err=true;
                                                }
                                                ((Creature)t.getTarget()).addDecorator(new PowerUpDecorator("BenevolentAncestorDecorator",0,1));
                                            }
                                            else{
                                                //setto la strategy
                                                System.out.println("Select player?");
                                                do{
                                                    System.out.println("1 - Current player\n2 - Adversary");
                                                    choice = in.nextInt();
                                                    playerchoice = choice;
                                                }while(choice == 0);
                                                if(choice == 1)
                                                    CardGame.instance.getCurrentPlayer().setInflictDmgStrategy(new PreventOneDamagePlayerStrategy());
                                                else
                                                    CardGame.instance.getCurrentAdversary().setInflictDmgStrategy(new PreventOneDamagePlayerStrategy());
                                            }
                                        }while(err);
                                    }
                                    @Override
                                    public String toString() 
                                        { return "tap: Prevent the next 1 damage that would be dealt to target creature or player this turn."; }
                                }
                ); 
        }
        
        private final TriggerAction BenevolentAncestorTrigger = new TriggerAction(){
            /**EXECUTE -> viene rimosso il decoratore o la strategy
             * 
             * @param args 
             */
            @Override
            public void execute(Object args) {
                if(type != 0){
                    if(type == 1){
                        System.out.println("[BENEVOLENT ANCESTOR] End of turn, removing tap effect from player.");
                        if(playerchoice == 1)
                            CardGame.instance.getCurrentPlayer().setInflictDmgStrategy(new DefaultInflictDamage());
                        else
                            CardGame.instance.getCurrentAdversary().setInflictDmgStrategy(new DefaultInflictDamage());
                    }
                    else{
                        System.out.println("[BENEVOLENT ANCESTOR] End of turn, removing tap effect from creature.");
                        ((Creature)t.getTarget()).removeDecorator("BenevolentAncestorDecorator");
                    }                
                }
                type = 0;
            }   
        };

        @Override
        public void insert() {
            super.insert();
            CardGame.instance.getTriggers().register(Triggers.END_FILTER,BenevolentAncestorTrigger);
        }

        @Override
        public void remove() {
            super.remove();
            CardGame.instance.getTriggers().deregister(BenevolentAncestorTrigger);
        }
        
        @Override
        public String name() { return "Benevolent Ancestor"; }
        
        @Override
        public int getPower() { return 0; }
        @Override
        public int getToughness() { return 4; }
        @Override
        public boolean defender(){ return true; }
        @Override
        public List<Effect> effects() { return all_effects; }
        @Override
        public List<Effect> avaliableEffects() { return (isTapped)?tap_effects:all_effects; }
    }
    
    
    @Override
    public String name() { return "Benevolent Ancestor"; }
    @Override
    public String type() { return "Creature"; }
    @Override
    public String ruleText() { return "Put in play a creature Benevolent Ancestor(0/4) with tap: Prevent the next 1 damage that would be dealt to target creature or player this turn."; }
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    @Override
    public boolean isInstant() { return false; }
}
