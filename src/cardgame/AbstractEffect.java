
package cardgame;

public abstract class AbstractEffect implements Effect {
    @Override
    public boolean play() { 
        CardGame.instance.getStack().add(this);
        return true;
    }
}
