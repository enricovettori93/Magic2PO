/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.ArrayList;

/**
 *
 * @author giaco
 */
public abstract class AbstractCardEffectTarget extends AbstractCardEffect{
    protected ArrayList<Target> targets;
    
    public AbstractCardEffectTarget(Player p, Card c) {
        super(p, c);
        targets = new ArrayList<>();
    }
    public abstract void setTarget();
       
    
    
}
