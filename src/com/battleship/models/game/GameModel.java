/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.models.game;

import com.battleship.constants.GameConstants;
import com.battleship.constants.Msg;
import com.battleship.exceptions.ForbiddenAction;





/**
 * <h1>GameModel</h1>
 * <p>
 * public class GameModel
 * </p>
 * 
 * <p>
 This class manage the game. Game is set by a configuration which is 
 a GameConfigModel class.
 </p>
 *
 * @date    Feb 9, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public class GameModel implements GameConstants{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private     final int               gridType;
    private     final int               gridWidth;
    private     final int               gridHeight;
    private     Player[]                listPlayers;
    private     int                     counterTurn;
    private     int                     currentNbPlayers;
    private     int                     currentPlayerTurn;
    private     FleetGridModel          currentEnemyFleetGrid;
    
    
    
    
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Constructor: create a new GameModel from a GameConfigModel class<br/>
 Set the configuration from GameConfigModel. After creation, basement config 
     * could not be changed anymore (As gridWidth, gridHeight and gridType)
     * @param pConfig configuration to set
     */
    public GameModel(GameConfigModel pConfig) {
        this.gridWidth              = pConfig.getGridWidth();
        this.gridHeight             = pConfig.getGridHeight();
        this.gridType               = pConfig.getGridType();
        
        this.counterTurn            = 0;
        this.currentNbPlayers       = 0;
        this.currentPlayerTurn      = 0;
        this.currentEnemyFleetGrid  = null;
        
        this.listPlayers            = new Player[NB_MAX_PLAYERS];
        this.listPlayers[0]         = null; //If more than 2 players => Create in loop
        this.listPlayers[1]         = null;
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Add this player in the game. If game is already full, this function throws 
     * an exception (And player is not added). <br/>
     * If player successfully added, the fleetGrid for this player is
     * initialized (En will create a specific grid in function of gridType)
     * @param pPlayer player to add
     * @throws ForbiddenAction if no empty place remaining
     */
    public void addPlayer(Player pPlayer) throws ForbiddenAction{
        if(this.isFull()){
            throw new ForbiddenAction(Msg.ERR_UNABLE_ADD_PLAYER);
        }
        //Add player and then, create the player fleet Grid
        this.listPlayers[this.currentNbPlayers] = pPlayer;
        this.listPlayers[this.currentNbPlayers].initFleetGrid(this.gridWidth, 
                                                              this.gridHeight, 
                                                              this.gridType);
        this.currentNbPlayers++;//Means 'Player added'
    }
    
    
    /**
     * Check if the game is full of players. Means it check if there is one more 
     * empty place for a new player
     * @return true if is full, otherwise, return false
     */
    public boolean isFull(){
        //Must be never higher than listPlayers.length, but, one never knows
        return this.currentNbPlayers >= this.listPlayers.length;
    }
    
    
    private void switchTurnBehaviors() {
        
    }
    
    
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return current players number in this game
     * @return current number players
     */
    public int getCurrentNbPlayers(){
        return this.currentNbPlayers;
    }
}
