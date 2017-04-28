
package cardgame.cards;

import cardgame.AbstractCardEffectTarget;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Effect;
import cardgame.Enchantment;
import cardgame.target.PermanentTarget;
import cardgame.Player;
import cardgame.target.Target;
import cardgame.target.TargetManager;

public class AuraBlast implements Card{
    
    private class AuraBlastEffect extends AbstractCardEffectTarget {
        //costruttore
        
        public AuraBlastEffect(Player p, Card c) { 
            super(p,c);
        }

        @Override
        public boolean play() {
            setTarget();
            return super.play(); //To change body of generated methods, choose Tools | Templates.
        }
       
        @Override
        public void setTarget(){
            targets.add(CardGame.instance.getTargetManager().getTarget(TargetManager.ENCHANTMENT_ON_FIELD_TARGET));
        }
        
        @Override
        public void resolve() {
            //destroy target
            if(targets.size()>0){
                for(Target t : targets){
                    ((PermanentTarget)t).getTargetOwner().getEnchantments().remove((Enchantment)t.getTarget());
                }
            }
            //draw a card
            owner.draw();
        }
        
    }
    @Override
    public Effect getEffect(Player owner) {
        //get effect ritorna un nuovo effetto -- 
        return new AuraBlastEffect(owner, this);  
    }

    @Override
    public String name() {
       return "Aura Blast";
    }

    @Override
    public String type() {
        return "Instant";
    }

    @Override
    public String ruleText() {
        return name() + " destroy target enchantment. Draw a card.";
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
