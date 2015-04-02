/* 
 * Creation:    Feb 25, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.controllers;

import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import com.battleship.models.game.GameConfigModel;
import com.battleship.models.game.PlaceBoatsModel;





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
    
    
    
    
    

    //**************************************************************************
    // Functions
    //**************************************************************************
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
