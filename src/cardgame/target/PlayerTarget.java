package cardgame.target;

import cardgame.Player;

public class PlayerTarget implements Target{
    
    Player target;
    
    public PlayerTarget(Player target){
        this.target=target;
    }
    
    @Override
    public Player getTarget() {
        return target;
    }
}
