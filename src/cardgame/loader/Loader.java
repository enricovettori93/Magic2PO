/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.loader;

import cardgame.Card;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author giaco
 */
public class Loader {
    /**
     * load ritorna un ArrayList di Classi
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
        String[] lines = txt.split("\r\n|\r|\n");
        ArrayList<Class> listOfClasses = new ArrayList<>();
        for(String s : lines)
            if(!s.contains("$")){ //rimuovo le classi che non sono nomi corretti di carte
                //rimuovo .class finale
                String correctClass = s.replace(".class", "");
                try {
                    listOfClasses.add(Class.forName("cardgame.cards."+correctClass));
                } catch (ClassNotFoundException ex) {
                    ex.getMessage();
                }
        }
        //test if Class if Card class
        for(Class c :listOfClasses){
            if(!testIsCard(c))
                listOfClasses.remove(c);
        }
        return listOfClasses;
    }
    
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