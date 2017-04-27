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
import cardgame.Player;
import java.util.Scanner;

/**
 *
 * @author giaco
 */
public class VolcanicHammer implements Card{
    
    private class VolcanicHammerEffect extends AbstractCardEffect {
        public VolcanicHammerEffect(Player p, Card c) { super(p,c); }
        @Override
        //MANCA METODO RESOLVE
        public void resolve() {
            int choice,i;
            choice = 0;
            Scanner input = new Scanner(System.in);
            if(CardGame.instance.getCurrentPlayer().getCreatures().isEmpty() && CardGame.instance.getCurrentAdversary().getCreatures().isEmpty()){
                System.out.println("Would you like to damage youselves or the opponent? 0 = yourselves, 1 = adversaty");
                do{
                    choice = input.nextInt();
                }while(choice != 0 && choice != 1);
                if(choice == 0)
                    CardGame.instance.getCurrentPlayer().inflictDamage(3);
                else
                    CardGame.instance.getCurrentAdversary().inflictDamage(3);
            }
            else{
                System.out.println("Would you like to damage one player or all the creature? 0 = player, 1 = creature");
                do{
                    choice = input.nextInt();
                }while(choice != 0 && choice != 1);
                if(choice == 0){
                    System.out.println("Would you like to damage youselves or the opponent? 0 = yourselves, 1 = adversaty");
                    do{
                        choice = input.nextInt();
                    }while(choice != 0 && choice != 1);
                    if(choice == 0)
                        CardGame.instance.getCurrentPlayer().inflictDamage(3);
                    else
                        CardGame.instance.getCurrentAdversary().inflictDamage(3);
                }
                else{
                    System.out.println("Inflicting 3 damage to all creature in the field.");
                    for(i = 0;i < CardGame.instance.getCurrentPlayer().getCreatures().size();i++)
                        CardGame.instance.getCurrentPlayer().getCreatures().get(i).inflictDamage(3);
                    for(i = 0;i < CardGame.instance.getCurrentAdversary().getCreatures().size();i++)
                        CardGame.instance.getCurrentAdversary().getCreatures().get(i).inflictDamage(3);
                }
            }
        }
    }
    @Override
    public Effect getEffect(Player owner) {
        return new VolcanicHammerEffect(owner, this);
    }

    @Override
    public String name() {
       return "Volcanic Hammer";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String ruleText() {
        return name() + " Volcanic Hammer deals 3 damage to any one creature or player. Fire finds its form in th heat of the forge";
    }
    @Override
    public String toString(){
        return name() + " (" + type() + ") [" + ruleText() +"]";
    }
    @Override
    public boolean isInstant() {
        return false;
    }
    
}
