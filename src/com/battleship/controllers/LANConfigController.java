/*
 * Creation :   24 mars 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.controllers;

import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import com.battleship.models.game.LANConfigModel;





/**
 * <h1>LANConfigController</h1>
 * <p>
 * public class LANConfigController<br/>
 * </p>
 * 
 * <p>Description</p>
 * 
 * @since   24 mars 2015
 * @author  Contsantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class LANConfigController extends Controller {
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new controller for LANConfig
     * @param pModel Model managed by this controller
     * @throws ExecError throws if pView or pMode is null
     */
    public LANConfigController(LANConfigModel pModel) throws ExecError{
        super(pModel);
        DebugTrack.showInitMsg("Create LANConfigController controller");
    }
    
    
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Reset configuration to default state
     */
    public void resetDefaultConfig(){
        ((LANConfigModel)this.m).resetConfig();
    }
    
    /**
     * Check if config is valid
     * @return true if valid, otherwise, return false
     */
    public boolean isValidConfig(){
        return ((LANConfigModel)this.m).isValid();
    }
}
