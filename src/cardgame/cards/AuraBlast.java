
package cardgame.cards;


import cardgame.AbstractCardEffectTarget;
import cardgame.Card;
import cardgame.CardGame;
import cardgame.Effect;
import cardgame.Enchantment;
import cardgame.target.PermanentTarget;
import cardgame.Player;
import cardgame.target.Target;
import java.util.Scanner;

/**
 *
 * @author giaco
 */
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
            Player rival;
            Scanner input = new Scanner(System.in);
            int choice;
            if(owner == CardGame.instance.getCurrentPlayer())
                rival = CardGame.instance.getCurrentAdversary();
            else
                rival = CardGame.instance.getCurrentPlayer();
            do{
                System.out.println( "[AURA BLAST] SELECT TARGET (from the rival Enchantments), 0 for pass:");
                rival.printEnchantments();
                choice =  input.nextInt();
            }while(choice < 0 && choice > rival.getEnchantments().size());
            if(choice!=0)
                targets.add(new PermanentTarget(rival, rival.getEnchantments().get(choice-1)));
            else{
                if(choice == 0){
                    do{
                        System.out.println( "[AURA BLAST] SELECT TARGET (from your Enchantments), 0 for skip:");
                        owner.printEnchantments();
                        choice = input.nextInt();
                    }while(choice < 0 && choice > owner.getEnchantments().size());
                    if(choice != 0)
                        targets.add(new PermanentTarget(owner, owner.getEnchantments().get(choice-1)));
                }
            }
        }
        @Override
        public void resolve() {
            //destroy target         
            for(Target t : targets){
                ((PermanentTarget)t).getTargetOwner().getEnchantments().remove((Enchantment)t.getTarget());
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
