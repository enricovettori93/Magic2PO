/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Effect;
import cardgame.Enchantment;
import cardgame.Player;
import java.util.Scanner;

/**
 *
 * @author giaco
 */
public class AuraBlast implements Card{
    
    private class AuraBlastEffect extends AbstractCardEffect {
        public AuraBlastEffect(Player p, Card c) { super(p,c); }        
        @Override
        public void resolve() {
            //draw a card
            int i=0;
            i=selectIndexTarget();
            if(i!=0){
                CardGame.instance.getCurrentAdversary().getEnchantments().remove(i);
                /*bisogna ancora gestire i Decorator creati da quell'incantesimo rimossso*/
            }
            owner.draw();
        }
        private int selectIndexTarget(){
            int i=1;
            int index=0;
            System.out.println(name()+" SELECT TARGET:");
            for(Enchantment a : CardGame.instance.getCurrentAdversary().getEnchantments()){
                System.out.println(i+" "+a.name());
                i++;
            }
            Scanner input = new Scanner(System.in);
            index = input.nextInt();
            return index;
        }
    }
    @Override
    public Effect getEffect(Player owner) {
        return new AuraBlastEffect(owner, this);
    }

    @Override
    public String name() {
       return "Aura Blast";
    }

    @Override
    public String type() {
        return "Instant";
    }

    @Override
    public String ruleText() {
        return name() + " destroy target enchantment. Draw a card.";
    }
    @Override
    public String toString(){
        return name() + " (" + type() + ") [" + ruleText() +"]";
    }
    @Override
    public boolean isInstant() {
        return true;
    }
    
}
