/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.models.game;

import com.battleship.main.DebugTrack;





/**
 * <h1>GameModel</h1>
 * <p>
 * public class GameModel<br/>
 * extends Model
 * </p>
 * 
 * <p>
 * This class manage the game. Game is set by a configuration which is 
 * a GameConfigModel class.
 * </p>
 *
 * @date    Feb 9, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public class GameModel extends Model{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private     GameConfigModel         config;
    private     final int               gridType;
    private     final int               gridWidth;
    private     final int               gridHeight;
    private     Player[]                listPlayers;
    private     int                     counterTurn;
    private     int                     currentPlayerTurn;
    
    
    
    
    

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
        DebugTrack.showInitMsg("Create GameModel");
        this.config             = pConfig;
        this.gridWidth          = pConfig.getGridWidth();
        this.gridHeight         = pConfig.getGridHeight();
        this.gridType           = pConfig.getGridType();
        this.listPlayers        = pConfig.getPlayers();
        
        this.counterTurn        = 0;
        this.currentPlayerTurn  = 0;
    }
    
    public void initMap(){
        
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    private void switchTurnBehaviors() {
        
    }
    
    
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return game config used by this game
     * @return 
     */
    public GameConfigModel getConfig(){
        return this.config;
    }
}
