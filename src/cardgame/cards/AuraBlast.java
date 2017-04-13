/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.Effect;
import cardgame.Player;

/**
 *
 * @author giaco
 */
public class AuraBlast implements Card{
    
    private class AuraBlastEffect extends AbstractCardEffect {
        public AuraBlastEffect(Player p, Card c) { super(p,c); }
        @Override
        public void resolve() {}
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
