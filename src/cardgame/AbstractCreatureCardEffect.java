
package cardgame;

public abstract class AbstractCreatureCardEffect extends AbstractCardEffect {
    protected AbstractCreatureCardEffect( Player p, Card c) { super(p,c); }
    
    // deferred method that creates the creature upon resolution
    protected abstract Creature createCreature();
    
    @Override
    public void resolve() {
        Creature c=createCreature();
        owner.getCreatures().add(c);
        c.insert();
    }
}
