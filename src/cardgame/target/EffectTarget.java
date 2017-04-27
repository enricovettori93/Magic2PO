/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.target;

import cardgame.Effect;
import cardgame.Permanent;
import cardgame.Player;

/**
 *
 * @author giaco
 */
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
