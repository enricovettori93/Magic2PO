
package cardgame.playerstrategy;

import cardgame.Player;

public class DefaultInflictDamage implements PlayerInflictDamageStrategy{

    @Override
    public void inflictDamage(Player player, int pts) {
        player.defaultInflictDamage(pts);
    }
}
