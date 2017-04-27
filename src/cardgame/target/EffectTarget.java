
package cardgame.target;

import cardgame.Effect;

public class EffectTarget implements Target{
    Effect target;
    public EffectTarget(Effect target){
        this.target=target;
    }
    
    @Override
    public Object getTarget() {
        return target;
    }
    
}
