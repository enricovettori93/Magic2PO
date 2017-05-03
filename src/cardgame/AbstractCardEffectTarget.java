
package cardgame;

import cardgame.target.PermanentTarget;
import cardgame.target.PlayerTarget;
import cardgame.target.Target;
import java.util.ArrayList;

public abstract class AbstractCardEffectTarget extends AbstractCardEffect{
    protected ArrayList<Target> targets;
    
    public AbstractCardEffectTarget(Player p, Card c) {
        super(p, c);
        targets = new ArrayList<>();
    }
    public void setTarget(){
        targets.clear();
    }
            
    public void printTarget(){
        for(Target i : targets){
            if(i instanceof PermanentTarget)
                System.out.println(((Permanent)((PermanentTarget)i).getTarget()).name());
            else if(i instanceof PlayerTarget)
                System.out.println(((Player)((PlayerTarget)i).getTarget()).name());
        }
    }
    public String toString(){
        String out = new String();
        for(Target i : targets){
            out+=i.toString()+",";
        }
        return out;
    }
    
    
}
