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
 * @date    Feb 15, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public abstract class Weapon {
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    protected int               ammo;
    protected final ShotType    shot;
    protected Player            owner;
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new weapon
     * @param pAmmo     weapon start ammo
     * @param pShot     ShotType
     * @param pOwner    weapon owner
     */
    protected Weapon(ShotType pShot, Player pOwner, int pAmmo){
        this.ammo       = pAmmo;
        this.shot       = pShot;
        this.owner      = pOwner;
    }
    
    
    
    
    

    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Process a fire on the target from Target matrix at position pX:pY. FleetGridModel 
     * change the weapon behavior (For example, the range and explosion shape), 
     * therefore it is needed
     * @param pX x target coordinate in Target matrix
     * @param pY y target coordinate in Target matrix
     * @param pTarget matrix where the target is located
     * @param grid type grid (It is the grid instance)
     * @return true if target hit, otherwise, return false (Target could be missed)
     */
    public boolean fireAt(int pX, int pY, Target[][] pTarget, FleetGridModel grid){
        if(grid instanceof FleetGridSquare){
            return shot.fireSquareGrid(pX, pY, pTarget);
        }
        else{
            return shot.fireHexagonGrid(pX, pY, pTarget);
        }
    }
    
    /**
     * Add bullet in current ammo (Will add in the current number)
     * @param pValue ammo to add
     * @return true if added successfully, otherwise, return false (Max ammo reached)
     */
    public boolean addAmmo(int pValue){
        if(pValue<0){
            return false;
        }
        this.ammo += pValue;
        return true;
    }
    
    
    
    

    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return current ammo
     * @return current ammo
     */
    public int getAmmo(){
        return this.ammo;
    }
}
