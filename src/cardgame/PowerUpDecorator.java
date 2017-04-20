/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.List;

/**
 *
 * @author Utente
 */
public class PowerUpDecorator extends Decorator{

    private int attackPowerUp;
    private int defensePowerUp;
    private Decorator deco;
    
    public PowerUpDecorator(Decorator deco,Object obj,int attackPowerUp,int defensePowerUp) {
        super(deco.getCreature());
        this.deco=deco;
        this.deco.setOwnerEffect(obj);
        this.attackPowerUp=attackPowerUp;
        this.defensePowerUp=defensePowerUp;
    }
    
    @Override
    public int getPower(){
        return attackPowerUp+deco.getPower();
    }
    
    @Override
    public int getToughness(){
        return deco.getToughness()+defensePowerUp;
    }
    
    @Override
    public String name(){
        return deco.name();
    }
    
    public void inflictDamage(int dmg) {
        deco.inflictDamage(dmg-defensePowerUp);
    }
}
