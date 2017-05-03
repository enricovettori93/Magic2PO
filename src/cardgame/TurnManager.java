
package cardgame;

public interface TurnManager {
    Player getCurrentPlayer();
    
    Player getCurrentAdversary();
    
    Player nextPlayer();
}
