/* 
 * Creation : 17 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.main;

import com.battleship.models.game.Player;
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
    /**
     * Account name, cannot be changed later
     */
    private     String              name;
    
    /**
     * Current account connection status : logged or not
     */
    private     boolean             isConnected;
    
    /**
     * List a weapon. User can buy more weapon. A player got all weapons the account 
     * bounded with has got. (All account has got the default weapon)
     */
    private     ArrayList<Weapon>   listWeapon;
    
    /**
     * Player account is playing with. Note it is used only during a game, otherwise, 
     * this class is null
     * @var Player
     */
    private     Player              player;
    
    
    
    
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Start the session
     * user is not logged yet
     */
    public Session(){
        this.isConnected = false;
    }
    
    public void initAccount(){
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
     * Return player used by this account
     * @return Player
     */
    public Player getPlayer(){
        return this.player;
    }
    
    /**
     * Set the player of this account
     * @param pPlayer 
     */
    public void setPlayer(Player pPlayer){
        this.player = pPlayer;
    }
}
