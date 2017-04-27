/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame.target;

import cardgame.CardGame;
import cardgame.Enchantment;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Utente
 */
public class TargetManager {
    
    ArrayList<Object> targets=new ArrayList<>(); 
    
    public Object getTarget(int filter){
        int index;
        Scanner input = new Scanner(System.in);
        int choice;
        
        switch(filter){
            case ENCHANTMENT_TARGET: 
                getEnchantment();
                break;
            case CREATURE_TARGET: break;
            case PERMANENT_TARGET: break;
            case PLAYER_TARGET: break;
            case STACK_EFFECT_TARGET: break;
            case STACK_CREATURE_EFFECT_TARGET: break;
            case STACK_ISTANT_EFFECT_TARGET: break;
            case STACK_SORCERY_EFFECT_TARGET: break;
            case STACK_ENCHANTMENT_EFFECT_TARGET: break;
            case CREATURE_OR_PLAYER_TARGET: break;
        }
        do{
            choice =  input.nextInt();
        }while(choice < 0 && choice > targets.size());        
        return targets.get(choice);
    }
    
    private void getEnchantment(){
        List<Enchantment> temp= CardGame.instance.getCurrentPlayer().getEnchantments();
        int i,j;
        for(i=0;i<temp.size();i++)
            System.out.println(i+"Player 1: "+temp.get(i).name());
        targets.addAll(temp);
        temp=CardGame.instance.getCurrentAdversary().getEnchantments();
        for(j=0;j<temp.size();j++)
            System.out.println((j+i)+"Player 2: "+temp.get(j).name());
        targets.addAll(temp);
    }
    
    public static final int ENCHANTMENT_TARGET=1;
    public static final int CREATURE_TARGET=2;
    public static final int PERMANENT_TARGET=4;
    public static final int PLAYER_TARGET=8;
    public static final int STACK_EFFECT_TARGET=16;
    public static final int STACK_CREATURE_EFFECT_TARGET=32;
    public static final int STACK_ISTANT_EFFECT_TARGET=64;
    public static final int STACK_SORCERY_EFFECT_TARGET=128;
    public static final int STACK_ENCHANTMENT_EFFECT_TARGET=256;
    public static final int CREATURE_OR_PLAYER_TARGET=512;
    
}
