package cardgame;


import cardgame.Player;
import cardgame.Target;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author giaco
 */
public class PlayerTarget implements Target{
    Player target;
    public PlayerTarget(Player target){
        this.target=target;
    }
    @Override
    public Player getTarget() {
        return target;
    }
}
