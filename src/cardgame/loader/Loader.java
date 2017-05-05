
package cardgame.loader;

import cardgame.Card;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 *
 * @author giaco
 */
public class Loader {
    /**
     * load ritorna un ArrayList di Classi -> le classi ritornate permettono di istanziare delle carte
     * res è il path dove sono situate le classi
     * @return
     */
    public ArrayList<Class> loadFrom(String res) {
           InputStream result = (InputStream) getClass().getClassLoader(). getResourceAsStream(res);
        byte [] b = new byte[256];
        int val = 0;
        String txt=new String();
        do {
            try {
                val = result.read(b);
                if (val > 0) {
                    String readed =new String(b, 0, val);
                    txt += readed; 
                }      } catch (IOException ex) {
                 System.out.println(ex.getMessage());
            }
        } while (val > -1);
        String[] lines = txt.split("\r\n|\r|\n"); //prendo le righe dal testo
        ArrayList<Class> listOfClasses = new ArrayList<>(); //creo array list di Classi
        for(String s : lines) //per ogni Stringa su lines, se contiene $ nel nome della Classe vuol dire che non è una carta
            if(!s.contains("$")){ //rimuovo le classi che non sono nomi corretti di carte
                //rimuovo .class finale
                String correctClass = s.replace(".class", "");
                try {
                    listOfClasses.add(Class.forName("cardgame.cards."+correctClass)); //aggiungo la classe alla lista
                } catch (ClassNotFoundException ex) {
                    ex.getMessage();
                }
        }
        //test se la listOfClasses contiene effettivamente delle Card Class
        for(Class c :listOfClasses){
            if(!testIsCard(c))
                listOfClasses.remove(c); //rimuovo se non lo sono
        }
        return listOfClasses;
    }
    /**
     * testo se l'instanza di una classe è una Carta (fatto per evitare problemi al runtime)
     * @param c
     * @return 
     */
    public boolean testIsCard(Class c){
        try {
            if(c.newInstance() instanceof Card)
                return true;
                } catch (InstantiationException | IllegalAccessException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
}