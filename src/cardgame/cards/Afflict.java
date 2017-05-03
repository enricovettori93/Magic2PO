
package cardgame.cards;

import cardgame.*;
import cardgame.decorator.PowerUpDecorator;

public class Afflict implements Card{
    
    private class AfflictEffect extends AbstractCardEffect {
    
        AbstractCreature CreatureTarget;
        PowerUpDecorator PuD;
        TriggerAction AfflictTriggerAcrion;
        
        
        
        public AfflictEffect (Player p, Card c){
            super(p,c);
            //
        }
        
    @Override
        public boolean play(){
            setTarget();
            return super.play();

        }
        
    @Override
        public void resolve(){
            // Aggiungere decoratore al Target - nell'else perchè è quando si può eseguire tale azione
            if (CreatureTarget.getToughnessDecorated() == 1){
                CreatureTarget.remove();
                // rimuovo la carta perchè la vita va a 0
            }
            else {
                // la toughness della carta decorata cambia
                PuD = new PowerUpDecorator(this, -1, -1);
                CreatureTarget.addDecorator(PuD);
            }
            
            
            
            // Aggiungere Trigger su END_PHASE - fino alla fine del turno esiste questo effetto.
        }
        
        public void setTarget(){
            // Bisogna far scegliere la carta
        }
    }
    
    @Override
    public Effect getEffect(Player owner) { 
        return new AfflictEffect(owner, this); 
    }
    
    
    @Override
    public String name() {
       return "Afflict";
    }

    @Override
    public String type() {
        return "Instant";
    }

    @Override
    public String ruleText() {
        return name() + " Target creature gets -1/-1 until end of turn";
    }
    @Override
    public String toString(){
        return name() + " (" + type() + ") [" + ruleText() +"]";
    }
    @Override
    public boolean isInstant() {
        return true;
    }
        

    
}
