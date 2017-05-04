
package cardgame.cards;

import cardgame.*;
import cardgame.target.*;

public class AuraBlast implements Card{
    
    private class AuraBlastEffect extends AbstractCardEffectTarget {
        //costruttore
        
        public AuraBlastEffect(Player p, Card c) { 
            super(p,c);
        }

        @Override
        public boolean play() {
            try{
                setTarget();
                return super.play();
            }
            catch(Exception e){
                System.out.println("[AURA BLAST] No target avaiable.");
                return false;
            }
        }
       
        @Override
        public void setTarget(){
            targets.add(CardGame.instance.getTargetManager().getTarget(TargetManager.ENCHANTMENT_TARGET));
        }
        
        @Override
        public void resolve() {
            //destroy target
            if(targets.size()>0){
                if(targets.get(0) instanceof PermanentTarget){
                    ((PermanentTarget)targets.get(0)).getTargetOwner().getEnchantments().remove((Enchantment)targets.get(0).getTarget());
                }
                if(targets.get(0) instanceof CardTarget){
                    CardGame.instance.getStack().remove((Effect)targets.get(0).getTarget());
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
