
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
    private void stackInsert(Player actPlayer, Player enemy, boolean isMain){
        boolean selP1=true, selP2=true;
        do{
            selP1=playAvailableEffect(actPlayer, isMain);
            if(selP2==false && selP2==selP1) //tiene conto della scelta fatta prima
                selP2=false;
            else
                selP2=playAvailableEffect(enemy, !isMain);
        }
        while(selP1 || selP2); /*se nessuno sceglie (selP1=false, selP2=false) allora esce*/
    }
    private boolean playAvailableEffect(Player activePlayer, boolean isMain) {
        //collect and display available effects...
        ArrayList<Effect> availableEffects = new ArrayList<>();
        Scanner reader = CardGame.instance.getScanner();

       
        System.out.println(activePlayer.name() + " select card/effect to play, 0 to pass");
        int i, idx;
        do{
            do{
                i=0;
                idx=0;
                 //...cards first
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
                idx= reader.nextInt()-1;
                if (idx<-1 || idx>=availableEffects.size())
                    System.out.println("[ERR] select correctly!");
                }while(idx<-1 || idx>=availableEffects.size());
                if(idx==-1){
                    System.out.println("You pass");
                    return false;
                }
                
        }while(!availableEffects.get(idx).play());
            return  true;
    }
    
    private boolean declareAttacker(){
         Scanner reader = CardGame.instance.getScanner();
         int i=0, idx;
         ArrayList<Creature> canAttack = new ArrayList<>();
         for(Creature c:currentPlayer.getCreatures()){
                    if(!c.isTapped() && !c.defender())
                        canAttack.add(c);
         }
        //Dichiarazione degli attaccanti
        if(canAttack.isEmpty())
            return false;
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
        return idx==0 ? false : true;
    }
    
    private boolean declareDefender(){
        Scanner reader = CardGame.instance.getScanner();
        int idx=0;
        ArrayList<Creature> canDefend = new ArrayList<>();
        ArrayList<Creature> attacker = new ArrayList<>();
        for(Creature c:opponent.getCreatures())
            if(!c.isTapped())
                canDefend.add(c);
        //Dichiarazione dei difensori
        if(canDefend.isEmpty())
            return false;
        for(Map.Entry<Creature, ArrayList<Creature>> m: fight.entrySet())
            attacker.add(m.getKey());
        if(!fight.isEmpty()){ /*se c'è almeno un attaccante...*/
            do{
                int i=0;

                System.out.println(opponent.name()+", select the creature that must defend, 0 to pass");
                for(Creature c: canDefend){
                    System.out.println((i+1)+" "+c.toString());
                    i++;
                }
                idx=reader.nextInt();
                if(idx!=0 && (idx-1)<canDefend.size()){
                    Creature defender = canDefend.get(idx-1);
                    canDefend.remove(idx-1);
                    int idxDif=0;
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
                }
            }while(idx!=0 && !canDefend.isEmpty());
        }
        return idx==0 ? false : true;
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

        
           if(declareAttacker()){
                stackInsert(opponent, currentPlayer, false);
                if(declareDefender())
                    stackInsert(currentPlayer, opponent, true );
           CardGame.instance.getStack().resolve();
           executeBattle();
           }
           System.out.println("[END_COMBAT_PHASE] STATUS: "+currentPlayer.name()+" LIFE:"+currentPlayer.getLife()+" "+opponent.name()+" LIFE:"+opponent.getLife());
    }
    
    public void setEbs(ExecuteBattleStrategy ebs){
        this.ebs = ebs;
    }
    public ExecuteBattleStrategy getEbs(){
        return ebs;
    }
}
