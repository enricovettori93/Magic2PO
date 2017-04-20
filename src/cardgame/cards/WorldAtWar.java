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
import cardgame.DefaultDrawPhase;
import cardgame.DefaultEndPhase;
import cardgame.DefaultMainPhase;
import cardgame.DefaultUntapPhase;
import cardgame.Effect;
import cardgame.Phases;
import cardgame.Player;
import cardgame.SkipPhase;

/**
 *
 * @author Enrico
 */
public class WorldAtWar implements Card {
    private class WorldAtWarEffect extends AbstractCardEffect{

        WorldAtWarEffect(Player p, Card c) { super(p,c); }
        @Override
        public void resolve(){
            CardGame.instance.getCurrentPlayer().setPhase(Phases.END,new SkipPhase(Phases.END));
            CardGame.instance.getCurrentPlayer().setPhase(Phases.NULL,new SkipPhase(Phases.NULL));
            CardGame.instance.getCurrentPlayer().setPhase(Phases.DRAW, new SkipPhase(Phases.DRAW));
            CardGame.instance.getCurrentPlayer().setPhase(Phases.UNTAP,new DefaultUntapPhase());
            CardGame.instance.getCurrentPlayer().setPhase(Phases.COMBAT, new DefaultCombatPhase());
            CardGame.instance.getCurrentPlayer().setPhase(Phases.MAIN, new DefaultMainPhase());
            CardGame.instance.getCurrentPlayer().setPhase(Phases.END, new DefaultEndPhase());
        }
    }
    @Override
    public Effect getEffect(Player owner) {
        return new WorldAtWarEffect(owner, this);
    }

    @Override
    public String name() {
        return "World at War";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String ruleText() {
        return "After the first postcombat main phase this turn, there's an addictional combat phase followed by an additional main phase. At the beginning of that combat, untap all creatures that attacked this turn";
    }
    
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    
    @Override
    public boolean isInstant() {
        return false;
    }
}
