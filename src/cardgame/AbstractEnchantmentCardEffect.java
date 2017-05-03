
package cardgame;

public abstract class AbstractEnchantmentCardEffect extends AbstractCardEffect {
    protected AbstractEnchantmentCardEffect( Player p, Card c) { super(p,c); }
    
    // deferred method that creates the creature upon resolution
    protected abstract Enchantment  createEnchantment();
    
    @Override
    public void resolve() {
        Enchantment e=createEnchantment();
        owner.getEnchantments().add(e);
        e.insert();
    }
}
