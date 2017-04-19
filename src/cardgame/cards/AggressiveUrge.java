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
 * @author Enrico
 */
public class AggressiveUrge implements Card {
    private class AggressiveUrgeEffect extends AbstractCardEffect {
        public AggressiveUrgeEffect(Player p, Card c) { super(p,c); }
        @Override
        //MANCA IMPLEMENTAZIONE METODO RESOLVE
        public void resolve() {}
    }

    @Override
    public Effect getEffect(Player owner) { 
        return new AggressiveUrgeEffect(owner, this); 
    }
    
    @Override
    public String name() { return "Aggressive Urge"; }
    @Override
    public String type() { return "Instant"; }
    @Override
    public String ruleText() { return name() + " Target creature gets +1/+1 untile end of turn"; }
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    @Override
    public boolean isInstant() { return true; }
}
