
package cardgame.playerstrategy;

import cardgame.Player;

public class PreventOneDamageStrategy implements PlayerInflictDamageStrategy{

    @Override
    public void inflictDamage(Player player, int pts) {
        if(pts>0)
            pts--;
        player.defaultInflictDamage(pts);
    }
    
}
