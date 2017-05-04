
package cardgame.target;

import cardgame.*;
import java.util.*;

public class TargetManager {
    
    ArrayList<Target> targets=new ArrayList<>(); 
    
    public Target getTarget(int filter) throws Exception{
        targets.clear();
        Scanner input = new Scanner(System.in);
        int choice,index;
        
        switch(filter){
            case ENCHANTMENT_ON_FIELD_TARGET: 
                getEnchantmentOnField(CardGame.instance.getCurrentPlayer());
                getEnchantmentOnField(CardGame.instance.getCurrentAdversary());
                break;
            case CREATURE_ON_FIELD_TARGET: 
                getCreatureOnField(CardGame.instance.getCurrentPlayer());
                getCreatureOnField(CardGame.instance.getCurrentAdversary());
                break;
            case PERMANENT_TARGET: 
                getPermanent(CardGame.instance.getCurrentPlayer());
                getPermanent(CardGame.instance.getCurrentAdversary());
                break;
            case PLAYER_TARGET: 
                getPlayer();
                break;
            case STACK_EFFECT_TARGET:
                getStack(CardGame.instance.getCurrentPlayer());
                getStack(CardGame.instance.getCurrentAdversary());
                break;
            case STACK_CREATURE_EFFECT_TARGET:
                getCreatureStack(CardGame.instance.getCurrentPlayer());
                getCreatureStack(CardGame.instance.getCurrentAdversary());
                break;
            case STACK_ISTANT_EFFECT_TARGET: 
                getInstantStack(CardGame.instance.getCurrentPlayer());
                getInstantStack(CardGame.instance.getCurrentAdversary());
                break;
            case STACK_SORCERY_EFFECT_TARGET: 
                getSorceryStack(CardGame.instance.getCurrentPlayer());
                getSorceryStack(CardGame.instance.getCurrentAdversary());                
                break;
            case STACK_ENCHANTMENT_EFFECT_TARGET:                 
                getEnchantmentStack(CardGame.instance.getCurrentPlayer());
                getEnchantmentStack(CardGame.instance.getCurrentAdversary());
                break;
            case CREATURE_OR_PLAYER_TARGET: 
                getPlayer();
                getCreatureOnField(CardGame.instance.getCurrentPlayer());
                getCreatureOnField(CardGame.instance.getCurrentAdversary());
                break;
            case CREATURE_TARGET:                 
                getCreature(CardGame.instance.getCurrentPlayer());
                getCreature(CardGame.instance.getCurrentAdversary());
                break;
            case ENCHANTMENT_TARGET: 
                getEnchantment(CardGame.instance.getCurrentPlayer());
                getEnchantment(CardGame.instance.getCurrentAdversary());
                break;
            case STACK_TARGETSPELL_TARGET: 
                getTargetSpell();
                break;
            default: 
                System.out.println("Bad target!");
        }
        if(targets.isEmpty()){
            //non esistono target che soddisfano la condizione,
            //se il giocatore è saggio non dovrebbe mai finire quì
            System.out.println("You can't choose a target!");
            throw new Exception("TARGET NULL EXC");
        }
        System.out.println("\n - Choose the target --");
        for(index=0;index<targets.size();index++){
            System.out.println(index+") "+targets.get(index).toString());
        }
        do{
            System.out.print("\n-> ");
            choice =  input.nextInt();
        }while(choice < 0 || choice > (targets.size()-1));        
        return targets.get(choice);
    }
    
    private void getEnchantmentOnField(Player p){
        List<Enchantment> temp= p.getEnchantments();
        int i;
        for(i=0;i<temp.size();i++)
            targets.add(new PermanentTarget(p,temp.get(i)));        
    }
    
    private void getCreatureOnField(Player p){
        List<Creature> temp= p.getCreatures();
        int i;
        for(i=0;i<temp.size();i++)
            targets.add(new PermanentTarget(p,temp.get(i)));
    }
    
    private void getPermanent(Player p){            
        getEnchantmentOnField(p);
        getCreatureOnField(p);
    }
    
    private void getPlayer(){
        targets.add(new PlayerTarget(CardGame.instance.getCurrentPlayer()));
        targets.add(new PlayerTarget(CardGame.instance.getCurrentAdversary()));
    }
    
    private void getCreature(Player p){
        getCreatureOnField(p);
        getCreatureStack(p);
    }
    
    private void getEnchantment(Player p){
        getEnchantmentOnField(p);
        getEnchantmentStack(p);
    }
    
    private void getCreatureStack(Player p){
        ArrayList<Effect> temp=CardGame.instance.getStack().getAllEffect();
        for(Effect e:temp){
            if (e instanceof AbstractCardEffect){
                Card tempC=((AbstractCardEffect) e).getCard();
                if(tempC.type().equals("Creature"))
                    if(((AbstractCardEffect) e).getOwner().name().equals(p.name()))
                        targets.add(new CardTarget((AbstractCardEffect) e));
            }
        }        
    }
    
    private void getEnchantmentStack(Player p){
        ArrayList<Effect> temp=CardGame.instance.getStack().getAllEffect();
        for(Effect e:temp){
            if (e instanceof AbstractCardEffect){
                Card tempC=((AbstractCardEffect) e).getCard();
                if(tempC.type().equals("Enchantment"))
                    if(((AbstractCardEffect) e).getOwner().name().equals(p.name()))
                        targets.add(new CardTarget((AbstractCardEffect) e));
            }
        } 
    }
    
    private void getInstantStack(Player p){
        ArrayList<Effect> temp=CardGame.instance.getStack().getAllEffect();
        for(Effect e:temp){
            if (e instanceof AbstractCardEffect){
                Card tempC=((AbstractCardEffect) e).getCard();
                if(tempC.type().equals("Instant"))
                    if(((AbstractCardEffect) e).getOwner().name().equals(p.name()))
                        targets.add(new CardTarget((AbstractCardEffect) e));
            }
        } 
    }
    
    private void getSorceryStack(Player p){
        ArrayList<Effect> temp=CardGame.instance.getStack().getAllEffect();
        for(Effect e:temp){
            if (e instanceof AbstractCardEffect){
                Card tempC=((AbstractCardEffect) e).getCard();
                if(tempC.type().equals("Sorcery"))
                    if(((AbstractCardEffect) e).getOwner().name().equals(p.name()))
                        targets.add(new CardTarget((AbstractCardEffect) e));
            }
        } 
    }
    
    private void getStack(Player p){
        ArrayList<Effect> temp=CardGame.instance.getStack().getAllEffect();
        for(Effect e:temp){
            if (e instanceof AbstractCardEffect){
                Card tempC=((AbstractCardEffect) e).getCard();
                if(((AbstractCardEffect) e).getOwner().name().equals(p.name()))
                    targets.add(new CardTarget((AbstractCardEffect) e));
            }
        } 
    }
    
    private void getTargetSpell(){
        ArrayList<EffectTarget> temp=CardGame.instance.getStack().getALSingleTargets();
        for(EffectTarget e:temp){
            targets.add(e);
        }
    }
    
    public static final int ENCHANTMENT_ON_FIELD_TARGET=1;
    public static final int CREATURE_ON_FIELD_TARGET=2;
    public static final int PERMANENT_TARGET=3;
    public static final int PLAYER_TARGET=4;
    public static final int STACK_EFFECT_TARGET=5;
    public static final int STACK_CREATURE_EFFECT_TARGET=6;
    public static final int STACK_ISTANT_EFFECT_TARGET=7;
    public static final int STACK_SORCERY_EFFECT_TARGET=8;
    public static final int STACK_ENCHANTMENT_EFFECT_TARGET=9;
    public static final int CREATURE_OR_PLAYER_TARGET=10;
    public static final int ENCHANTMENT_TARGET=11;
    public static final int CREATURE_TARGET=12;
    public static final int STACK_TARGETSPELL_TARGET=13;
    
}
