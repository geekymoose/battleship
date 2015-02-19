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
        for(int i=-1; i<2; i++){
            for(int j=-1; j<2; j++){
                try{
                    pTarget[pX+i][pY+j].hit();
                } catch(java.lang.ArrayIndexOutOfBoundsException ex){
                    //Means this square is not in the Target matrix (Out of range)
                }
            }
        }
        return true;
    }

    @Override
    public boolean fireHexagonGrid(int pX, int pY, Target[][] pTarget) {
        /*
         * ATTENTION!!!!!!!
         * I'm not sure it's going to work. I don't remember me if try will try 
         * every one even if the first one throws exeception or if it will stop 
         * at the first throw. (In this case, we have to do in another way)
         */
        try{
            pTarget[pY-1][pX].hit();
            pTarget[pY][pX-1].hit();
            pTarget[pY][pX].hit();
            pTarget[pY][pX+1].hit();
            pTarget[pY+1][pX-1].hit();
            pTarget[pY+1][pX].hit();
            pTarget[pY+1][pX+1].hit();
        } catch(java.lang.ArrayIndexOutOfBoundsException ex){
            //Means this square is not in the Target matrix (Out of range)
        }
        return true;
    } 
}
