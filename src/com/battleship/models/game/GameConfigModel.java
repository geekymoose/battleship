/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.models.game;

import com.battleship.constants.GameConstants;
import com.battleship.main.DebugTrack;
import com.battleship.asset.Config;
import com.battleship.asset.Session;
import com.battleship.exceptions.ForbiddenAction;





/**
 * <h1>GameConfigModel</h1>
 * <p>
 * public class GameConfigModel<br/>
 * extends Model<br/>
 * implements GameConstants
 * </p>
 * 
 * <p>
 * Configuration for a new GameModel: it keeps the game configuration.<br/>
 * A new game will be created later with this configuration class.
 * </p>
 * <h2>Configure options</h2>
 * <ul>
 *  <li>Grid width: int number</li>
 *  <li>Grid height: int number</li>
 *  <li>Grid type: square or hexagon</li>
 * </ul>
 * 
 *
 * @since   Feb 9, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 * 
 * @see GameModel
 */
public class GameConfigModel extends Model implements GameConstants{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private     final int       gridDefaultWidth;
    private     final int       gridDefaultHeight;
    private     final int       gridDefaultType;
    
    private     final int       gridMinWidth;
    private     final int       gridMinHeight;
    
    private     final int       gridMaxWidth;
    private     final int       gridMaxHeight;
    
    private     int             gridWidth;
    private     int             gridHeight;
    private     int             gridType;
    
    private     int             nbMaxPlayer;
    private     int             nbMinPlayer;
    private     Player[]        listPlayers;
    private     int             currentNbPlayers;
    
    private     int             firstPlayer; //Player which start to play
    
    private     String          title;
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new empty GameConfig. All data are loaded with default values.
     */
    public GameConfigModel(){
        DebugTrack.showInitMsg("Create GameConfigModel Model");
        this.gridDefaultWidth   = Config.getDimValues_int("grid-default-width");
        this.gridDefaultHeight  = Config.getDimValues_int("grid-default-height");
        this.gridMinWidth       = Config.getDimValues_int("grid-min-width");
        this.gridMaxWidth       = Config.getDimValues_int("grid-max-width");
        this.gridMinHeight      = Config.getDimValues_int("grid-min-height");
        this.gridMaxHeight      = Config.getDimValues_int("grid-max-height");
        
        this.gridDefaultType    = GRID_TYPE_SQUARE;
        this.initPlayer();
        this.defaultConfig();
    }
    
    /**
     * Initialize the players. Depending of game mode. First player is the 
     * session player
     * <ul>
     *  <li>Mode AI : will create an AI player</li>
     *  <li>Mode V2 : will create second human player</li>
     *  <li>Mode LAN : no player created, use add(Player)</li>
     *  <li>Mode INTERNET : no player created, use add(Player)</li>
     * </ul>
     */
    private void initPlayer(){
        this.nbMaxPlayer        = Config.getGameValues_int("nb-max-players");
        this.nbMinPlayer        = Config.getGameValues_int(("nb-min-players"));
        this.listPlayers        = new Player[this.nbMaxPlayer];
        Session.setPlayer(new PlayerHuman(Session.getListWeapons()));
        try {
            Session.getPlayer().setName("SessionPlayer");
        } catch(ForbiddenAction ex) {
        }
        switch(Session.getGameMode()){
            case MODE_AI:
                this.listPlayers[0]     = Session.getPlayer();
                this.listPlayers[1]     = new PlayerAI();
                this.currentNbPlayers   = 2;
                break;
            case MODE_V2:
                this.listPlayers[0]     = Session.getPlayer();
                this.listPlayers[1]     = new PlayerHuman();
                this.currentNbPlayers   = 2;
                break;
            case MODE_LAN:
                this.listPlayers[0]     = Session.getPlayer();
                this.listPlayers[1]     = new PlayerLan();
                this.currentNbPlayers   = 1;
                break;
        }
    }
    
    /**
     * Set configuration to default values
     */
    private void defaultConfig(){
        this.gridWidth      = this.gridDefaultWidth;
        this.gridHeight     = this.gridDefaultHeight;
        this.gridType       = this.gridDefaultType;
        this.firstPlayer    = Config.getGameValues_int("default-first-player");
        this.notifyObserversModel(null);
    }
    
    /**
     * Reset the current configuration. Every value will be set toward default 
     * value.
     */
    public void resetConfig(){
        this.defaultConfig();
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * @deprecated Not used 
     * Add this player in the game. If game is already full, this function throws 
     * an exception (And player is not added). <br/>
     * If player successfully added, the fleetGrid for this player is
     * initialized (En will create a specific grid in function of gridType)
     * @param pPlayer player to add
     * @throws ForbiddenAction if no empty place remaining
     */
    public void addPlayer(Player pPlayer) throws ForbiddenAction{
        if(this.isFull()){
            throw new ForbiddenAction("Unable to join the party: it's full");
        }
        //Add player and then, create the player fleet Grid
        this.listPlayers[this.currentNbPlayers] = pPlayer;
        this.currentNbPlayers++;//Means 'Player added'
    }
    
    
    
    
    
    //**************************************************************************
    // Check Functions
    //**************************************************************************
    /**
     * Check if current configuration is default config
     * @return true if default config, otherwise,return false
     */
    public boolean isDefaultConfig(){
        return  this.gridWidth  == this.gridDefaultWidth   &&
                this.gridHeight == this.gridDefaultHeight  &&
                this.gridType   == this.gridDefaultType;
    }
    
    /**
     * Check if configuration is valid. Means number players is between min number 
     * players (Include) and max number players (Include)
     * @return true if valid, otherwise, return false
     */
    public boolean isValid(){
        
        return currentNbPlayers>=nbMinPlayer && currentNbPlayers<=nbMaxPlayer;
    }
    
    /**
     * Check if player are ready
     * @return return true if ready, otherwise, return false
     */
    public boolean areAllFleetValid(){
        for(Player p : this.listPlayers){
            if(p.getFleet()==null || p.getFleet().isValidFleetGrid() == false){
                DebugTrack.showObjectToString(p);
                return false;
            }
        }
        return true;
    }
    
    
    /**
     * Check if the game is full of players. Means it check if there is one more 
     * empty place for a new player
     * @return true if is full, otherwise, return false
     */
    public boolean isFull(){
        //Should be never higher than listPlayers.length, but, one never knows
        return this.currentNbPlayers >= this.listPlayers.length;
    }
    
    
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return the current grid width
     * @return grid with
     */
    public int getGridWidth() {
        return this.gridWidth;
    }

    /**
     * Return the current grid height
     * @return grid height
     */
    public int getGridHeight() {
        return this.gridHeight;
    }
    
    /**
     * Return current grid type (Ex: SQUARE or HEXAGON)
     * @return  int grid type
     */
    public int getGridType() {
        return this.gridType;
    }
    
    /**
     * Return tab with all players
     * @return Player[] tab with all players
     */
    public Player[] getPlayers(){
        return this.listPlayers;
    }
    
    /**
     * Return current number players in this game config 
     * @return int number of players in the game
     */
    public int getNbPlayers(){
        return this.listPlayers.length;
    }
    
    /**
     * Return id of the player which will play first
     * @return int id player
     */
    public int getFirstPlayerTurn(){
        return this.firstPlayer;
    }
    
    /**
     * Return id second player
     * @return int id player
     */
    public int getSecondPlayerTurn(){
        switch(this.firstPlayer){
            case 0:
                return 1;
            default:
                return 0;
        }
    }
    
    /**
     * Return the game title
     * @return String title of the game
     */
    public String getTitle(){
        return this.title;
    }
    
    //**************************************************************************
    /**
     * Set id of the player which is going to play first. If not valid id, 
     * do nothing
     * @param pValue id player
     */
    public void setFirstPlayerTurn(int pValue){
        if (pValue>=0 || pValue < this.currentNbPlayers){
            this.firstPlayer = pValue;
        }
    }
    
    /**
     * Set a new grid width, will replace the old one. Must be between 
     * minimum value and maximum value otherwise, do nothing
     * @param pValue new width
     */
    public void setGridWidth(int pValue) {
        if(pValue>=this.gridMinWidth && pValue<=this.gridMaxWidth){
            this.gridWidth = pValue;
        }
        this.notifyObserversModel(null);
    }

    /**
     * Set a new gridWidth, will replace the old one. Must be between 
     * minimum value and maximum value otherwise, do nothing
     * @param pValue new width
     */
    public void setGridHeight(int pValue) {
        if(pValue>=this.gridMinHeight && pValue<=this.gridMaxHeight){
            this.gridHeight = pValue;
        }
        this.notifyObserversModel(null);
    }

    /**
     * Set a new Grid type. Must be a valid type, otherwise, do nothing.
     * @param pValue new width
     */
    public void setGridType(int pValue) {
        if(pValue == GRID_TYPE_SQUARE || pValue == GRID_TYPE_HEXAGON){
            this.gridType = pValue;
        }
        this.notifyObserversModel(null);
    }
    
    /**
     * Set new game title
     * @param pValue title to set
     * @throws ForbiddenAction thrown if to long or null
     */
    public void setTitle(String pValue) throws ForbiddenAction{
        int maxLength = Config.getGameValues_int("game-title-max-length");
        if(pValue.length() > maxLength || pValue.length() == 0){
            throw new ForbiddenAction("Title is too long");
        }
        this.title = pValue;
    }
}
