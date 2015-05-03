/*
 * Class :      ShotTypeBomb
 * Creation:    Feb 15, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.models.weapons;

import com.battleship.behaviors.Target;





/**
 * <h1>ShotTypeBomb</h1>
 * <p>
 * public class ShotTypeBomb<br/>
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
public class ShotTypeBomb implements ShotType{
    //**************************************************************************
    // Fire Functions
    //**************************************************************************
    @Override
    public boolean fireSquareGrid(int pX, int pY, Target[][] pTarget) {
        for(int i=-1; i<2; i++){
            for(int j=-1; j<2; j++){
                Weapon.hitTargetIfExists(pX+i, pY+j, pTarget);
            }
        }
        return true;
    }

    @Override
    public boolean fireHexagonGrid(int pX, int pY, Target[][] pTarget) {
        if(pX%2==0){
            Weapon.hitTargetIfExists(pX, pY, pTarget);
            Weapon.hitTargetIfExists(pX, pY-1, pTarget);
            Weapon.hitTargetIfExists(pX, pY+1, pTarget);
            Weapon.hitTargetIfExists(pX-1, pY-1, pTarget);
            Weapon.hitTargetIfExists(pX-1, pY, pTarget);
            Weapon.hitTargetIfExists(pX+1, pY-1, pTarget);
            Weapon.hitTargetIfExists(pX+1, pY, pTarget);
        }else{
            Weapon.hitTargetIfExists(pX, pY-1, pTarget);
            Weapon.hitTargetIfExists(pX-1, pY, pTarget);
            Weapon.hitTargetIfExists(pX, pY, pTarget);
            Weapon.hitTargetIfExists(pX+1, pY, pTarget);
            Weapon.hitTargetIfExists(pX-1, pY+1, pTarget);
            Weapon.hitTargetIfExists(pX, pY+1, pTarget);
            Weapon.hitTargetIfExists(pX+1, pY+1, pTarget);
        }
        return true;
    } 
    
    
    //**************************************************************************
    // Aim Functions
    //**************************************************************************
    @Override
    public boolean aimSquareGrid(int pX, int pY, Target[][] pTarget){
        for(int i=-1; i<2; i++){
            for(int j=-1; j<2; j++){
                Weapon.aimTargetIfExists(pX+i, pY+j, pTarget);
            }
        }
        return true;
    }

    @Override
    public boolean aimHexagonGrid(int pX, int pY, Target[][] pTarget){
        if(pX%2==0){
            Weapon.aimTargetIfExists(pX, pY, pTarget);
            Weapon.aimTargetIfExists(pX, pY-1, pTarget);
            Weapon.aimTargetIfExists(pX, pY+1, pTarget);
            Weapon.aimTargetIfExists(pX-1, pY-1, pTarget);
            Weapon.aimTargetIfExists(pX-1, pY, pTarget);
            Weapon.aimTargetIfExists(pX+1, pY-1, pTarget);
            Weapon.aimTargetIfExists(pX+1, pY, pTarget);
        }else{
            Weapon.aimTargetIfExists(pX, pY-1, pTarget);
            Weapon.aimTargetIfExists(pX-1, pY, pTarget);
            Weapon.aimTargetIfExists(pX, pY, pTarget);
            Weapon.aimTargetIfExists(pX+1, pY, pTarget);
            Weapon.aimTargetIfExists(pX-1, pY+1, pTarget);
            Weapon.aimTargetIfExists(pX, pY+1, pTarget);
            Weapon.aimTargetIfExists(pX+1, pY+1, pTarget);
        }
        return true;
    }
}
