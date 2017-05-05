
package cardgame;

import cardgame.target.EffectTarget;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

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
    /**
     * ritorna un ArrayList contente gli effetti che hannno un target dello stack
     * @return 
     */
    public ArrayList<EffectTarget> getALSingleTargets(){
        ArrayList<EffectTarget> effectTargets = new ArrayList<>();
        for(Effect e : stack){
            if(e instanceof AbstractCardEffectTarget || e instanceof AbstractEnchantmentCardEffectTarget)
                effectTargets.add(new EffectTarget(e));
        }
        return effectTargets;
    }
    /**
     * ritorna tutti gli effetti dello stack sottoforma di ArrayList
     * @return 
     */
    public ArrayList<Effect> getAllEffect(){
        ArrayList<Effect> temp=new ArrayList<>();
        for(Effect e : stack){
            temp.add(e);
        }
        return temp;
    }
}
