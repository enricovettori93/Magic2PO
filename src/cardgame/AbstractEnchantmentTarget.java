
package cardgame;

import cardgame.target.Target;
import java.util.ArrayList;

public abstract class AbstractEnchantmentTarget extends AbstractEnchantment{
    protected ArrayList<Target> targets;
    
    public AbstractEnchantmentTarget(Player owner) {
        super(owner);
        targets = new ArrayList<>();
    }
    public abstract void setTarget();
    
    
    

    
    
}
