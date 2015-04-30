/*
 * Class :      ShotTypeMissile
 * Creation:    Feb 15, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.models.weapons;

import com.battleship.behaviors.Target;





/**
 * <h1>ShotTypeMissile</h1>
 * <p>
 * public class ShotTypeMissile<br/>
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
public class ShotTypeMissile implements ShotType{
    //**************************************************************************
    // Fire Functions
    //**************************************************************************
    @Override
    public boolean fireSquareGrid(int pX, int pY, Target[][] pTarget) {
        return pTarget[pY][pX].hit(); //pX pY must to be valid position
    }

    @Override
    public boolean fireHexagonGrid(int pX, int pY, Target[][] pTarget) {
        return pTarget[pY][pX].hit();
    }
    
    
    //**************************************************************************
    // Aim Functions
    //**************************************************************************
    @Override
    public boolean aimSquareGrid(int pX, int pY, Target[][] pTarget){
        return pTarget[pY][pX].aim(); //pX pY must to be valid position
    }

    @Override
    public boolean aimHexagonGrid(int pX, int pY, Target[][] pTarget){
        return pTarget[pY][pX].aim(); //pX pY must to be valid position
    }
}
