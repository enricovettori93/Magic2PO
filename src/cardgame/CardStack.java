
package cardgame;

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
    
    public ArrayList<Effect> getAllEffect(){
        ArrayList<Effect> temp=new ArrayList<>();
        for(Effect e : stack){
            temp.add(e);
        }
        return temp;
    }
}
