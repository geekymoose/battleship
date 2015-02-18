/*
 * Class :      ShotTypeMissile
 * Creation:    Feb 15, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.models.weapons;

import com.battleship.behaviors.Target;
import com.battleship.models.game.FleetGridModel;





/**
 * <h1>ShotTypeMissile</h1>
 * <p>
 * public class ShotTypeMissile<br/>
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
public class ShotTypeMissile implements ShotType{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    
    
    
    
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    public ShotTypeMissile(){
    
    }
    
    
    
    
    

    //**************************************************************************
    // Functions
    //**************************************************************************
    @Override
    public boolean fireSquareGrid(int pX, int pY, Target[][] pTarget) {
        if(pTarget[pY][pX].isValideTarget()){
            pTarget[pY][pX].hit();
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean fireHexagonGrid(int pX, int pY, Target[][] pTarget) {
        if(pTarget[pY][pX].isValideTarget()){
            pTarget[pY][pX].hit();
            return true;
        }
        else{
            return false;
        }
    }
    
    
    
    

    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
}
