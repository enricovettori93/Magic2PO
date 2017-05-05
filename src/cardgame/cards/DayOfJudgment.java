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
        @Override
        public void resolve() {
           /**
            * Itero per entrambe le liste di creature dei 2 giocatori per andare a rimuoverli
            */
           List <Creature> app = CardGame.instance.getCurrentPlayer().getCreatures();
           for(int i = 0;i<app.size();i++){
               app.get(i).remove();
           }
           app.clear();
           app = CardGame.instance.getCurrentAdversary().getCreatures();
           for(int i = 0;i<app.size();i++){
               app.get(i).remove();
           }
           app.clear();
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
