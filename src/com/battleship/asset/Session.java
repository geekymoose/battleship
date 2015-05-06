/* 
 * Creation : 17 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.asset;

import com.battleship.constants.GameConstants;
import com.battleship.exceptions.ForbiddenAction;
import com.battleship.models.game.Player;
import com.battleship.models.weapons.Missile;
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
    private     int                 money;
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Start the session
     * user is not logged yet
     */
    private Session(){
        this.initAccount();
    }
    
    /**
     * Create a new Session
     */
    public static void createSession(){
        Session.singleton = new Session();
    }
    
    /**
     * Initialize session status with default values
     */
    private void initAccount(){
        this.name           = "Unknown";
        this.isConnected    = false;
        this.money          = 0;
        this.listWeapon     = new ArrayList();
        this.money          = 0;
        this.name           = "Unknown";
        this.player         = null;
        this.listWeapon     .add(new Missile(this.player, GameConstants.INFINITE_AMO));
    }
    
    
    
    
    
    //**************************************************************************
    // Functions weapon sell / Buy
    //**************************************************************************
    /**
     * Add new weapon in current player weapon list. If weapon is already owned,
     * throw ForbiddenAction Exception. Do nothing if null
     * @param pWeapon weapon to add, if already exists, throw Exception
     * @throws ForbiddenAction if already owned
     */
    private void addWeapon(Weapon pWeapon) throws ForbiddenAction{
        if(pWeapon != null){
            for(Weapon w : this.listWeapon){
                if(pWeapon.getClass() == w.getClass()){
                    throw new ForbiddenAction("You already have a "+pWeapon.getName()+" !");
                }
            }
            this.listWeapon.add(pWeapon);
            pWeapon.setOwner(this.player);
        }
    }
    
    /**
     * Add ammo for a specific weapon. Session user must already have this weapon, 
     * otherwise, throw a ForbiddenAction Exception. Add this amount of ammo 
     * in current loader
     * @param pWeapon   weapon where to add ammo
     * @param pAmmo     Ammo to add in the weapon
     * @throws ForbiddenAction if already owned
     */
    private void addAmmoWeapon(Weapon pWeapon, int pAmmo) throws ForbiddenAction{
        if(pWeapon != null){
            for(Weapon w : this.listWeapon){
                if(pWeapon.getClass() == w.getClass()){
                    w.addAmmo(pAmmo);
                }
            }
            throw new ForbiddenAction("You must by a "+pWeapon.getName()+" before !");
        }
    }
    
    /**
     * Try to buy a new weapon. Add in owned weapon if can do this, otherwise, 
     * throw ForbiddenAction Exception (Not enough money, already owned etc)
     * @param pWeapon   weapon to buy
     * @throws ForbiddenAction throw if unable to buy
     */
    public void buyWeapon(Weapon pWeapon) throws ForbiddenAction{
        if(pWeapon.getPriceWeapon() > this.money){
            throw new ForbiddenAction("You don't have enough money!!!");
        }else{
            this.addWeapon(pWeapon);
        }
    }
    
    /**
     * Try to buy a ammo for weapon. 
     * @param pWeapon   weapon to buy
     * @param pAmmo     
     * @throws ForbiddenAction throw if unable to buy
     */
    public void buyAmmoWeapon(Weapon pWeapon, int pAmmo) throws ForbiddenAction{
        if((pWeapon.getPriceAmmo()*pAmmo) > this.money){
            throw new ForbiddenAction("You don't have enough money!!!");
        }else{
            this.addAmmoWeapon(pWeapon, pAmmo);
        }
    }
    
    /**
     * Earn an among of money. It will add to current money. Do nothing if 
     * value if less than 0
     * @param pValue to add
     */
    public void earnMoney(int pValue){
        if(pValue>=0){
            this.money += pValue;
        }
    }
    
    /**
     * Ear an among of money calculated from current score
     * @param pScore score
     */
    public void earMoneyFromScore(int pScore){
        if(pScore>=0){
            int value = (int)(pScore * Config.getGameValues_double("rate-score-money"));
            this.earnMoney(value);
        }
    }
    
    
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return the current session
     * @return Session
     */
    public static Session getSession(){
        return Session.singleton;
    }
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
     * Return list of weapon owned by session player
     * @return ArrayList of Weapon with weapon owned
     */
    public static ArrayList<Weapon> getListWeapons(){
        return Session.singleton.listWeapon;
    }
    
    
    //**************************************************************************
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
