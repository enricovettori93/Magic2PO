/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.Stack;

/**
 *
 * @author Utente
 */
public class StructDecoratorCreature {
    
    AbstractCreature creature;
    private Stack<Decorator> stackDecorator=new Stack<>();
    
    public StructDecoratorCreature(AbstractCreature creature){
        this.creature=creature;
        stackDecorator.add(new Decorator(creature) {  });
    }
    
    public void add(Decorator decorator){
        stackDecorator.add(decorator);
    }
    
    public void remove(Object o){
        if(!isEmpty()){
            int i;
            boolean flag=true;
            for(i=1;i<stackDecorator.size();i++){
                if(stackDecorator.get(i).getOwnerEffect() == o){
                    stackDecorator.remove(i);
                }
            }
        }
    }
    
    public Decorator peek(){
        return stackDecorator.peek();
    }
    
    public void clear(){
        stackDecorator.clear();
        stackDecorator.add(new Decorator(creature) {  });
    }
    
    public boolean isEmpty(){
        if(stackDecorator.size() == 1) 
            return true;
        else
            return false;
    }
    
    
}
