
package cardgame.decorator;

import cardgame.Creature;
import java.util.Stack;

public class DecoratorManagementSystem {
    
    public Stack<AbstractDecorator> dms=new Stack<>();
    
    public DecoratorManagementSystem(Creature c){
        dms.push(new DefaultDecorator(c,c));
    }
    
    public void addDecorator(AbstractDecorator d){
        d.setCreature(dms.peek());
        dms.push(d);
    }
    
    public void removeDecorator(Object launcher){
        Stack<AbstractDecorator> temp=new Stack<>();
        AbstractDecorator tempDeco;
        //Elimino tutti i decoratori riferiti a l
        while(!dms.isEmpty()){
            tempDeco=dms.pop();
            if(tempDeco.launcher != launcher){
                temp.push(tempDeco);
            }
        }
        //Inserisco il DefaultDecorator
        dms.push(temp.pop());
        //reinserisco i decoratori
        while(!temp.isEmpty()){
            addDecorator(temp.pop());
        }
        
        tempDeco=dms.pop();
        ((PowerUpDecorator)tempDeco).setCreature(dms.peek());
        dms.push(tempDeco);
    }
    
    public AbstractDecorator peek(){
        return dms.peek();
    }
}
