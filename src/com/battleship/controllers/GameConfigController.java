/* 
 * Creation:    Feb 25, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.controllers;

import com.battleship.exceptions.ExecError;
import com.battleship.exceptions.ForbiddenAction;
import com.battleship.main.DebugTrack;
import com.battleship.models.game.GameConfigModel;
import com.battleship.models.game.Player;
import com.battleship.models.game.PlayerAI;
import com.battleship.models.game.PlayerAI.Difficulty;





/**
 * <h1>GameConfigController</h1>
 * <p>
 * public class GameConfigController<br/>
 * extends Controller
 * </p>
 * 
 * <p>Controller for ConfigGame model</p>
 *
 * @since   Feb 25, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 * 
 * @see     GameConfigModel
 * @see     GameConfigView
 */
public class GameConfigController extends Controller{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private GameConfigModel model = (GameConfigModel)this.m;
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new controller for GameConfig
     * @param pModel Model managed by this controller
     * @throws ExecError throws if pView or pMode is null
     */
    public GameConfigController(GameConfigModel pModel) throws ExecError{
        super(pModel);
        DebugTrack.showInitMsg("Create GameConfigController controller");
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Reset configuration to default state
     */
    public void resetDefaultConfig(){
        this.model.resetConfig();
    }
    
    /**
     * Check if config is valid
     * @return true if valid, otherwise, return false
     */
    public boolean isValidConfig(){
        return this.model.isValid();
    }
    
    /**
     * Change current grid width by new value.
     * See model function for further information
     * @param pValue new value
     */
    public void changeGridWidth(int pValue){
        this.model.setGridWidth(pValue);
    }
    
    /**
     * Change current grid height by new value.
     * See model function for further information
     * @param pValue new value
     */
    public void changeGridHeight(int pValue){
        this.model.setGridHeight(pValue);
    }
    
    /**
     * Change current grid type by new one.
     * See model function for further information
     * @param pValue new value
     */
    public void changeGridType(int pValue){
        this.model.setGridType(pValue);
    }
    
    /*
     * Set game title 
     */
    public void setTitle(String pValue) throws ForbiddenAction{
        this.model.setTitle(pValue);
    }
    
    public void setAIDifficulty(Difficulty pValue){
        ((PlayerAI)this.model.getPlayers()[1]).setDifficulty(pValue);
    }
}
