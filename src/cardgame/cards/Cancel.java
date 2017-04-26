/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCardEffectTarget;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Effect;
import cardgame.Player;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Ilaria
 */
public class Cancel implements Card {

    @Override
    public Effect getEffect(Player owner) {
        return new CancelEffect(owner, this);
    }
    
    private class CancelEffect extends AbstractCardEffectTarget {

        AbstractCardEffectTarget targetSpell;

        public CancelEffect(Player p, Card c) {
            super(p, c);
        }

        public void setTarget(){
            Scanner input = new Scanner(System.in);
            int choice;
            ArrayList<AbstractCardEffectTarget> effects = CardGame.instance.getStack().getALSingleTargets();
            if(effects.size()>0){
                do{
                    int i = 1;
                    System.out.println( "[CANCEL] SELECT TARGET (from effect stack):");
                    for(AbstractCardEffectTarget e : effects){
                        System.out.println(i+". "+e.getCard().toString()+" [TARGET: "+e.toString()+"]");
                        i++;
                    }
                    choice = input.nextInt();
                }while(choice < 0 && choice > effects.size());
                targetSpell = effects.get(choice-1);



            }
            else{
                System.out.println("[CANCEL] Nothing to select... PASS");
            }
        }

        @Override
        public void resolve() {
            if(targetSpell!=null)
                targetSpell = new AbstractCardEffectTarget(owner, card) {
                    @Override
                    public void setTarget() {
                        // Non fa nulla, è stato neutralizzato
                    }

                    @Override
                    public void resolve() {
                        // Non fa nulla, è stato neutralizzato
                    }
                };
        }

    }    

        
    @Override
    public String name() {
       return "Cancel";
    }

    @Override
    public String type() {
        return "Instant";
    }

    @Override
    public String ruleText() {
        return name() + " Counter target spell.";
    }
    @Override
    public String toString(){
        return name() + " (" + type() + ") [" + ruleText() +"]";
    }
    @Override
    public boolean isInstant() {
        return true;
    }
    
}
