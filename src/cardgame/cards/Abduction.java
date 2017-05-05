
package cardgame.cards;

import cardgame.*;
import cardgame.creaturestrategy.CreatureDefaultInflictDamage;
import cardgame.creaturestrategy.CreatureInflictDamageStrategy;
import cardgame.target.TargetManager;

public class Abduction implements Card{
    
    private class AbductionEffect extends AbstractEnchantmentCardEffectTarget{
        private class AbductionStrategy implements CreatureInflictDamageStrategy{
            Player oldOwner; //giocatore precedente all'applicazione di questa Strategy
            public AbductionStrategy(Player oldOwner){
                this.oldOwner=oldOwner;
            }
            /***
             * INFLICT DAMAGE 
             * Quando la carta riceve più danno della sua vita rimanente, non muore ma viene spostata al suo proprietario precedente, e resettato il danno
             * @param c
             * @param dmg 
             */
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
        public AbductionEffect(Player p, Card c){
            super(p,c);
        }
        @Override
        protected Enchantment createEnchantment() {
            return new AbductionEnchantment(owner);
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
        public void setTarget() throws Exception{
            try{
                targets.add(CardGame.instance.getTargetManager().getTarget(TargetManager.CREATURE_TARGET));
            }catch(Exception e){
                throw new Exception(e.getMessage());
            }
        }
        /**
         * RESOLVE
         * 1. Viene settata una strategy al target,
         *      in modo che quando muore viene riportata al giocatore precedente
         * 2. Rimuovo vecchio player e setto quello nuovo, 
         */
        @Override
        public void resolve() {
            if(targets.size()>0){ /*se c'è un target, sennò nothing*/
                AbstractCreature c =((AbstractCreature)targets.get(0).getTarget());
                System.out.println("[ABDUCTION] moving "+c.name()+" from Player "+c.getOwner()+" to "+this.owner+"...");
                c.setCids(new AbductionStrategy(c.getOwner()));
                c.getOwner().getCreatures().remove(c); //rimuovo la creatura dal campo del giocatore
                c.setOwner(this.owner); //setto il nuovo proprietario
                owner.getCreatures().add(c); //la inserisco nel campo del nuovo proprietario
                System.out.println("[ABDUCTION] untap "+c.name()+"...");
                if(c.isTapped()) //se è tappata, la untappo
                    c.untap();
                System.out.println("[ABDUCTION] Done.");
            }
            super.resolve();
        }
    }
    private class AbductionEnchantment extends AbstractEnchantment{

        public AbductionEnchantment(Player owner){
            super(owner);
        }

        @Override
        public String name() {
            return "Abduction";
        }
        public void insert() {
            super.insert();
        }
            @Override
        public void remove() {
            super.remove();
        }
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
        return name()+" When abduction come into play, untap enchanted creature. You control enchanted creature. When enchanted creature die, return that creature to play under is own control"; 
    }

    @Override
    public boolean isInstant() {
        return false;
    }
    
    @Override
    public String toString(){
         return name() + " (" + type() + ") [" + ruleText() +"]";
    }
}
