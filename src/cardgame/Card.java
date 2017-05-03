
package cardgame;

public interface Card {
    // returns the effect to be placed on the stack
    Effect getEffect(Player owner);
    String name();
    String type(); //sorcery, instant, or creature
    String ruleText();
    boolean isInstant();
}
