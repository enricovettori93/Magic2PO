/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;

import cardgame.cards.*;
/**
 *
 * @author atorsell
 */
public class CardGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //create decks
        ArrayList<Card> deck = new ArrayList<>();
        /*for (int i=0; i!=5; ++i) deck.add(new Reflexologist());
        for (int i=0; i!=5; ++i) deck.add(new FriendlyEnvironment());
        Aggiunta carte per prova
        for (int i=0; i!=5; ++i) deck.add(new BronzeSable());
        for (int i=0; i!=5; ++i) deck.add(new AncestralMask());*/
        
        //Creo deck per il giocatore1
        instance.createDeck(0,deck);
        instance.getPlayer(0).setDeck(deck.iterator());
        deck.clear();
        //Creo deck per il giocatore2
        instance.createDeck(1, deck);
        instance.getPlayer(1).setDeck(deck.iterator());
        
        instance.run();
    }
    
    //Signleton and instance access
    public static final CardGame instance = new CardGame();
    
    //Metodo per creare i mazzi
    private void createDeck(int player, ArrayList deck){
        int i,choice;
        i = 1;
        Scanner in;
        in = new Scanner(System.in);
        if(player == 0)
            System.out.println("Player 1 deck's creation");
        else
            System.out.println("Player 2 deck's creation");
        
        System.out.println("Select the cards that you would like to insert into deck");
        System.out.println("1 - Abduction");
        System.out.println("2 - Aether Barrier");
        System.out.println("3 - Aether Flash");
        System.out.println("4 - Afflict");
        System.out.println("5 - Aggressive Urge");
        System.out.println("6 - Ancestral Mask");
        System.out.println("7 - Argothian Enchantment");
        System.out.println("8 - Aura Blast");
        System.out.println("9 - Benevolent Ancestor");
        System.out.println("10 - Boiling Earth");
        System.out.println("11 - Bronze Sable");
        System.out.println("12 - Calming Verse");
        System.out.println("13 - Cancel");
        System.out.println("14 - Darkness");
        System.out.println("15 - Day of Judgment");
        System.out.println("16 - Deflection");
        System.out.println("17 - False Peace");
        System.out.println("18 - Fatigue");
        System.out.println("19 - Norwood Ranger");
        System.out.println("20 - Savor The Moment");
        System.out.println("21 - Volcanic Hammer");
        System.out.println("22 - World at War");
        System.out.println("23 - Reflexologist");
        System.out.println("24 - Homeophaty");
        System.out.println("25 - Friendly Envirorment");
        do{
            do{
                System.out.print("Select the card " + i + " di 20 -> ");
                choice = in.nextInt();
                if(choice<1 || choice > 25)
                    System.out.println("Wrong index.");
                else{
                    switch (choice){
                        case 1:
                            //deck.add(new Abduction());
                            System.out.println("Added Abduction");
                            break;
                        case 2:
                            deck.add(new AetherBarrier());
                            System.out.println("Added AetherBarrier");
                            break;
                        case 3:
                            deck.add(new AetherFlash());
                            System.out.println("Added AetherFlash");
                            break;
                        case 4:
                            //deck.add(new Afflict());
                            System.out.println("Added Afflict");
                            break;
                        case 5:
                            deck.add(new AggressiveUrge());
                            System.out.println("Added AggressiveUrge");
                            break;
                        case 6:
                            deck.add(new AncestralMask());
                            System.out.println("Added AncestralMask");
                            break;
                        case 7:
                            //deck.add(new ArgothianEnchantress());
                            System.out.println("Added ArgothianEnchantress");
                            break;
                        case 8:
                            deck.add(new AuraBlast());
                            System.out.println("Added AuraBlast");
                            break;
                        case 9:
                            //deck.add(new BenevolentAncestror());
                            System.out.println("Added BenevolentAncestror");
                            break;
                        case 10:
                            deck.add(new BoilingEarth());
                            System.out.println("Added BoilingEarth");
                            break;
                        case 11:
                            deck.add(new BronzeSable());
                            System.out.println("Added BronzeSable");
                            break;
                        case 12:
                            deck.add(new CalmingVerse());
                            System.out.println("Added CalmingVerse");
                            break;
                        case 13:
                            deck.add(new Cancel());
                            System.out.println("Added Cancel");
                            break;
                        case 14:
                            //deck.add(new Darkness());
                            System.out.println("Added Darkness");
                            break;
                        case 15:
                            deck.add(new DayOfJudgment());
                            System.out.println("Added DayOfJudgment");
                            break;
                        case 16:
                            deck.add(new Deflection());
                            System.out.println("Added Deflection");
                            break;
                        case 17:
                            deck.add(new FalsePeace());
                            System.out.println("Added FalsePeace");
                            break;
                        case 18:
                            deck.add(new Fatigue());
                            System.out.println("Added Fatigue");
                            break;
                        case 19:
                            deck.add(new NorwoodRanger());
                            System.out.println("Added NorwoodRanger");
                            break;
                        case 20:
                            deck.add(new SavorTheMoment());
                            System.out.println("Added SavorTheMoment");
                            break;
                        case 21:
                            deck.add(new VolcanicHammer());
                            System.out.println("Added VolcanicHammer");
                            break;
                        case 22:
                            deck.add(new WorldAtWar());
                            System.out.println("Added WorldAtWar");
                            break;
                        case 23:
                            deck.add(new Reflexologist());
                            System.out.println("Added Reflexologist");
                            break;
                        case 24:
                            deck.add(new Homeopathy());
                            System.out.println("Added Homeopathy");
                            break;
                        case 25:
                            deck.add(new FriendlyEnvironment());
                            System.out.println("Added FriendlyEnvironment");
                            break;
                    }
                    i++;
                }
            }while(choice < 1 || choice > 25);
        }while(i<=20);
    }
    
    //game setup 
    private CardGame() { 
        turnManagerStack.push( new DefaultTurnManager(Players) );
        
        Players[0]=new Player();
        Players[0].setName("Player 1");
        Players[0].setPhase(Phases.DRAW,new SkipPhase(Phases.DRAW));
        
        
        Players[1]=new Player();
        Players[1].setName("Player 2");
    }
    
    //execute game
    public void run() {
        Players[0].getDeck().shuffle();
        Players[1].getDeck().shuffle();
                
        for (int i=0; i!=5; ++i) Players[0].draw();
        for (int i=0; i!=5; ++i) Players[1].draw();
        
        try {
            while (true) { instance.nextPlayer().executeTurn(); }
        } catch(EndOfGame e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    
    // Player and turn management
    private final Player[] Players = new Player[2];
    private final Deque<TurnManager>  turnManagerStack = new ArrayDeque<>();
    public void setTurnManager(TurnManager m) { turnManagerStack.push(m); }
    public void removeTurnManager(TurnManager m) { turnManagerStack.remove(m); }
    
    Player getPlayer(int i) { return Players[i]; }    
    public Player getCurrentPlayer() { return turnManagerStack.peek().getCurrentPlayer(); }
    public Player getCurrentAdversary() { return turnManagerStack.peek().getCurrentAdversary(); }
    Player nextPlayer() { return turnManagerStack.peek().nextPlayer(); }
    
    
    // Stack access
    private final CardStack stack = new CardStack();
    public CardStack getStack() { return stack; }
    
    
    //Trigger access
    private final Triggers triggers=new Triggers();
    public Triggers getTriggers() { return triggers; }
    
    
    //IO resources  to be dropped in final version
    private final Scanner reader = new Scanner(System.in);
    Scanner getScanner() { return reader; }
}
