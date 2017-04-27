/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCreature;
import cardgame.AbstractEnchantmentCardEffectTarget;
import cardgame.AbstractEnchantmentTarget;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Creature;
import cardgame.Effect;
import cardgame.Enchantment;
import cardgame.Player;
import cardgame.creaturestrategy.CreatureDefaultInflictDamage;
import cardgame.creaturestrategy.CreatureInflictDamageStrategy;
import cardgame.target.TargetManager;
/**
 *
 * @author giaco
 */
public class Abduction implements Card{
    
    private class AbductionEffect extends AbstractEnchantmentCardEffectTarget{
        public AbductionEffect(Player p, Card c){
            super(p,c);
            //quando creo l'effetto metto il target?!?
            setTarget();
        }

        @Override
        public void setTarget() {
           
        }
        
        protected Enchantment createEnchantment() {
            return new AbductionEnchantment(owner);
        }
    }
    private class AbductionEnchantment extends AbstractEnchantmentTarget{
        private class AbductionStrategy implements CreatureInflictDamageStrategy{
            Player oldOwner;
            public AbductionStrategy(Player oldOwner){
                this.oldOwner=oldOwner;
            }
            @Override
            public void inflictDamage(Creature c, int dmg) {
                int subDmg = c.getDamageLeft()-dmg;
                if (subDmg <= 0){
                    System.out.println("[ABDUCTION] Move "+c.name()+"to his default owner");
                    /*non muore. Resetto danni e la sposto all'altro giocatore*/
                    c.resetDamage();
                    c.getOwner().getCreatures().remove(c);
                    c.setOwner(oldOwner); /*setto vecchio giocatore.*/
                    c.getOwner().getCreatures().add(c); /*lo aggiungo alle creature*/
                    c.setCids(new CreatureDefaultInflictDamage()); /*setto default inflict damage*/
                }
            
            }
        }
        public AbductionEnchantment(Player owner){
            super(owner);
        }
        
        @Override
        public void insert(){
            /*prima di inserire l'effetto nello stack, setto il target..*/
            setTarget();
            super.insert();
            
        }
        
        @Override
        public void remove() {
            /*quando viene rimossso, andrò a settare la Strategy per il metodo destroy della creatura*/
            AbstractCreature c =((AbstractCreature)targets.get(0).getTarget());
            if(c.isTapped())
                c.untap();
            c.setCids(new AbductionStrategy(c.getOwner()));
        }
        @Override
        public String name() {
            return "Abduction";
        }

        @Override
        public void setTarget() {
            targets.add(CardGame.instance.getTargetManager().getTarget(TargetManager.CREATURE_TARGET));        }
        }


    @Override
    public Effect getEffect(Player owner) {
        return new AbductionEffect(owner, this);
    }
    
    @Override
    public String name() {
        return "Abduction";
    }

    @Override
    public String type() {
        return "Enchantment";
    }

    @Override
    public String ruleText() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isInstant() {
        return false;
    }
    
}
