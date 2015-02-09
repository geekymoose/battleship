/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.models;

import com.battleship.behaviors.Target;
import com.battleship.constants.FleetGridConstants;
import java.util.ArrayList;





/**
 * <h1>Player</h1>
 * <p>
 * public class Player<br/>
 * implements FleetGridConstants
 * </p>
 * 
 * <p>
 * This class represent a player during a battleship game. Data could be loaded 
 * from account or new created but player instance is only for one game.
 * </p>
 *
 * 
 * @date    Feb 9, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public class Player implements FleetGridConstants{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private     String                  name;
    private     FleetGridModel          fleetGrid;
    private     ArrayList<Weapon>       listWeapons;
    private     int                     score;
    private     Weapon                  currentWeapon;
    
    
    
    
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    private Player() {
        this.name           = "";
        this.score          = 0;
        this.listWeapons    = new ArrayList();
        this.fleetGrid      = null;
    }
    
    /**
     * Create the FleetGrid according to the parameter configuration (Could not be
     * modified later)<br/>
     * Parameter pGridType determine which kind of grid will be created
     * @param pGridWidth    Grid Width
     * @param pGridHeight   Grid Height
     * @param pGridType     Grid Type (Could be square or hexagon)
     */
    public void initFleetGrid(int pGridWidth, int pGridHeight, int pGridType) {
        switch(pGridType){
            case GRID_SQUARE:
                this.fleetGrid  = new FleetGridSquare(pGridWidth, pGridHeight, this);
                break;
            case GRID_HEXAGON:
                this.fleetGrid  = new FleetGridHexagon(pGridWidth, pGridHeight, this);
                break;
        }
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Switch player Weapon. get the next one or the previous one (Depend of 
     * controller action, for example, a wheel up/down movement)
     */
    public final void switchWeapon() {
        //To do: switch weapon
    }
    
    /**
     * Try to launch a shot at position pX, pY
     * @param pX
     * @param pY
     * @param pWhere
     * @return true if is able to shoot, otherwise, return false
     */
    public boolean shootAt(int pX, int pY, Target[] pWhere) {
        return this.currentWeapon.fire();
    }
    
    
    
    
    

    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return current player's name
     * @return name of this player
     */
    public final String getName() {
        return this.name;
    }
    
    /**
     * Set player name
     * @param pValue new player name, if is not a String or empty, throw exception
     */
    public final void setName(String pValue) {
        //Add exception and check if valide
    }
}
