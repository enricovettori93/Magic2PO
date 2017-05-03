
package cardgame;

public class DefaultDrawPhase implements Phase {
    @Override
    public void execute() {
        Player currentPlayer = CardGame.instance.getCurrentPlayer();
        
        System.out.println(currentPlayer.name() + ": draw phase");
        
        CardGame.instance.getTriggers().trigger(Triggers.DRAW_FILTER);
        currentPlayer.draw();
        
        while(currentPlayer.getHand().size() > currentPlayer.getMaxHandSize())
            currentPlayer.selectDiscard();
    }
}
