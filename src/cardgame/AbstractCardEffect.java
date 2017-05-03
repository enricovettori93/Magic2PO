
package cardgame;

public abstract class AbstractCardEffect extends AbstractEffect {
    
    protected Player owner;
    protected Card card;
    
    protected AbstractCardEffect(Player p, Card c) { owner=p; card=c; }
    
    public boolean play() { 
        owner.getHand().remove(card);
        return super.play();
    }
    
    public String toString() { return card.toString(); }
    
    public Card getCard(){
        return card;
    }
    
    public Player getOwner(){
        return owner;
    }
}
