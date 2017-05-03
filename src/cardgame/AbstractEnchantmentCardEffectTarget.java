
package cardgame;

import cardgame.target.*;
import java.util.ArrayList;

public abstract class AbstractEnchantmentCardEffectTarget extends AbstractEnchantmentCardEffect{
        protected ArrayList<Target> targets;
    
    public AbstractEnchantmentCardEffectTarget(Player p, Card c) {
        super(p, c);
        targets = new ArrayList<>();
    }
    public abstract void setTarget();
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
