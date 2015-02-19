/*
 * Class :      ShotTypeNuclearBomb
 * Creation:    Feb 15, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.models.weapons;

import com.battleship.behaviors.Target;





/**
 * <h1>ShotTypeNuclearBomb</h1>
 * <p>
 * public class ShotTypeNuclearBomb<br/>
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
public class ShotTypeNuclearBomb implements ShotType{
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    public ShotTypeNuclearBomb(){
    }
    

    //**************************************************************************
    // Functions
    //**************************************************************************
    @Override
    public boolean fireSquareGrid(int pX, int pY, Target[][] pTarget) {
        if(pTarget[pX][pY].isValidTarget()){
            for(int i=-3; i<4; i++){
                for(int j=-3; j<4; j++){
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
            for(int i=-1; i<2; i++){
                for(int j=-2; j<3; j++){
                    pTarget[pY+i][pX+j].hit();
                }
            }
            pTarget[pY-1][pX].hit();
            pTarget[pY+2][pX-1].hit();
            pTarget[pY+2][pX].hit();
            pTarget[pY+2][pX+1].hit();
            return true;
        }
        else{
            return false;
        }
    }
}
