
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
        for (int i=0; i!=5; ++i) deck.add(new Reflexologist());
        for (int i=0; i!=5; ++i) deck.add(new Abduction());
        for (int i=0; i!=5; ++i) deck.add(new BronzeSable());
        for (int i=0; i!=5; ++i) deck.add(new BoilingEarth());
        
        //Creo deck per il giocatore1
        //instance.createDeck(0,deck);
        instance.getPlayer(0).setDeck(deck.iterator());
        //deck.clear();
        //Creo deck per il giocatore2
        //instance.createDeck(1, deck);
        instance.getPlayer(1).setDeck(deck.iterator());
        
        instance.run();
    }
    
    //Signleton and instance access
    public static final CardGame instance = new CardGame();
    
    //Metodo per creare i mazzi
    private void createDeck(int player, ArrayList deck){
        int i,choice;
        CardFactory factory = new CardFactory();
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
                /*AL MOMENTO LA CREAZIONE AVVIENE RANDOMICAMENTE, DA RIMODIFICARE PRIMA DELLA CONSEGNA*/
                System.out.print("Select the card " + i + " di 20 -> ");
                choice = 1 + (int)(Math.random() * 25); 
                //choice = in.nextInt();
                if(choice<1 || choice > 25)
                    System.out.println("Wrong index.");
                else{
                    deck.add(factory.returnCard(choice));
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
    
    private TargetManager targetManager=new TargetManager();
    public TargetManager getTargetManager(){ return targetManager; }
}
