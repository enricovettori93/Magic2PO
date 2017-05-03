
package cardgame;

public abstract class AbstractEnchantment implements Enchantment {
    protected Player owner;        
    protected AbstractEnchantment(Player owner) { this.owner=owner; }
        
    @Override
        public void insert() {
            CardGame.instance.getTriggers().trigger(Triggers.ENTER_ENCHANTMENT_FILTER,this);
        }
    
    @Override
        public void remove() {
            owner.getEnchantments().remove(this);
            CardGame.instance.getTriggers().trigger(Triggers.EXIT_ENCHANTMENT_FILTER,this);
        }
        
    @Override
        public String toString() {
            return name() + " (Enchantment)";
        }
}
