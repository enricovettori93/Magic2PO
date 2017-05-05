/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.*;
import java.util.List;

public class CalmingVerse implements Card{

    private class CalmingVerseEffect extends AbstractCardEffect{

        CalmingVerseEffect(Player p, Card c) { super(p,c); }
        /**
         * RESOLVE -> prende gli incantesimi dell'avversario e gli distrugge
         */
        @Override
        public void resolve() {
           List <Enchantment> app = CardGame.instance.getCurrentAdversary().getEnchantments();
           for(int i = 0;i<app.size();i++){
               app.get(i).remove();
           }
        }
    }
    @Override
    public Effect getEffect(Player owner) {
        return new CalmingVerseEffect(owner, this);
    }

    @Override
    public String name() {
        return "Calming Verse";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String ruleText() {
        return "Calming Verse destroy all enchantments you don't control.";
    }

    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    
    @Override
    public boolean isInstant() {
        return false;
    }
    
}
