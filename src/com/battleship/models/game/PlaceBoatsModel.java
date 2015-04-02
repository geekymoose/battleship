/*
 * Class :      PlaceBoatsModel
 * Creation:    Apr 2, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.models.game;

import com.battleship.constants.GameConstants;
import com.battleship.exceptions.ExecError;





/**
 * <h1>PlaceBoatsModel</h1>
 * <p>
 * public class PlaceBoatsModel<br/>
 * extends Model<br/>
 * implements GameConstants
 * </p>
 *
 * @author Constantin MASSON
 * @date Apr 2, 2015
 */
public class PlaceBoatsModel extends Model implements GameConstants{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private     GameConfigModel     config;
    private     FleetGridModel      grid;
    private     int                 playerTurn;
    
    
    
    
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new PlaceBoatsModel use to place user boats. There are 2 players 
     * (Means there are 2 FleetGridModel)
     * @param pConfig
     * @throws ExecError 
     */
    //public PlaceBoatsModel(GameConfigModel pConfig, FleetGridModel[] pFleets) throws ExecError{
    public PlaceBoatsModel(GameConfigModel pConfig) throws ExecError{
        if (pConfig == null){
            throw new ExecError();
        }
        this.config         = pConfig;
        this.playerTurn     = 0;
        this.createGrid();
    }
    
    
    
    
    

    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Create grid for current player
     */
    private void createGrid(){
        int width       = this.config.getGridWidth();
        int height      = this.config.getGridHeight();
        Player owner    = this.config.getPlayers()[this.playerTurn];
        
        switch(this.config.getGridType()){
            case GRID_TYPE_SQUARE:
                this.grid = new FleetGridSquare(width, height, owner);
                break;
            case GRID_TYPE_HEXAGON:
                this.grid = new FleetGridHexagon(width, height, owner);
                break;
        }
        this.notifyObservers(null);
    }
    
    
    
    
    

    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return type grid
     * @return 
     */
    public int getGridType(){
        return this.config.getGridType();
    }
    
    /**
     * Return grid Width
     * @return 
     */
    public int getGridWidth(){
        return this.config.getGridWidth();
    }
    
    /**
     * Return grid height
     * @return 
     */
    public int getGridHeight(){
        return this.config.getGridHeight();
    }
}
