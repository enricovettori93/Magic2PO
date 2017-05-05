
package cardgame;

import cardgame.cards.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;
import cardgame.factory.*;
import cardgame.target.TargetManager;

public class CardGame {

    public static void main(String[] args) {
        //create decks
        ArrayList<Card> deck = new ArrayList<>();
        /* (int i=0; i!=5; ++i) deck.add(new ArgothianEnchantress());
        for (int i=0; i!=5; ++i) deck.add(new Afflict());
        for (int i=0; i!=5; ++i) deck.add(new BronzeSable());
        for (int i=0; i!=5; ++i) deck.add(new BoilingEarth());*/
        
        //Creo deck per il giocatore1
        instance.createDeck(0,deck);
        instance.getPlayer(0).setDeck(deck.iterator());
        deck.clear();
        //Creo deck per il giocatore2
        instance.createDeck(1, deck);
        instance.getPlayer(1).setDeck(deck.iterator());
        deck.clear();
        instance.run();
    }
    
    //Signleton and instance access
    public static final CardGame instance = new CardGame();
    
    //Metodo per creare i mazzi
    private void createDeck(int player, ArrayList deck){
        int i,choice, choice2;
        CardFactory factory = new CardFactory();
        i = 1;
        choice = 0;
        choice2 = 0;
        Scanner in;
        in = new Scanner(System.in);
        System.out.println("Player "+ (player+1) +" deck's creation!");
        System.out.println("Would u like to generate random your deck or insert manually the cards? 0 = Random, 1 = Card Selection");
        do{
            System.out.print("-> ");
            choice2 = in.nextInt();
            if(choice2 != 0 && choice2 != 1)
                System.out.println("Wrong input.");
        }while(choice2 != 0 && choice2!= 1);
        do{
            //Input from keyboard
            if(choice2 == 1){
                factory.printHouseOfCards();
                System.out.print("Select the card " + i + " of 20 -> ");
                do{
                    choice = in.nextInt();
                    if(choice<1 || choice > factory.getNumberOfCards())
                        System.out.println("Wrong index.");
                    else
                        deck.add(factory.returnCard(choice));
                }while(choice < 1 || choice > 25);
            }
            //Random card
            else{
                choice =  1 + (int)(Math.random() * 25);
                deck.add(factory.returnCard(choice));
            } 
            i++;
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
    
    private TargetManager targetManager=new TargetManager();
    public TargetManager getTargetManager(){ return targetManager; }
}
