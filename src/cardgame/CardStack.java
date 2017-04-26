/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author atorsell
 */
public class CardStack implements Iterable<Effect> {
    private final ArrayDeque<Effect> stack = new ArrayDeque<>();
    
    public Iterator<Effect> iterator() { return stack.iterator(); }
    
    public void add(Effect e) {
        stack.push(e);
        
    }
    
    public void remove(Effect e) { stack.remove(e); }
    
    public void resolve() {
        while(!stack.isEmpty()) { 
            Effect e = stack.pop();
            System.out.println("Stack: resolving " + e);
            e.resolve();
        }
    }
    
    public ArrayList<AbstractCardEffectTarget> getALSingleTargets(){
        ArrayList<AbstractCardEffectTarget> effectTargets = new ArrayList<>();
        for(Effect e : stack){
            if(e instanceof AbstractCardEffectTarget){
                if(((AbstractCardEffectTarget) e).targets.size()==1) //target singolo
                    effectTargets.add((AbstractCardEffectTarget)e);
            }
        }
        return effectTargets;
    }
    
}
