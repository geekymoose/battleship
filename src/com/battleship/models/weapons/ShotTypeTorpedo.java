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
 */
public class ShotTypeTorpedo implements ShotType{
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    public ShotTypeTorpedo(){
    
    }
    

    //**************************************************************************
    // Functions
    //**************************************************************************
    @Override
    public boolean fireSquareGrid(int pX, int pY, Target[][] pTarget) {
        /*
         * ATTENTION
         * See fireHexagonGrid in ShotTYpeBomb for information about this try
         */
        try{
            pTarget[pY][pX].hit();
            pTarget[pY-1][pX].hit();
            pTarget[pY+1][pX].hit();
            pTarget[pY][pX-1].hit();
            pTarget[pY][pX+1].hit();
        } catch(java.lang.ArrayIndexOutOfBoundsException ex){
            //Means this square is not in the Target matrix (Out of range)
        }
        return true;
    }

    @Override
    public boolean fireHexagonGrid(int pX, int pY, Target[][] pTarget) {
        /*
         * ATTENTION
         * See fireHexagonGrid in ShotTYpeBomb for information about this try
         */
        try{
            pTarget[pY][pX].hit();
            pTarget[pY][pX-1].hit();
            pTarget[pY][pX+1].hit();
            pTarget[pY+1][pX-1].hit();
            pTarget[pY+1][pX+1].hit();
        } catch(java.lang.ArrayIndexOutOfBoundsException ex){
            //Means this square is not in the Target matrix (Out of range)
        }
        return true;
    }
}
