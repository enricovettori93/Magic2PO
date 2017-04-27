/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.target.Target;
import java.util.ArrayList;

/**
 *
 * @author giaco
 */
public abstract class AbstractEnchantmentTarget extends AbstractEnchantment{
    protected ArrayList<Target> targets;
    
    public AbstractEnchantmentTarget(Player owner) {
        super(owner);
        targets = new ArrayList<>();
    }
    public abstract void setTarget();
    
    
    

    
    
}
