
package cardgame.factory;
import cardgame.*;
import cardgame.loader.Loader;
import java.util.ArrayList;

public class CardFactory implements Factory{
    ArrayList<Class> houseOfCards;
    public CardFactory(){
        houseOfCards=new Loader().loadFrom("cardgame/cards");
    }
    
    /**
     * Ritorna un istanza della carta scelta
     * @param choice: indice della carta da creare
     * @return : ritorna l'oggetto o di tipo Card
     */
    @Override
    public Card returnCard(int choice) {
        Object o = null;
        try {
            o = houseOfCards.get(choice-1).newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            System.out.println(ex.getMessage());
        }
        return (Card) o;
    }
    
    /**
     * Stampa delle classi presenti nella cartella cardgames/cards
     */
    public void printHouseOfCards(){
        int i=1;
        for(Class c : houseOfCards)
            System.out.println((i++)+" "+c.getSimpleName());
    
    }
    
    /**
     * Ritorna la quantità di carte disponibili
     */
    public int getNumberOfCards(){
        return houseOfCards.size();
    }
}
