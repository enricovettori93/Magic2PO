/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

/**
 *
 * @author giaco
 */
public class PermanentTarget implements Target{
    Player targetOwner;
    Permanent target;
    public PermanentTarget(Player targetOwner, Permanent target){
        this.target=target;
        this.targetOwner=targetOwner;
    }
    @Override
    public Object getTarget() {
        return target;
    }
    public Player getTargetOwner(){
        return targetOwner;
    }
}
