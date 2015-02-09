/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.models;





/**
 * <h1>FleetGridHexagon</h1>
 * <p>
 * public class FleetGridHexagon<br/>
 * extends FleetGridModel
 * </p>
 * 
 *
 * @date    Feb 9, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public class FleetGridHexagon extends FleetGridModel{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    
    
    
    
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new FleetGrid with hexagon coordinates
     * @param pWidth    Grid width
     * @param pHeight   Grid Height
     * @param pOwner    FleetGrid owner
     */
    public FleetGridHexagon(int pWidth, int pHeight, Player pOwner){
        super(pWidth, pHeight, pOwner);
    }
    
    
    
    
    

    //**************************************************************************
    // Functions
    //**************************************************************************
    @Override
    public BoxMap getNextBoxMap(int pX, int pY){
        return null; //To Do
    }
    
    
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
}
