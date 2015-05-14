/* 
 * Creation:    Feb 25, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.controllers;

import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import com.battleship.models.game.GameConfigModel;
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
 * @since   Feb 25, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public class PlaceBoatsController extends Controller{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private PlaceBoatsModel model = (PlaceBoatsModel)this.m;
    
    
    
    
    

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
    
    public void initPage(){
        this.model.createPlayersGrid();
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Check if current player grid is valid
     * @return true if valid, otherwise, return false
     */
    public boolean isValidGrid(){
        return this.model.getCurrentPlayer().getFleet().isValidFleetGrid();
    }
    
    /**
     * Switch player turn
     * @return -1 invalid grid, 0 last player reached, 1 next player
     */
    public int switchPlayer(){
        return this.model.switchPlayer();
    }
    
    /**
     * Reset current player grid
     */
    public void resetFleetGrid(){
        this.model.resetPlayerGrid();
    }
    
    /**
     * Place all boat owned by current player turn in random position
     */
    public void placeAllBoatsRandom(){
        this.model.placeAllRandom();
    }
    
    /*
     * Select a boat
     */
    public void selectBoat(int pBoatIt){
        this.model.getCurrentPlayer().selectBoat(pBoatIt);
    }
    
    

    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    public Player getCurrentPlayer(){
        return this.model.getCurrentPlayer();
    }
    /**
     * Return type grid
     * @return int grid type
     */
    public int getGridType(){
        return this.model.getGridType();
    }
    
    /**
     * Return grid Width
     * @return int grid width
     */
    public int getWidth(){
        return this.model.getGridWidth();
    }
    
    /**
     * Return grid height
     * @return int grid height
     */
    public int getHeight(){
        return this.model.getGridHeight();
    }
    
    /**
     * Return the game configuration
     * @return GameConfig
     */
    public GameConfigModel getGameConfig(){
        return this.model.getConfig();
    }
}
