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
 * @date Feb 15, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public class ShotTypeBomb implements ShotType{
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    public ShotTypeBomb(){
    }
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    @Override
    public boolean fireSquareGrid(int pX, int pY, Target[][] pTarget) {
        if(pTarget[pX][pY].isValidTarget()){
            for(int i=-1; i<2; i++){
                for(int j=-1; j<2; j++){
                    pTarget[pX+i][pY+j].hit();
                }
            }
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean fireHexagonGrid(int pX, int pY, Target[][] pTarget) {
        if(pTarget[pX][pY].isValidTarget()){
            pTarget[pY-1][pX].hit();
            pTarget[pY][pX-1].hit();
            pTarget[pY][pX].hit();
            pTarget[pY][pX+1].hit();
            pTarget[pY+1][pX-1].hit();
            pTarget[pY+1][pX].hit();
            pTarget[pY+1][pX+1].hit();
            return true;
        }
        else{
            return false;
        }
    } 
}
