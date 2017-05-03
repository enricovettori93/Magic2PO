
package cardgame.target;

import cardgame.AbstractCardEffect;
import cardgame.Player;

public class CardTarget implements Target{

    AbstractCardEffect ace;
    
    public CardTarget(AbstractCardEffect ace){
        this.ace=ace;
    }
    
    @Override
    public Object getTarget() {
        return ace;
    }
    
    public Player getTargetOwner(){
        return ace.getOwner();
    }
    
    public String toString(){
        return ace.getOwner().name()+" "+ace.getCard().name();
    }
}
