/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.models.game;

import com.battleship.constants.GameConstants;
import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;





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
    }
    
    /**
     * Initialize grid at the start but must be called after object creation
     */
    public void initGrid(){
        this.createGrid();
    }
    
    /**
     * Reset current player fleet grid
     */
    public void resetPlayerGrid(){
        DebugTrack.showObjectToString(this.config.getPlayers()[this.playerTurn].getFleet());
        this.config.getPlayers()[this.playerTurn].getFleet().resetFleetGrid();
    }
    
    
    
    
    

    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Create grid model without data, owner will be added later, when grid is 
     * valid.
     */
    private void createGrid(){
        int width       = this.config.getGridWidth();
        int height      = this.config.getGridHeight();
        
        switch(this.config.getGridType()){
            case GRID_TYPE_SQUARE:
                this.grid = new FleetGridSquare(width, height, null);
                break;
            case GRID_TYPE_HEXAGON:
                this.grid = new FleetGridHexagon(width, height, null);
                break;
        }
        this.config.getPlayers()[this.playerTurn].setFleetGrid(this.grid);
        this.notifyObservers(null);
        DebugTrack.showObjectToString(this.grid);
    }
    
    
    /**
     * Try to set grid to current player. Player has to place boats in grid displayer, 
     * then, he try to accept this grid and place it in his owned grid. If grid 
     * is not yet valid (Some boats are not placed), return false and do nothing, 
     * otherwise, add grid to player and switch player turn 
     * @return 
     */
    public boolean setPlayerFleetGrid(){
        if (this.grid.isValidFleetGrid()){
            //this.config.getPlayers()[playerTurn].setFleetGrid(grid);
            this.playerTurn++;
            this.grid = null;
            
            //If there is one more player to place his grid
            if(this.isLastPlayer() == false){
                this.createGrid();
            }
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Check if current player who place boats is the last
     * @return 
     */
    public boolean isLastPlayer(){
        return this.playerTurn >= (this.config.getNbPlayers()-1);
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
    
    /**
     * Return current player. Means the player who has to place his boats on the grid
     * @return player turn 
     */
    public Player getCurrentPlayer(){
        return this.config.getPlayers()[this.playerTurn];
    }
}
