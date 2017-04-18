/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.*;
import java.util.List;
/**
 *
 * @author Utente
 */
public class BoilingEarth implements Card{

    private class BoilingEarthEffect extends AbstractCardEffect{

        BoilingEarthEffect(Player p, Card c) { super(p,c); }
        
        @Override
        public void resolve() {
            //prova effetto: se vedete errori o avete un'idea migliore cambiate pure
            int i=0;
            //infliggo danni alle creature dell'avversario
            List<Creature> temp=CardGame.instance.getCurrentAdversary().getCreatures();
            while(i<temp.size()){
                System.out.println("Creature:"+temp.get(i).name()+" get 3 damages: "+temp.get(i).getToughness()+" -> "+(temp.get(i).getToughness()-3));
                if(temp.get(i).getToughness() >3){
                    temp.get(i).inflictDamage(3);
                    i++;
                }else
                    temp.get(i).inflictDamage(3);
            }
            //infliggo alle creature del giocatore corrente
            temp=CardGame.instance.getCurrentPlayer().getCreatures();
            while(i<temp.size()){
                System.out.println("Creature:"+temp.get(i).name()+" get 3 damages: "+temp.get(i).getToughness()+" -> "+(temp.get(i).getToughness()-3));
                if(temp.get(i).getToughness() >3){
                    temp.get(i).inflictDamage(3);
                    i++;
                }else
                    temp.get(i).inflictDamage(3);
            }
        }
    
    }
    
    @Override
    public Effect getEffect(Player owner) {
        return new BoilingEarthEffect(owner, this);
    }

    @Override
    public String name() {
        return "Boiling Earth";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String ruleText() {
        return "Boiling Earth deals 1 damage to each creature";
    }

    @Override
    public boolean isInstant() {
        return false;
    }
    
}
