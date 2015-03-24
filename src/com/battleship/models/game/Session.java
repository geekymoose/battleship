/* 
 * Creation : 17 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.models.game;

import com.battleship.models.weapons.Weapon;
import java.util.ArrayList;





/**
 * <h1>Session</h1>
 * <p>public class Session</p>
 * 
 * <p>
 * This class is used for account. It represent the current logged session. <br/>
 * When user launch the game program, Session is created. According to 
 * game mode, the Session need a web login or local login. (Or no 
 * login at all). 
 * <br/>
 * Save user data. When user start the game, a empty session is created (No data) 
 * Depending of the game mode (AI / 2v2 / LAN / Internet), the session is 
 * set with data (For example, it save current game type and recover 
 * weapons user have got)
 * <br/>
 * Pattern Singleton is used for Session
 * </p>
 *
 * 
 * @date    Feb 25, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class Session {
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private     static Session      singleton = null;
    private     String              name;
    private     int                 gameMode;
    private     boolean             isConnected;
    private     ArrayList<Weapon>   listWeapon;
    private     Player              player; //used only during a game
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Start the session
     * user is not logged yet
     */
    private Session(){
        this.isConnected = false;
    }
    
    /**
     * Create a new Session
     */
    public static void createSession(){
        Session.singleton = new Session();
        Session.singleton.initAccount();
    }
    
    /**
     * Initialize session status
     */
    private void initAccount(){
        this.name           = "Unknown";
        this.isConnected    = false;
        this.player         = new Player();
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    
    
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return current game mode used by this session
     * @return int game mode
     */
    public static int getGameMode(){
        return Session.singleton.gameMode;
    }
    
    /**
     * Return player used by this account
     * @return Player
     */
    public static Player getPlayer(){
        return Session.singleton.player;
    }
    
    /**
     * Set a new game mode for this Session
     * @param pValue 
     */
    public static void setGameMode(int pValue){
        Session.singleton.gameMode = pValue;
    }
    
    /**
     * Set the player of this account
     * @param pPlayer 
     */
    public static void setPlayer(Player pPlayer){
        Session.singleton.player = pPlayer;
    }
}
