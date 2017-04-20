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
           // prendere la lista di creature del giocatore
           List<Creature> app = CardGame.instance.getCurrentPlayer().getCreatures();
           for (i = 0;i < app.size(); i ++){
               System.out.println("Creature:"+ app.get(i).name()+" destroyed");
               CardGame.instance.getCurrentPlayer().destroy(CardGame.instance.getCurrentPlayer().getCreatures().get(i));
           }
           // prendere la lista di creature dell'avversario
           app = CardGame.instance.getCurrentAdversary().getCreatures();
           for (i = 0;i < app.size(); i ++){
               System.out.println("Creature:"+ app.get(i).name()+" destroyed");
               CardGame.instance.getCurrentAdversary().destroy(CardGame.instance.getCurrentAdversary().getCreatures().get(i));
           }
           
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
