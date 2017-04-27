
package cardgame.target;

import cardgame.Permanent;
import cardgame.Player;

public class PermanentTarget implements Target{
    
    Player targetOwner;
    Permanent target;
    
    public PermanentTarget(Player owner, Permanent target){
        this.target=target;
        this.targetOwner=owner;
    }
    
    @Override
    public Object getTarget() {
        return target;
    }
    
    public Player getTargetOwner(){
        return targetOwner;
    }
    
    public String toString(){
        return target.name();
    }
}
