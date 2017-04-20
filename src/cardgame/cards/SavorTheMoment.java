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
import cardgame.DefaultPhaseManager;
import cardgame.Effect;
import cardgame.Phase;
import cardgame.Phases;
import cardgame.Player;
import cardgame.SkipPhase;
import java.util.ArrayDeque;
import java.util.Scanner;

/**
 *
 * @author Enrico
 */
public class SavorTheMoment implements Card {
    private class SavorTheMomentEffect extends AbstractCardEffect{

        SavorTheMomentEffect(Player p, Card c) { super(p,c); }
        @Override
        public void resolve(){
            CardGame.instance.getCurrentPlayer().setPhase(Phases.END,new SkipPhase(Phases.END));
            CardGame.instance.getCurrentPlayer().setPhase(Phases.DRAW, new DefaultDrawPhase());
            CardGame.instance.getCurrentPlayer().setPhase(Phases.COMBAT, new DefaultCombatPhase());
            CardGame.instance.getCurrentPlayer().setPhase(Phases.MAIN, new DefaultMainPhase());
            CardGame.instance.getCurrentPlayer().setPhase(Phases.END, new DefaultEndPhase());
            //CardGame.instance.getCurrentPlayer().setPhase(Phases.NULL, new ArrayDeque<Phase>());
        }
    }
    @Override
    public Effect getEffect(Player owner) {
        return new SavorTheMomentEffect(owner, this);
    }

    @Override
    public String name() {
        return "Savor the moment";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String ruleText() {
        return "Take an extra turn after this one. Skip the untap step of that turn.";
    }
    
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    
    @Override
    public boolean isInstant() {
        return false;
    }
}
