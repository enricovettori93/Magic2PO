
package cardgame;

public class SkipPhase implements Phase {
    private final Phases phaseId;
    private int skipNum;
    
    public SkipPhase(Phases id) {
        phaseId=id;
        skipNum=1;
    }
    public SkipPhase(Phases id, int skip) {
        phaseId=id;
        skipNum=skip;
    }
    
    
    @Override
    public void execute() {
        Player currentPlayer = CardGame.instance.getCurrentPlayer();
        System.out.println(currentPlayer.name() + ": skip " + phaseId.name() +" phase");
        --skipNum;
        if (skipNum==0) currentPlayer.removePhase(phaseId,this);
    }
}
