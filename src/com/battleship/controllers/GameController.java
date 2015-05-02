/*
 * Class :      GameController
 * Creation:    Mar 24, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.controllers;

import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import com.battleship.models.game.GameConfigModel;
import com.battleship.models.game.GameModel;
import com.battleship.models.game.Player;
import com.battleship.views.app.GamePanel;





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
    private GameModel model = (GameModel)this.m;
    
    
    
    
    

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
    
    /**
     * Initialize observers for players
     */
    public void initPlayerObservers(){
        for (Player p:this.model.getConfig().getPlayers()){
            p.addObserver((GamePanel)this.v);
        }
        this.model.getConfig().getPlayers();
    }
    
    
    
    

    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Return the game configuration
     * @return GameConfig
     */
    public GameConfigModel getGameConfig(){
        return this.model.getConfig();
    }
    
    /**
     * Return the GameModel 
     * @return GameModel
     */
    public GameModel getGameModel(){
        return this.model;
    }
}
