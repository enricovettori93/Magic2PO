
package cardgame.playerstrategy;

import cardgame.Player;

public class DefaultInflictDamage implements PlayerInflictDamageStrategy{
    /**
     * Strategy di default
     */
    @Override
    public void inflictDamage(Player player, int pts) {
        player.defaultInflictDamage(pts);
    }
}
