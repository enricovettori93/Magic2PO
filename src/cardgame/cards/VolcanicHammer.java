/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.cards;

import cardgame.AbstractCardEffect;
import cardgame.Card;
import cardgame.Effect;
import cardgame.Player;

/**
 *
 * @author giaco
 */
public class VolcanicHammer implements Card{
    
    private class VolcanicHammerEffect extends AbstractCardEffect {
        public VolcanicHammerEffect(Player p, Card c) { super(p,c); }
        @Override
        //MANCA METODO RESOLVE
        public void resolve() {}
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
