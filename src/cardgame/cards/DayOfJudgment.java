/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.*;
import java.util.List;


public class DayOfJudgment implements Card{

    private class DayOfJudgmentEffect extends AbstractCardEffect{

        DayOfJudgmentEffect(Player p, Card c) { super(p,c); }
        
        int i = 0;
        @Override
        public void resolve() {
           // distruggi tutte le creature in campo
           CardGame.instance.getCurrentPlayer().getCreatures().clear();
           CardGame.instance.getCurrentAdversary().getCreatures().clear();
        }
    }
    @Override
    public Effect getEffect(Player owner) {
        return new DayOfJudgmentEffect(owner, this);
    }

    @Override
    public String name() {
        return "Day Of Judgment";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String ruleText() {
        return "Day of Judgment destroy all creatures";
    }
    
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    
    @Override
    public boolean isInstant() {
        return false;
    }
    
}
