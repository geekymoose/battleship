/*
 * Class :      Weapon
 * Creation:    Feb 15, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.models.weapons;

import com.battleship.behaviors.Target;
import com.battleship.models.game.FleetGridModel;
import com.battleship.models.game.FleetGridSquare;
import com.battleship.models.game.Player;





/**
 * <h1>Weapon</h1>
 * <p>public abstract class Weapon</p>
 *
 *
 * 
 * @date Feb 15, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public abstract class Weapon {
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    protected int ammo;
    protected final ShotType shot;
    protected Player owner;
    
    
    
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    protected Weapon(int pAmmo, ShotType pShot, Player pOwner){
        this.ammo       = pAmmo;
        this.shot       = pShot;
        this.owner      = pOwner;
    }
    
    
    
    
    

    //**************************************************************************
    // Functions
    //**************************************************************************
    protected boolean fireAt(int pX, int pY, Target[][] pTarget, FleetGridModel grid){
        if(grid instanceof FleetGridSquare){
            return shot.fireSquareGrid(pX, pY, pTarget);
        }
        else{
            return shot.fireHexagonGrid(pX, pY, pTarget);
        }
    }
    
    
    
    

    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    protected void setAmmo(int pAmmo){
        this.ammo=pAmmo;
    }
    
    protected void setOwner(Player pOwner){
        this.owner=pOwner;
    }
}
