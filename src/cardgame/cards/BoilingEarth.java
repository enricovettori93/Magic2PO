
package cardgame.cards;

import cardgame.*;
import java.util.List;

public class BoilingEarth implements Card{

    private class BoilingEarthEffect extends AbstractCardEffect{

        BoilingEarthEffect(Player p, Card c) { super(p,c); }
        
        @Override
        public void resolve() {
            List<Creature> temp=CardGame.instance.getCurrentAdversary().getCreatures();
            temp.addAll(CardGame.instance.getCurrentPlayer().getCreatures());
            for(int i=0;i<temp.size();i++){
                System.out.println("[BOILING EARTH] "+temp.get(i).name()+" get 1 damage: "+temp.get(i).getToughnessDecorated()+" ->"+(temp.get(i).getToughnessDecorated()-1));
                temp.get(i).inflictDamage(1);
            }
        }
    
    }
    
    @Override
    public Effect getEffect(Player owner) {
        return new BoilingEarthEffect(owner, this);
    }

    @Override
    public String name() {
        return "Boiling Earth";
    }

    @Override
    public String type() {
        return "Sorcery";
    }

    @Override
    public String ruleText() {
        return "Boiling Earth deals 1 damage to each creature";
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
