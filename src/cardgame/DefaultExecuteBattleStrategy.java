
package cardgame;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class DefaultExecuteBattleStrategy implements ExecuteBattleStrategy {
    /**EXECUTE BATTLE DI DEFAULT
     *  se ci sono difensori, allora l'attaccante attacca i difensori dichiarati a catena
     *  se non ci sono, viene attaccato il giocatore avversario
     * @param combat
     * @param fight 
     */
    @Override
    public void executeBattle(DefaultCombatPhase combat,LinkedHashMap<Creature, ArrayList<Creature>> fight) {
        for (Map.Entry<Creature,ArrayList<Creature>> entry : fight.entrySet()) {
            if(!entry.getValue().isEmpty())
                for(Creature defender : entry.getValue()){
                    if(entry.getKey().getPowerLeft()>0){
                        System.out.println("["+entry.getKey().name()+"] attack the defender "+defender.name());
                        defender.defend(entry.getKey()); /*attacca creatura*/
                    }
                }
                else{
                    System.out.println("["+entry.getKey().name()+"] attack the rival!");
                    entry.getKey().attack();
                }
        }
    }
    
}
