/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Enchantment;
import cardgame.Player;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Enrico
 */
public class AncestralMask implements Card{
    
    private class AncestralMaskEffect extends AbstractCardEffect{

        AncestralMaskEffect(Player p, Card c) { super(p,c); }
        
        @Override
        public void resolve() {
            int powerup = 0;
            int in;
            Scanner input = new Scanner(System.in);
            List<Enchantment> temp=CardGame.instance.getCurrentAdversary().getEnchantments();
            powerup = temp.size();
            temp=CardGame.instance.getCurrentPlayer().getEnchantments();
            powerup = powerup + temp.size();
            powerup = 2 * powerup;
            System.out.println("Player's creature");
            System.out.println("" + CardGame.instance.getCurrentPlayer().getCreatures());
            System.out.println("Adversary's creature");
            System.out.println("" + CardGame.instance.getCurrentAdversary().getCreatures());
            System.out.println("Select player creature target -> 0 = player, 1 = adversary");
            do{
                in =  input.nextInt();
            }while(in != 0 || in != 1);
            System.out.println("Select creature");
            if(in == 0){
                System.out.println("" + CardGame.instance.getCurrentPlayer().getCreatures());
                do{
                    in = input.nextInt();
                }while(in < 0 || in > CardGame.instance.getCurrentPlayer().getCreatures().size());
                //DA DEFINIRE DECORATORE PER AUMENTARE ATTACCO E DIFESA DELLA CREATURA
                CardGame.instance.getCurrentPlayer().getCreatures().get(in+1).DECORATOREAUMENTAATKDEF(powerup);
            }
            else{
                System.out.println("" + CardGame.instance.getCurrentAdversary().getCreatures());
                do{
                    in = input.nextInt();
                }while(in < 0 || in > CardGame.instance.getCurrentAdversary().getCreatures().size());
                //DA DEFINIRE DECORATORE PER AUMENTARE ATTACCO E DIFESA DELLA CREATURA
                CardGame.instance.getCurrentAdversary().getCreatures().get(in+1).DECORATOREAUMENTAATKDEF(powerup);
            }
        }
    }
    
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
    public boolean isInstant() {
        return false;
    }
    
}
