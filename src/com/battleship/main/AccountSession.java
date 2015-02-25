/* 
 * Creation : 17 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.main;

import com.battleship.models.game.Player;
import com.battleship.models.weapons.Weapon;
import java.util.ArrayList;





/**
 * <h1>AccountSession</h1>
 * <p>public class AccountSession</p>
 * <p>
 * This class is used for account. It represent the current logged session. <br/>
 * When user launch the game program, AccountSession is created. According to 
 * game mode, the AccountSession need a internet loggin or local loggin. (Or no 
 * login at all). 
 * </p>
 *
 * @date    Feb 25, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class AccountSession {
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
    public AccountSession(){
        
    }
    
    public void initAccount(){
        
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
