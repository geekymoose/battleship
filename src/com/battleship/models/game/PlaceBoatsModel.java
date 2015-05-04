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
    private     GameConfigModel         config;
    private     int                     playerTurn;
    
    
    
    
    

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
        this.playerTurn     = 0; //Player 0 place boats first
        DebugTrack.showObjectToString(pConfig);
    }
    
    /**
     * Create grid of each player
     */
    public void createPlayersGrid(){
        for(Player p : this.config.getPlayers()){
            this.createPlayerGrid(p);
        }
    }
    
    /**
     * Create grid owned by one player
     * @param pPlayer owner of the new grid
     */
    private void createPlayerGrid(Player pPlayer){
        int width       = this.config.getGridWidth();
        int height      = this.config.getGridHeight();
        switch(this.config.getGridType()){
            case GRID_TYPE_SQUARE:
                pPlayer.setFleetGrid(new FleetGridSquare(width, height, null));
                break;
            case GRID_TYPE_HEXAGON:
                pPlayer.setFleetGrid(new FleetGridHexagon(width, height, null));
                break;
        }
        DebugTrack.showObjectToString(pPlayer.getFleet());
        DebugTrack.showObjectToString(pPlayer);
    }
    
    
    
    
    

    //**************************************************************************
    // Check Functions
    //**************************************************************************
    /**
     * Check if current player who place boats is the last
     * @return true if is last player, otherwise, return false
     */
    private boolean isLastPlayer(){
        return this.playerTurn >= (this.config.getNbPlayers()-1);
    }
    
    
    
    
    

    //**************************************************************************
    // Check Functions
    //**************************************************************************
    /**
     * Reset current player fleet grid
     */
    public void resetPlayerGrid(){
        this.config.getPlayers()[this.playerTurn].getFleet().resetFleetGrid();
    }
    
    /**
     * Place all boats in a random position
     */
    public void placeAllRandom(){
        this.config.getPlayers()[this.playerTurn].placeAllRandomBoat();
    }
    
    /**
     * Switch current player turn. Return an Integer which determine current 
     * PlaceBoat status
     * <ul>
     *  <li>-1 Current player grid is not valid (Some boats are remaining)</li>
     *  <li>0 All player are placed, the current one was the last one</li>
     *  <li>1 Fleet placed and it's turn for next player</li>
     * </ul>
     * @return -1 invalid grid, 0 last player reached, 1 next player
     */
    public int switchPlayer(){
        if(this.getCurrentPlayer().getFleet().isValidFleetGrid() == false){
            return -1;
        }
        else if(this.isLastPlayer()){
            return 0;
        } 
        else{
            this.playerTurn++;
            return 1;
        }
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
    
    /**
     * Return game configuration
     * @return GameConfigModel
     */
    public GameConfigModel getConfig(){
        return this.config;
    }
}
