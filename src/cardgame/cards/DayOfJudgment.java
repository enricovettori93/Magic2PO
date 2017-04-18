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
        int j = 0;
        @Override
        public void resolve() {
           // distruggi tutte le creature in campo */
           //   prova effetto: se vedete errori o avete un'idea migliore cambiate pure
           
           // prendere la lista di creature del giocatore - QUELLE GIOCATE? */
           List<Creature> plrcrt = CardGame.instance.getCurrentPlayer().getCreatures();
           // prendere la lista di creature dell'avversario */
           List<Creature> advcrt = CardGame.instance.getCurrentAdversary().getCreatures();
           
           while (i < plrcrt.size()){
               System.out.println("Creature:"+ plrcrt.get(i).name()+" destroyed");
               plrcrt.get(i).remove();
               i++;
           }
        
           while (j < advcrt.size()){
               System.out.println("Creature:"+ plrcrt.get(i).name()+" destroyed");
               /*rimuove la creatura */
               advcrt.get(j).remove();
               j++;
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
    public boolean isInstant() {
        return false;
    }
    
}
