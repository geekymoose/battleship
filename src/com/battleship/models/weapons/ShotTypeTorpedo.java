/*
 * Class :      ShotTypeTorpedo
 * Creation:    Feb 15, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.models.weapons;

import com.battleship.behaviors.Target;
import com.battleship.models.game.FleetGridModel;





/**
 * <h1>ShotTypeTorpedo</h1>
 * <p>
 * public class ShotTypeTorpedo<br/>
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
public class ShotTypeTorpedo implements ShotType{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    
    
    
    
    

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
        if(pTarget[pY][pX].isValideTarget()){
            pTarget[pY][pX].hit();
            pTarget[pY-1][pX].hit();
            pTarget[pY+1][pX].hit();
            pTarget[pY][pX-1].hit();
            pTarget[pY][pX+1].hit();
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
            pTarget[pY][pX-1].hit();
            pTarget[pY][pX+1].hit();
            pTarget[pY+1][pX-1].hit();
            pTarget[pY+1][pX+1].hit();
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
