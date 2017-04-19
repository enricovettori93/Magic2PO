/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractEnchantment;
import cardgame.AbstractEnchantmentCardEffect;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Effect;
import cardgame.Enchantment;
import cardgame.Player;
import cardgame.TriggerAction;
import cardgame.Triggers;
import java.util.Scanner;

/**
 *
 * @author giaco
 */
public class AetherBarrier implements Card{

    private class AetherBarrierEffect extends AbstractEnchantmentCardEffect{
        public AetherBarrierEffect(Player p, Card c) { 
            super(p,c);
        }        

        @Override
        protected Enchantment createEnchantment() {
            return new AetherBarrierEnchantment(owner); 
        }
        
        
    }
    
    private class AetherBarrierEnchantment extends AbstractEnchantment{
        private AetherBarrierEnchantment(Player owner){
            super(owner);
        }
        
                private final TriggerAction AetherBarrierAction = new TriggerAction() {
                @Override
                public void execute(Object args) {
                    if (args != null  && args instanceof Enchantment) {
                        Enchantment c = (Enchantment)args;
                        Scanner s = new Scanner(System.in);
                        String choice = new String();
                        //se sono qui, vuol dire che il giocatore p è il giocatore corrente (ha giocato la carta)
                        Player p = CardGame.instance.getCurrentPlayer();
                        do{
                            System.out.println("[AetherBarrier]"+p.name()+ "sacrifices a Permanent? Y/N");
                            choice = s.nextLine();
                        }while(!(choice.equals("Y")) || !(choice.equals("N")));
                        if(p.getCreatures().isEmpty() && choice.equals("Y"))
                            System.out.println("[AetherBarrier]You don't have a permanent! ");
                        if(choice.equals("N") || p.getCreatures().isEmpty()){
                            System.out.println("[AetherBarrier] inflict 1 damage");
                            p.inflictDamage(1);
                        }
                        else{
                            int choicePermanent;
                            do{
                                System.out.println("[AetherBarrier] Select the Permanent to discard:");
                                p.printPermanents();
                                choicePermanent=s.nextInt();
                            }while(choicePermanent<=0 && choicePermanent >p.getCreatures().size());
                            p.destroy(p.getCreatures().get(choicePermanent-1));
                        }
                    }
                }
            };
        @Override
        public void insert() {
            super.insert();
            CardGame.instance.getTriggers().register(Triggers.ENTER_CREATURE_FILTER, AetherBarrierAction);
        }
        
        @Override
        public void remove() {
            super.remove();
            CardGame.instance.getTriggers().deregister(AetherBarrierAction);
        }
        
        @Override
        public String name() {
            return "Aether Barrier"; //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    @Override
    public Effect getEffect(Player owner) {
       return new AetherBarrierEffect(owner,this);
    }
    
     @Override
    public String name() {
        return "AetherBarrier";
    }
    
    @Override
    public String type() {
       return "Enchantment";
    }

    @Override
    public String ruleText() {
        return "Whenever a player plays Creature Spell, that player sacrifices a permanent unless he or she pays 1";
    }

    @Override
    public boolean isInstant() {
       return false;
    }
    
}
