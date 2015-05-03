/*
 * Class :      ShotTypeTorpedo
 * Creation:    Feb 15, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.models.weapons;

import com.battleship.behaviors.Target;





/**
 * <h1>ShotTypeTorpedo</h1>
 * <p>
 * public class ShotTypeTorpedo<br/>
 * implements ShotType
 * </p>
 *
 *
 * 
 * @date    Feb 15, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 * @see     ShotType
 */
public class ShotTypeTorpedo implements ShotType{
    //**************************************************************************
    // Fire Functions
    //**************************************************************************
    @Override
    public boolean fireSquareGrid(int pX, int pY, Target[][] pTarget) {
        Weapon.hitTargetIfExists(pX, pY, pTarget);
        Weapon.hitTargetIfExists(pX, pY-1, pTarget);
        Weapon.hitTargetIfExists(pX, pY+1, pTarget);
        Weapon.hitTargetIfExists(pX-1, pY, pTarget);
        Weapon.hitTargetIfExists(pX+1, pY, pTarget);
        return true;
    }

    @Override
    public boolean fireHexagonGrid(int pX, int pY, Target[][] pTarget) {
        if(pX%2==0){
            Weapon.hitTargetIfExists(pX, pY, pTarget);
            Weapon.hitTargetIfExists(pX-1, pY-1, pTarget);
            Weapon.hitTargetIfExists(pX-1, pY, pTarget);
            Weapon.hitTargetIfExists(pX+1, pY-1, pTarget);
            Weapon.hitTargetIfExists(pX+1, pY, pTarget);
        } else{
            Weapon.hitTargetIfExists(pX, pY, pTarget);
            Weapon.hitTargetIfExists(pX-1, pY, pTarget);
            Weapon.hitTargetIfExists(pX+1, pY, pTarget);
            Weapon.hitTargetIfExists(pX-1, pY+1, pTarget);
            Weapon.hitTargetIfExists(pX+1, pY+1, pTarget);
        }
        return true;
    }
    
    
    //**************************************************************************
    // Aim Functions
    //**************************************************************************
    @Override
    public boolean aimSquareGrid(int pX, int pY, Target[][] pTarget){
        Weapon.aimTargetIfExists(pX, pY, pTarget);
        Weapon.aimTargetIfExists(pX, pY-1, pTarget);
        Weapon.aimTargetIfExists(pX, pY+1, pTarget);
        Weapon.aimTargetIfExists(pX-1, pY, pTarget);
        Weapon.aimTargetIfExists(pX+1, pY, pTarget);
        return true;
    }

    @Override
    public boolean aimHexagonGrid(int pX, int pY, Target[][] pTarget){
        if(pX%2==0){
            Weapon.aimTargetIfExists(pX, pY, pTarget);
            Weapon.aimTargetIfExists(pX-1, pY-1, pTarget);
            Weapon.aimTargetIfExists(pX-1, pY, pTarget);
            Weapon.aimTargetIfExists(pX+1, pY-1, pTarget);
            Weapon.aimTargetIfExists(pX+1, pY, pTarget);
        } else{
            Weapon.aimTargetIfExists(pX, pY, pTarget);
            Weapon.aimTargetIfExists(pX-1, pY, pTarget);
            Weapon.aimTargetIfExists(pX+1, pY, pTarget);
            Weapon.aimTargetIfExists(pX-1, pY+1, pTarget);
            Weapon.aimTargetIfExists(pX+1, pY+1, pTarget);
        }
        return true;
    }
}
