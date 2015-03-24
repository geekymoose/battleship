/*
 * Class :      GameController
 * Creation:    Mar 24, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.controllers;

import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import com.battleship.models.game.GameModel;





/**
 * <h1>GameController</h1>
 * <p>
 * public class GameController<br/>
 * extends Controller
 * </p>
 *
 * @author Constantin MASSON
 * @date Mar 24, 2015
 */
public class GameController extends Controller{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    
    
    
    
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new controller for game model and game panel view
     * @param pModel Model managed by this controller
     * @throws ExecError throws if pView or pMode is null
     */
    public GameController(GameModel pModel) throws ExecError{
        super(pModel);
        DebugTrack.showInitMsg("Create GameController controller");
    }
    
    
    
    
    

    //**************************************************************************
    // Functions
    //**************************************************************************
    
    
    
    
    

    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
}
