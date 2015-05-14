/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.models.game;

import com.battleship.asset.CheatCode;
import com.battleship.asset.Session;
import com.battleship.constants.GameConstants;
import com.battleship.main.DebugTrack;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;





/**
 * <h1>GameModel</h1>
 * <p>
 * public class GameModel<br/>
 * extends Model<br/>
 * implements GameConstants
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
public class GameModel extends Model implements GameConstants{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    //Runtime constants
    public      static final int    SWITCH_UPDATE           = 0;
    public      static final int    SWITCH_TURN             = 1;
    public      static final int    SWITCH_PAGE             = 2;
    public      static final int    GAME_OVER               = 3;
    public      static final int    GAME_VICTORY            = 4;
    public      static final int    SWITCH_BEHAVIORS        = 5;
    
    //Variables
    private     GameConfigModel     config;
    private     final int           gridType;
    private     final int           gridWidth;
    private     final int           gridHeight;
    
    private     Player[]            listPlayers;
    private     int                 counterTurn;
    private     int                 currentPlayerTurn;
    
    //Break timer
    private     Timer               breakV1;
    private     Timer               breakV2;
    private     Timer               breakLan;
    
    
    
    
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Constructor: create a new GameModel from a GameConfigModel class<br/>
     * Set the configuration from GameConfigModel. After creation, basement config 
     * could not be changed anymore (As gridWidth, gridHeight and gridType)
     * @param pConfig configuration to set
     */
    public GameModel(GameConfigModel pConfig) {
        DebugTrack.showInitMsg("Create GameModel");
        CheatCode.setData(this);
        this.config             = pConfig;
        this.gridWidth          = pConfig.getGridWidth();
        this.gridHeight         = pConfig.getGridHeight();
        this.gridType           = pConfig.getGridType();
        this.listPlayers        = pConfig.getPlayers();
        
        this.counterTurn        = 1;
        this.currentPlayerTurn  = pConfig.getFirstPlayerTurn();
        
        //Add this GameModel in every player and Fleet of player
        for (Player p : this.listPlayers){
            p.setGameModel(this);
            p.getFleet().setGame(this);
        }
        this.setBreakTimers();
    }
    
    /*
     * Create and set break timer. This timer create a break between two 
     * player turn. Action created by switch is set using game mode
     */
    private void setBreakTimers(){
        this.breakV1 = new Timer(GameConstants.DELAY_SWITCH_BREAK, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                switchAiModeTurn();
            }
        });
        this.breakV2 = new Timer(GameConstants.DELAY_SWITCH_BREAK, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                switchV2ModeTurn();
            }
        });
        this.breakLan = new Timer(GameConstants.DELAY_SWITCH_BREAK, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                switchLanModeTurn();
            }
        });
    }
    
    
    
    
    
    //**************************************************************************
    // Switch turn Functions
    //**************************************************************************
    /**
     * Switch turn behavior. It depend of current game mode
     * <ul>
     *  <li>Mode AI : start AI shoot</li>
     *  <li>Mode V2 : display a switching panel</li>
     *  <li>Mode LAN : do nothing, juste wait for other player shot</li>
     * </ul>
     */
    public void switchTurnBehaviors(){
        switch(Session.getGameMode()){
            case GameConstants.MODE_AI:
                notifyObservers(GameModel.SWITCH_BEHAVIORS);
                this.breakV1.start();
                break;
                
            case GameConstants.MODE_V2:
                notifyObservers(GameModel.SWITCH_BEHAVIORS);
                this.breakV2.start();
                break;
                
            case GameConstants.MODE_LAN:
                notifyObservers(GameModel.SWITCH_BEHAVIORS);
                this.breakLan.start();
                break;
        }
    }
    
    /**
     * Change behavior for V1 Mode (AI)
     */
    private void switchAiModeTurn(){
        int     oldPlayerTurn   = currentPlayerTurn;
        int     foeIndex        = (currentPlayerTurn+1)%2;  //Works only for 2 players
        Player  foe             = listPlayers[foeIndex];
        if(Session.getPlayer().getFleet().isFleetDestroyed()){
            Session.getSession().earMoneyFromScore(Session.getPlayer().getScore());
            notifyObservers(GameModel.GAME_OVER);
        } 
        else if(foe.getFleet().isFleetDestroyed()){
            Session.getSession().earMoneyFromScore(Session.getPlayer().getScore());
            notifyObservers(GameModel.GAME_VICTORY);
        }
        else{
            this.counterTurn++;
            this.currentPlayerTurn  = foeIndex;
            this.notifyObservers(GameModel.SWITCH_BEHAVIORS);
            //AI player shoot on session player
            if(foe instanceof PlayerAI){
                DebugTrack.showExecMsg("AI Turn");
                this.counterTurn++;
                this.currentPlayerTurn  = oldPlayerTurn;
                ((PlayerAI)foe).processAiShoot(Session.getPlayer().getFleet().getTabBoxMap());
            }
        }
        this.breakV1.stop(); //Stop break timer
    }
    
    /**
     * Change behavior for V2 Mode (2 Players with same computer)
     */
    public void switchV2ModeTurn(){
        int     foeIndex        = (currentPlayerTurn+1)%2;  //Works only for 2 players
        Player  foe             = listPlayers[foeIndex];
        if(listPlayers[currentPlayerTurn].getFleet().isFleetDestroyed()){
            Session.getSession().earMoneyFromScore(Session.getPlayer().getScore());
            notifyObservers(GameModel.GAME_OVER);
        } 
        else if(foe.getFleet().isFleetDestroyed()){
            Session.getSession().earMoneyFromScore(Session.getPlayer().getScore());
            notifyObservers(GameModel.GAME_VICTORY);
        } 
        else {
            counterTurn++;
            currentPlayerTurn  = foeIndex;
            notifyObservers(GameModel.SWITCH_PAGE);
        }
        this.breakV2.stop();
    }
    
    /**
     * Change behavior for LAN Mode (Network)
     */
    public void switchLanModeTurn(){
        int     oldPlayerTurn   = currentPlayerTurn;
        int     foeIndex        = (currentPlayerTurn+1)%2;  //Works only for 2 players
        Player  foe             = listPlayers[foeIndex];
        if(Session.getPlayer().getFleet().isFleetDestroyed()){
            Session.getSession().earMoneyFromScore(Session.getPlayer().getScore());
            notifyObservers(GameModel.GAME_OVER);
        } 
        else if(foe.getFleet().isFleetDestroyed()){
            Session.getSession().earMoneyFromScore(Session.getPlayer().getScore());
            notifyObservers(GameModel.GAME_VICTORY);
        }
        else{
            this.counterTurn++;
            this.currentPlayerTurn  = foeIndex;
            this.notifyObservers(GameModel.SWITCH_BEHAVIORS);
            //AI player shoot on session player
            if(foe instanceof PlayerLan){
                DebugTrack.showExecMsg("AI Turn");
                this.counterTurn++;
                this.currentPlayerTurn  = oldPlayerTurn;
                ((PlayerAI)foe).processAiShoot(Session.getPlayer().getFleet().getTabBoxMap());
            }
        }
        this.breakLan.stop();
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
    
    /**
     * Return current turn. On turn is done all player have played
     * @return current turn position 
     */
    public int getNbTurn(){
        return this.counterTurn/2;
    }
    
    /**
     * Return id of current player turn
     * @return int id current player turn
     */
    public int getIdPlayerTurn(){
        return this.currentPlayerTurn;
    }
    
    /**
     * Return the current player turn
     * @return Player current player
     */
    public Player getPlayerTurn(){
        return this.listPlayers[this.currentPlayerTurn];
    }
}
