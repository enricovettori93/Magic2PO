/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.*;
import java.util.List;



public class CalmingVerse implements Card{

    private class CalmingVerseEffect extends AbstractCardEffect{

        CalmingVerseEffect(Player p, Card c) { super(p,c); }
        
        int i = 0;
        @Override
        public void resolve() {
           
           // distruggere incantesimi che non si controllano - quelli dell'avversario?
           // prendere la lista di incantesimi dell'avversario */
           
           List<Enchantment> advenc = CardGame.instance.getCurrentAdversary().getEnchantments();
           
           for(i=0;i<advenc.size();i++){
               System.out.println("Enchantment:"+ advenc.get(i).name()+" destroyed");
               CardGame.instance.getCurrentAdversary().getEnchantments().remove(i);
           }
        }
    }
    @Override
    public Effect getEffect(Player owner) {
        return new CalmingVerseEffect(owner, this);
    }

    @Override
    public String name() {
        return "Calming Verse";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String ruleText() {
        return "Calming Verse destroy all enchantments you don't control.";
    }

    @Override
    public String toString() { return name() + " (" + type() + ") [" + ruleText() +"]";}
    
    @Override
    public boolean isInstant() {
        return false;
    }
    
}
