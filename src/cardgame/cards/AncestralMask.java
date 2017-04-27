/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.AbstractCardEffectTarget;
import cardgame.AbstractCreature;
import cardgame.AbstractEnchantment;
import cardgame.AbstractEnchantmentCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Enchantment;
import cardgame.Permanent;
import cardgame.target.PermanentTarget;
import cardgame.Player;
import cardgame.TriggerAction;
import cardgame.Triggers;
import cardgame.decorator.PowerUpDecorator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Enrico
 */
public class AncestralMask implements Card{
    
    //BEGIN EFFECT CARD
    private class AncestralMaskEffect extends AbstractEnchantmentCardEffect{
            
        PermanentTarget effectTarget;
        PowerUpDecorator app;
        int powerup;
        
        AncestralMaskEffect(Player p, Card c) { 
            super(p,c); 
            
        }

        @Override
        protected Enchantment createEnchantment() { return new AncestralMaskEnchantment(owner); }
        
        @Override
        public void resolve() {
            app = new PowerUpDecorator(this, powerup, powerup);
            ((Creature)effectTarget.getTarget()).addDecorator(app);
            //System.out.println(" " + effectTarget.getTarget().toString()+((Creature)effectTarget.getTarget()).getPowerDecorated());
        }

        public void setTarget() {
            int in;
            Scanner input = new Scanner(System.in);
            powerup = 0;
            powerup = getNumberEnchantmens();
            powerup = 2 * powerup;
            System.out.println("Select player creature target -> 0 = player, 1 = adversary");
            do{
                in =  input.nextInt();
            }while(in != 0 && in != 1);
            System.out.println("Select creature");
            if(in == 0){
                CardGame.instance.getCurrentPlayer().printPermanents();
                do{
                    in = input.nextInt();
                }while(in < 0 || in > CardGame.instance.getCurrentPlayer().getCreatures().size());
                effectTarget = new PermanentTarget(owner,CardGame.instance.getCurrentPlayer().getCreatures().get(in-1));
            }
            else{
                CardGame.instance.getCurrentAdversary().printPermanents();
                do{
                    in = input.nextInt();
                }while(in < 0 || in > CardGame.instance.getCurrentAdversary().getCreatures().size());
                effectTarget = new PermanentTarget(owner,CardGame.instance.getCurrentAdversary().getCreatures().get(in-1));
            }
        }
        
        public int getNumberEnchantmens(){
            List<Enchantment> temp=CardGame.instance.getCurrentAdversary().getEnchantments();
            powerup = temp.size();
            temp=CardGame.instance.getCurrentPlayer().getEnchantments();
            powerup = powerup + temp.size();
            return powerup - 1;
        }
        
        @Override
        public boolean play() {
            setTarget();
            return super.play();
        }
        
        //CLASSE ANONIMA PER IL TRIGGER
        private class AncestralMaskEnchantment extends AbstractEnchantment {
            public AncestralMaskEnchantment(Player owner) {
                super(owner);
            }

            private final TriggerAction PowerUpAction = new TriggerAction() {
                    @Override
                    public void execute(Object args) {
                        if (args != null  && args instanceof Creature) {
                            Creature c = (Creature)args;
                            int newPowerUp = getNumberEnchantmens();
                            powerup = newPowerUp * 2;
                            resolve();
                        }
                    }
                };

            @Override
            public void insert() {
                super.insert();
                CardGame.instance.getTriggers().register(Triggers.ENTER_ENCHANTMENT_FILTER, PowerUpAction);
            }

            @Override
            public void remove() {
                super.remove();
                CardGame.instance.getTriggers().deregister(PowerUpAction);
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
