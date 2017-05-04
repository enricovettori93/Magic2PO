
package cardgame;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class DefaultCombatPhase implements Phase {
    Player currentPlayer;
    Player opponent;
    ExecuteBattleStrategy ebs = new DefaultExecuteBattleStrategy();
    LinkedHashMap<Creature, ArrayList<Creature>> fight=new LinkedHashMap<>();
    private boolean playAvailableEffect(Player activePlayer, boolean isMain) {
        //collect and display available effects...
        ArrayList<Effect> availableEffects = new ArrayList<>();
        Scanner reader = CardGame.instance.getScanner();

        //...cards first
        System.out.println(activePlayer.name() + " select card/effect to play, 0 to pass");
        int i=0;
        for( Card c:activePlayer.getHand() ) {
            if ( isMain || c.isInstant() ) {
                availableEffects.add( c.getEffect(activePlayer) );
                System.out.println(Integer.toString(i+1)+") " + c );
                ++i;
            }
        }
        
        //...creature effects last
        for ( Creature c:activePlayer.getCreatures()) {
            for (Effect e:c.avaliableEffects()) {
                availableEffects.add(e);
                System.out.println(Integer.toString(i+1)+") " + c.name() + 
                    " ["+ e + "]" );
                ++i;
            }
        }
        
        //get user choice and play it
        int idx= reader.nextInt()-1;
        if (idx<0 || idx>=availableEffects.size()) return false;

        availableEffects.get(idx).play();
        return true;
    }
    
    private void declareAttacker(){
         Scanner reader = CardGame.instance.getScanner();
         int i=0, idx;
         ArrayList<Creature> canAttack = new ArrayList<>();
         for(Creature c:currentPlayer.getCreatures()){
                    if(!c.isTapped() && !c.defender())
                        canAttack.add(c);
         }
        //Dichiarazione degli attaccanti
        do{
            System.out.println(currentPlayer.name()+", select the creature that must attack, 0 to pass");
            for(Creature d: canAttack){
               System.out.println((i+1)+")"+d.toString());
               i++;
            }
            idx=reader.nextInt();
            if(idx!=0 && (idx-1)<currentPlayer.getCreatures().size()){
                fight.put(canAttack.get(idx-1), new ArrayList<Creature>()); /*la aggiungo alla mappa*/
                canAttack.remove(idx-1); /*la rimuovo dalla lista*/
            }
            i=0;
        }while(idx!=0 && !canAttack.isEmpty());
    }
    
    private void declareDefender(){
        Scanner reader = CardGame.instance.getScanner();
        int idx;
        ArrayList<Creature> canDefend = new ArrayList<>();
        ArrayList<Creature> attacker = new ArrayList<>();
        for(Creature c:opponent.getCreatures())
            if(!c.isTapped())
                canDefend.add(c);
        //Dichiarazione dei difensori 
        if(!fight.isEmpty()){ /*se c'è almeno un attaccante...*/
            do{
                int i=0;
                for(Map.Entry<Creature, ArrayList<Creature>> m: fight.entrySet())
                    attacker.add(m.getKey());
                System.out.println(opponent.name()+", select the creature that must defend, 0 to pass");
                for(Creature c: canDefend){
                    System.out.println((i+1)+" "+c.toString());
                    i++;
                }
                idx=reader.nextInt();
                    if(idx!=0 && (idx-1)<canDefend.size()){
                        Creature defender = canDefend.get(idx-1);
                        canDefend.remove(idx-1);
                        int idxDif;
                        int j=0;
                        do{
                            System.out.println(opponent.name()+", select the creature from which you have to defend yourself");
                            System.out.println("Opponent's attackers:");
                            for(Creature c : attacker){
                                System.out.println((j+1)+" "+c.toString());
                                j++;
                            }
                            //TODO: Fase Effetti Stack e istantanei sulla sungola creatura(Difensore)
                            //playAvailableEffect(opponent,false); boh si fa dopo
                            //definizione dei difensori rispetto agli attaccanti
                            idxDif=reader.nextInt();
                            if(idxDif>attacker.size() && idxDif<1)
                                System.out.println("Error: there isn't any attacker in that position. Reselect");
                        }while(idxDif>attacker.size() && idxDif<1);
                        Creature att = attacker.get(idxDif-1);
                        ArrayList<Creature> defendFrom = fight.get(att);
                        defendFrom.add(defender);
                        fight.put(att, defendFrom);
                        playAvailableEffect(opponent, false);
                    }
                }while(idx!=0);
        }
    }
    
    private void executeBattle(){
        ebs.executeBattle(this, fight);
    }
    
    @Override
    public void execute() {
        currentPlayer = CardGame.instance.getCurrentPlayer();
        opponent = CardGame.instance.getCurrentAdversary();
        System.out.println(currentPlayer.name() + ": combat phase");
       
        CardGame.instance.getTriggers().trigger(Triggers.COMBAT_FILTER);

        if(!currentPlayer.getCreatures().isEmpty()){ //nel programma del prof., le creature giocate sono contenute in "Player"
           declareAttacker();
           playAvailableEffect(opponent, false);
           CardGame.instance.getStack().resolve();
           declareDefender();
           
           executeBattle();
           System.out.println("[END_COMBAT_PHASE] STATUS: "+currentPlayer.name()+" LIFE:"+currentPlayer.getLife()+" "+opponent.name()+" LIFE:"+opponent.getLife());
        }
    }
    
    public void setEbs(ExecuteBattleStrategy ebs){
        this.ebs = ebs;
    }
    public ExecuteBattleStrategy getEbs(){
        return ebs;
    }
}
