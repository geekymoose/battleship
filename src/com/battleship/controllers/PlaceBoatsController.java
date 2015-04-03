/* 
 * Creation:    Feb 25, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.controllers;

import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import com.battleship.models.game.PlaceBoatsModel;
import com.battleship.models.game.Player;





/**
 * <h1>PlaceBoatsController</h1>
 * <p>
 public class PlaceBoatsController<br/>
 * extends controller
 * </p>
 * <p>Controller for a fleet grid</p>
 *
 * @date    Feb 25, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public class PlaceBoatsController extends GridController{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    
    
    
    
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new Controller for PlaceBoatsPanel
     * @param pModel Model managed by this controller
     * @throws ExecError throws if pView or pMode is null
     */
    public PlaceBoatsController(PlaceBoatsModel pModel) throws ExecError{
        super(pModel);
        DebugTrack.showInitMsg("Create GameConfigController controller");
    }
    
    public void initGrid(){
        ((PlaceBoatsModel)this.model).initGrid();
    }
    
    
    
    
    

    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Reset current player grid
     */
    public void resetFleetGrid(){
        ((PlaceBoatsModel)this.model).resetPlayerGrid();
    }
    
    /**
     * Try to valid current player fleet. Check if fleet is valid. If ok, 
     * add fleet to player and return true
     * @return true if successfully added, otherwise, return false (Grid not yet valid)
     */
    public boolean acceptGrid(){
        return ((PlaceBoatsModel)this.model).setPlayerFleetGrid(); 
    }
    
    /**
     * Check if all player are placed
     * @return true if all placed, otherwise, return false
     */
    public boolean areAllPlayerPlaced(){
        return ((PlaceBoatsModel)this.model).areAllPlayerPlaced();
    }
    
    
    
    
    

    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    public Player getCurrentPlayer(){
        return ((PlaceBoatsModel)this.model).getCurrentPlayer();
    }
    /**
     * Return type grid
     * @return 
     */
    public int getGridType(){
        return ((PlaceBoatsModel)this.model).getGridType();
    }
    
    /**
     * Return grid Width
     * @return 
     */
    public int getWidth(){
        return ((PlaceBoatsModel)this.model).getGridWidth();
    }
    
    /**
     * Return grid height
     * @return 
     */
    public int getHeight(){
        return ((PlaceBoatsModel)this.model).getGridHeight();
    }
}
