/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

/**
 *
 * @author Utente
 */
public abstract class Decorator{
    
    private AbstractCreature decoCreature;
    private Object ownerEffect;
    
    public Decorator(AbstractCreature creature){  decoCreature=creature;  }
    
    public void setCreature(AbstractCreature creature){
        decoCreature=creature;
    }
    
    public AbstractCreature getCreature(){
        return decoCreature;
    }
    
    public void setOwnerEffect(Object owner){
        ownerEffect=owner;
    }
    
    public Object getOwnerEffect(){
        return ownerEffect;
    }
    
    public int getPower(){
        return decoCreature.getPower();
    }
    
    public int getToughness(){
        return decoCreature.getToughness();
    }
    
    public String name(){
        return decoCreature.name();
    }
    
    public void inflictDamage(int dmg) {
        decoCreature.inflictDamage(dmg);
    }
}
