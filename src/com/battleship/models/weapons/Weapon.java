/*
 * Class :      Weapon
 * Creation:    Feb 15, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.models.weapons;

import com.battleship.behaviors.Target;
import com.battleship.constants.GameConstants;
import com.battleship.models.game.FleetGridModel;
import com.battleship.models.game.FleetGridSquare;
import com.battleship.models.game.Player;





/**
 * <h1>Weapon</h1>
 * <p>
 * public abstract class Weapon<br/>
 * implements GameConstants
 * </p>
 *
 *
 * 
 * @date    Feb 15, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public abstract class Weapon implements GameConstants{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    protected   final int           id;
    protected   String              name;
    protected   int                 ammo;
    protected   final ShotType      shot;
    protected   Player              owner;
    protected   int                 priceWeapon;
    protected   int                 priceAmmo;
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new weapon
     * @param pId       weapon id
     * @param pAmmo     weapon start ammo
     * @param pShot     ShotType
     * @param pOwner    weapon owner
     */
    protected Weapon(int pId, ShotType pShot, Player pOwner, int pAmmo){
        this.id             = pId;
        this.ammo           = pAmmo;
        this.shot           = pShot;
        this.owner          = pOwner;
        this.priceWeapon    = NO_VALUE;
        this.priceAmmo      = NO_VALUE;
        this.name           = "NoData";
    }
    
    
    
    
    

    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Process a fire on the target from Target matrix at position pX:pY. FleetGridModel 
     * change the weapon behavior (For example, the range and explosion shape), 
     * therefore it is needed
     * @param pX        x target coordinate in Target matrix
     * @param pY        y target coordinate in Target matrix
     * @param pTarget   matrix where the target is located
     * @param grid      type grid (It is the grid instance)
     * @return true if target hit, otherwise, return false (Target could be missed)
     */
    public boolean fireAt(int pX, int pY, Target[][] pTarget, FleetGridModel grid){
        if(this.ammo == 0){
            return false;
        }
        this.ammo--;
        if(grid instanceof FleetGridSquare){
            return shot.fireSquareGrid(pX, pY, pTarget);
        }
        else{
            return shot.fireHexagonGrid(pX, pY, pTarget);
        }
    }
    
    
    /**
     * Aim a target from Target matrix at position pX:pY. FleetGridModel 
     * change the weapon behavior (For example, the range and explosion shape), 
     * therefore it is needed
     * @param pX        x target coordinate in Target matrix
     * @param pY        y target coordinate in Target matrix
     * @param pTarget   matrix where the target is located
     * @param grid      type grid (It is the grid instance)
     * @return true if target hit, otherwise, return false (Target could be missed)
     */
    public boolean aimAt(int pX, int pY, Target[][] pTarget, FleetGridModel grid){
        if(grid instanceof FleetGridSquare){
            return shot.aimSquareGrid(pX, pY, pTarget);
        }
        else{
            return shot.aimHexagonGrid(pX, pY, pTarget);
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
    
    /**
     * Return current weapon id
     * @return int weapon id
     */
    public int getWeaponId(){
        return this.id;
    }
    
    /**
     * Return current weapon price
     * @return int price
     */
    public int getPriceWeapon(){
        return this.priceWeapon;
    }
    
    /**
     * Return current weapon ammo price 
     * @return int ammo price
     */
    public int getPriceAmmo(){
        return this.priceAmmo;
    }
    
    /**
     * Return current weapon name
     * @return String name of the weapon
     */
    public String getName(){
        return this.name;
    }
    
    //**************************************************************************
    /**
     * Change weapon owner
     * @param pOwner 
     */
    public void setOwner(Player pOwner){
        this.owner = pOwner;
    }
    
    
    
    
    
    //**************************************************************************
    // Useful Functions
    //**************************************************************************
    /**
     * Hit the target is exists, otherwise, do nothing and return false
     * @param x         x coordinate
     * @param y         y coordinate
     * @param pTarget   array of target where to shoot
     * @return true if hit, otherwise, return false
     */
    public static boolean hitTargetIfExists(int x, int y, Target[][] pTarget){
        try {
            return pTarget[y][x].hit();
        } catch(java.lang.ArrayIndexOutOfBoundsException ex){
            //Means this square is not in the Target matrix (Out of range)
        }
        return false;
    }
    
    /**
     * Aim the target is exists, otherwise, do nothing and return false
     * @param x         x coordinate
     * @param y         y coordinate
     * @param pTarget   array of target where to aim
     * @return true if aimed successfully, otherwise, return false
     */
    public static boolean aimTargetIfExists(int x, int y, Target[][] pTarget){
        try {
            return pTarget[y][x].aim();
        } catch(java.lang.ArrayIndexOutOfBoundsException ex){
            //Means this square is not in the Target matrix (Out of range)
        }
        return false;
    }
}
