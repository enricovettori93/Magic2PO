/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.factory;
import cardgame.*;
import cardgame.cards.*;
/**
 *
 * @author Enrico
 */
public interface Factory {
    public Card returnCard(int choice);
}
