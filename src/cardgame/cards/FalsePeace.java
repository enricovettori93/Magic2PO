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
import cardgame.Phases;
import cardgame.Player;
import cardgame.SkipPhase;
import java.util.Scanner;

/**
 *
 * @author Enrico
 */
public class FalsePeace implements Card {
    private class FalsePeaceEffect extends AbstractCardEffect{

        FalsePeaceEffect(Player p, Card c) { super(p,c); }
        @Override
        public void resolve() {
           int powerup = 0;
            int in;
            Scanner input = new Scanner(System.in);
            System.out.println("Select player target -> 0 = player, 1 = adversary");
            do{
                in =  input.nextInt();
            }while(in != 0 && in != 1);
            if(in == 0){
                CardGame.instance.getCurrentPlayer().setPhase(Phases.COMBAT,new SkipPhase(Phases.COMBAT));
            }
            else{
                CardGame.instance.getCurrentAdversary().setPhase(Phases.COMBAT,new SkipPhase(Phases.COMBAT));
            }
        }
    }
    @Override
    public Effect getEffect(Player owner) {
        return new FalsePeaceEffect(owner, this);
    }

    @Override
    public String name() {
        return "False Peace";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String ruleText() {
        return "Target player skips his next combat phase.";
    }
    
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    
    @Override
    public boolean isInstant() {
        return false;
    }
    
}
