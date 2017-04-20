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
 * @author Enrico
 */
public class AggressiveUrge implements Card {
    private class AggressiveUrgeEffect extends AbstractCardEffect {
        public AggressiveUrgeEffect(Player p, Card c) { super(p,c); }
        @Override
        //MANCA IMPLEMENTAZIONE METODO RESOLVE
        public void resolve() {
            int powerup = 0;
            int in;
            Scanner input = new Scanner(System.in);
            System.out.println("Player's creature");
            System.out.println("" + CardGame.instance.getCurrentPlayer().getCreatures());
            System.out.println("Adversary's creature");
            System.out.println("" + CardGame.instance.getCurrentAdversary().getCreatures());
            System.out.println("Select player creature target -> 0 = player, 1 = adversary");
            do{
                in =  input.nextInt();
            }while(in != 0 || in != 1);
            System.out.println("Select creature");
            if(in == 0){
                System.out.println("" + CardGame.instance.getCurrentPlayer().getCreatures());
                do{
                    in = input.nextInt();
                }while(in < 0 || in > CardGame.instance.getCurrentPlayer().getCreatures().size());
                //DA DEFINIRE DECORATORE PER AUMENTARE ATTACCO E DIFESA DELLA CREATURA
                CardGame.instance.getCurrentPlayer().getCreatures().get(in+1).DECORATOREAUMENTAATKDEF
            }
            else{
                System.out.println("" + CardGame.instance.getCurrentAdversary().getCreatures());
                do{
                    in = input.nextInt();
                }while(in < 0 || in > CardGame.instance.getCurrentAdversary().getCreatures().size());
                //DA DEFINIRE DECORATORE PER AUMENTARE ATTACCO E DIFESA DELLA CREATURA
                CardGame.instance.getCurrentAdversary().getCreatures().get(in+1).DECORATOREAUMENTAATKDEF
            }
        }
    }

    @Override
    public Effect getEffect(Player owner) { 
        return new AggressiveUrgeEffect(owner, this); 
    }
    
    @Override
    public String name() { return "Aggressive Urge"; }
    @Override
    public String type() { return "Instant"; }
    @Override
    public String ruleText() { return name() + " Target creature gets +1/+1 untile end of turn"; }
    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    @Override
    public boolean isInstant() { return true; }
}
