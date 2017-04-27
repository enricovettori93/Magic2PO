/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.DefaultCombatPhase;
import cardgame.Effect;
import cardgame.OneTimeExecuteBattleStrategy;
import cardgame.Phases;
import cardgame.Player;
import cardgame.PreventAllDamageStrategy;

/**
 *
 * @author giaco
 */
public class Darkness implements Card{
    private class DarknessEffect extends AbstractCardEffect{
        public DarknessEffect(Player p, Card c){
            super(p,c);
        }
        @Override
        public void resolve() {
           DefaultCombatPhase thisCombat = (DefaultCombatPhase)CardGame.instance.getCurrentPlayer().getPhase(Phases.COMBAT);
           thisCombat.setEbs(new OneTimeExecuteBattleStrategy(thisCombat.getEbs(), new PreventAllDamageStrategy()));
        }
    }
    @Override
    public Effect getEffect(Player owner) {
        return new DarknessEffect(owner, this);
    }

    @Override
    public String name() {
        return "Darkness";
    }

    @Override
    public String type() {
        return "Instant";
    }

    @Override
    public String ruleText() {
        return name() + " Prevent all damage that would be dealt this turn.";
    }
@Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    
    @Override
    public boolean isInstant() {
        return true;
    }

    
}
