
package cardgame;

/**
 *
 * @author giaco
 */
public class Target {
   Player targetOwner;
   Permanent target;

    public Target(Player targetOwner, Enchantment target) {
        this.targetOwner = targetOwner;
        this.target=target;
    }
    public Player getTargetOwner(){
        return targetOwner;
    }
    public Permanent getTarget(){
        return target;
    }
}
