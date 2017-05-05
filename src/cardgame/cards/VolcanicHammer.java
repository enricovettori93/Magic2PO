
package cardgame.cards;

import cardgame.*;
import cardgame.target.PermanentTarget;
import cardgame.target.PlayerTarget;
import cardgame.target.TargetManager;

public class VolcanicHammer implements Card{
    
    private class VolcanicHammerEffect extends AbstractCardEffectTarget {
        public VolcanicHammerEffect(Player p, Card c) { super(p,c); }
        
        @Override
        public boolean play() {
            try{
                setTarget();
                return super.play();
            }
            catch(Exception e){
                System.out.println("[VOLCANIC HAMMER] No target avaiable.");
                return false;
            }
        }
        /**
        * Infliggo il danno o al guidatore selezionato o alla creatura
        */
        @Override
        public void resolve() {
            System.out.print("[VOLCANIC HAMMER] Inflict 3 damages to: ");
            if(targets.get(0) instanceof PlayerTarget){
            System.out.println(((Player)targets.get(0).getTarget()).name());
               ((Player)targets.get(0).getTarget()).inflictDamage(3);
            }
            if(targets.get(0) instanceof PermanentTarget){
            System.out.println(((Creature)targets.get(0).getTarget()).name());
               ((Creature)targets.get(0).getTarget()).inflictDamage(3);
            }
        }

        @Override
        public void setTarget() throws Exception {
            try{
                targets.add(CardGame.instance.getTargetManager().getTarget(TargetManager.CREATURE_OR_PLAYER_TARGET));
            }catch(Exception e){
                throw new Exception(e.getMessage());
            }
        }
    }
    @Override
    public Effect getEffect(Player owner) {
        return new VolcanicHammerEffect(owner, this);
    }

    @Override
    public String name() {
       return "Volcanic Hammer";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String ruleText() {
        return name() + " Volcanic Hammer deals 3 damage to any one creature or player. Fire finds its form in th heat of the forge";
    }
    @Override
    public String toString(){
        return name() + " (" + type() + ") [" + ruleText() +"]";
    }
    @Override
    public boolean isInstant() {
        return false;
    }
    
}
