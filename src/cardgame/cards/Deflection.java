package cardgame.cards;

import cardgame.*;
import cardgame.target.TargetManager;

public class Deflection implements Card{
    
    private class DeflectionEffect extends AbstractCardEffectTarget {
        
        public DeflectionEffect(Player p, Card c) { 
            super(p,c);
        }

        @Override
        public boolean play() {
            try{
                setTarget();
                return super.play();
            }
            catch(Exception e){
                System.out.println("[ABDUCTION] No target avaiable.");
                return false;
            }
        }
       
        @Override
        public void setTarget(){
            targets.add(CardGame.instance.getTargetManager().getTarget(TargetManager.STACK_TARGETSPELL_TARGET));          
        }
        @Override
        public void resolve() {
            ((AbstractCardEffectTarget)targets.get(0).getTarget()).setTarget();
        }
        
    }
    @Override
    public Effect getEffect(Player owner) {
        //get effect ritorna un nuovo effetto -- 
        return new DeflectionEffect(owner, this);  
    }

    @Override
    public String name() {
       return "Deflection";
    }

    @Override
    public String type() {
        return "Instant";
    }

    @Override
    public String ruleText() {
        return name() + " Change the target of target spell with a single target.";
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
