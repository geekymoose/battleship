/* 
 * Creation:    Feb 25, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.controllers;

import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import com.battleship.models.game.GameConfigModel;
import com.battleship.views.app.GameConfigPanel;





/**
 * <h1>GameConfigController</h1>
 * <p>
 * public class GameConfigController<br/>
 * extends Controller
 * </p>
 * 
 * <p>Controller for ConfigGame model</p>
 *
 * @date    Feb 25, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 * @see     GameConfigModel
 * @see     GameConfigView
 */
public class GameConfigController extends Controller{
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new controller for GameConfig
     * @param pView     View linked with this controller
     * @param pModel    Model managed by this controller
     * @throws ExecError throws if pView or pMode is null
     */
    public GameConfigController(GameConfigPanel pView, GameConfigModel pModel) throws ExecError{
        super(pView, pModel);
        DebugTrack.displayMsg(" * Create GameConfigController controller");
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Reset configuration to default state
     */
    public void resetDefaultConfig(){
        ((GameConfigModel)this.model).resetConfig();
    }
    /**
     * Change current grid width by new value.
     * See model function for further information
     * @param pValue new value
     */
    public void changeGridWidth(int pValue){
        ((GameConfigModel)this.model).setGridWidth(pValue);
    }
    
    /**
     * Change current grid height by new value.
     * See model function for further information
     * @param pValue new value
     */
    public void changeGridHeight(int pValue){
        ((GameConfigModel)this.model).setGridHeight(pValue);
    }
    
    /**
     * Change current grid type by new one.
     * See model function for further information
     * @param pValue new value
     */
    public void changeGridType(int pValue){
        ((GameConfigModel)this.model).setGridType(pValue);
    }
}
