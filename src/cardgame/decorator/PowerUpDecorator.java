
package cardgame.decorator;

import cardgame.Creature;
import cardgame.Effect;
import cardgame.Player;
import cardgame.creaturestrategy.CreatureInflictDamageStrategy;
import java.util.List;

/**
 *
 * @author Utente
 */
public class PowerUpDecorator extends AbstractDecorator{

    private int attackPowerUp;
    private int defensePowerUp;
    
    public PowerUpDecorator(Object l,int aPu,int dPu) {
        super(l);
        attackPowerUp=aPu;
        defensePowerUp=dPu;
    }
    
    public int getAttackPowerUp(){
        return attackPowerUp;
    }
    
    public int getDefensePowerUp(){
        return defensePowerUp;
    }

    @Override
    public int getPower() {
        return creature.getPower();
    }

    @Override
    public int getToughness() {
        return creature.getToughness();  
    }

    @Override
    public List<Effect> effects() {
        return creature.effects();
    }

    @Override
    public List<Effect> avaliableEffects() {
        return creature.avaliableEffects();
    }

    @Override
    public String name() {
        return "PowerUpDecorator:"+creature.name();
    }

    @Override
    public int getPowerDecorated() {
        return attackPowerUp+creature.getPowerDecorated();
    }

    @Override
    public int getToughnessDecorated() {
        return defensePowerUp+creature.getToughnessDecorated();  
    }
    
    public void increaseDamageLeft(int val){
        creature.increaseDamageLeft(val);
    }

    @Override
    public int getDamageLeft() {
        return creature.getDamageLeft();
    }
    
    public void setCreature(Creature c){
        creature=c;
        creature.increaseDamageLeft(defensePowerUp);
    }

    @Override
    public void setOwner(Player owner) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Player getOwner() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCids(CreatureInflictDamageStrategy cids) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public int getPowerLeft() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void decreasePowerLeft(int val) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
