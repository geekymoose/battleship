/* 
 * Creation:    Feb 25, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.controllers;

import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import com.battleship.models.game.GameModel;





/**
 * <h1>PlaceBoatsController</h1>
 * <p>public class PlaceBoatsController</p>
 * 
 * <p>Controller</p>
 *
 * @date    Feb 25, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public class PlaceBoatsController extends Controller{
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
    public PlaceBoatsController(GameModel pModel) throws ExecError{
        super(pModel);
        DebugTrack.showInitMsg("Create GameConfigController controller");
    }
    
    
    
    
    

    //**************************************************************************
    // Functions
    //**************************************************************************
    
    
    
    
    

    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
}
