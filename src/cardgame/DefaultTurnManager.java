
package cardgame;

public class DefaultTurnManager implements TurnManager {
    private final Player[] Players;
    int currentPlayerIdx=1;
    
    public DefaultTurnManager(Player[] p) { Players=p; }
    
    @Override
    public Player getCurrentPlayer() { return Players[currentPlayerIdx]; }
    
    @Override
    public Player getCurrentAdversary() { return Players[(currentPlayerIdx+1)%2]; }
    
    @Override
    public Player nextPlayer() { 
        currentPlayerIdx = (currentPlayerIdx+1)%2;
        return getCurrentPlayer();
    }
}
