/* 
 * Creation : 17 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.asset;

import com.battleship.exceptions.ForbiddenAction;
import com.battleship.exceptions.LanError;
import com.battleship.models.game.Player;
import com.battleship.models.weapons.Missile;
import com.battleship.models.weapons.Weapon;
import com.battleship.network.Capsule;
import com.battleship.network.Network;
import com.battleship.network.Request;
import java.io.IOException;
import java.util.ArrayList;





/**
 * <h1>Session</h1>
 * <p>public class Session</p>
 * 
 * <p>
 * This class is used for account. It represent the current logged session. </p><p>
 * When user launch the game program, Session is created. According to 
 * game mode, the Session need a web login or local login. (Or no 
 * login at all). 
 * </p><p>
 * Save user data. When user start the game, a empty session is created (No data) 
 * Depending of the game mode (AI / 2v2 / LAN / Internet), the session is 
 * set with data (For example, it save current game type and recover 
 * weapons user have got)
 * </p><p>
 * Pattern Singleton is used for Session
 * </p>
 *
 * 
 * @since   Feb 25, 2015
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
    private     ArrayList<Weapon>   listWeapon;
    private     Player              player; //used only during a game
    private     int                 money;
    private     Network             network;
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Start the session
     * user is not logged yet
     */
    private Session(){
        this.initAccount();
        this.network = null;
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
        this.name           = "Session";
        this.money          = 0;
        this.listWeapon     = new ArrayList();
        this.player         = null;
        this.listWeapon     .add(new Missile(this.player, Weapon.INFINITE_AMO));
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
                    return;
                }
            }
            throw new ForbiddenAction("You must buy a "+pWeapon.getName()+" before !");
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
            this.money -= pWeapon.getPriceWeapon();
            this.addWeapon(pWeapon);
        }
    }
    
    /**
     * Try to buy a ammo for weapon. 
     * @param pWeapon   weapon to buy
     * @param pAmmo     among of ammo to buy
     * @throws ForbiddenAction throw if unable to buy
     */
    public void buyAmmoWeapon(Weapon pWeapon, int pAmmo) throws ForbiddenAction{
        if(pWeapon.getPriceAmmo() == Weapon.INFINITE_AMO){
            throw new ForbiddenAction("Ammo is infinit, you don't have to buy it");
        }
        if((pWeapon.getPriceAmmo()*pAmmo) > this.money){
            throw new ForbiddenAction("You don't have enough money!!!");
        }else{
            this.money -= pWeapon.getPriceAmmo();
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
    // Network functions
    //**************************************************************************
    /**
     * Check if session is connected to network 
     * @return true if connected, otherwise, return false
     */
    public static boolean isConnected(){
        return Session.singleton.network != null;
    }
    
    /**
     * Try to disconnect current server
     * @throws LanError thrown if unable to disconnect
     */
    public static void disconnect() throws LanError{
        if(Session.isConnected()){
            try{
                Session.singleton.network.sendCapsule(new Capsule(Request.DISCONNECT, null));
                Session.singleton.network.getInput().close();
                Session.singleton.network.getOutput().close();
                Session.singleton.network = null;
            } catch(IOException ex) {
                throw new LanError("Unable to disconnect");
            }
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
    
    /**
     * Return a specific weapon owned by player using weapon id. If player 
     * doesn't own this weapon , throws error
     * @param pWeaponId id of the weapon to get
     * @return weapon owned by session
     * @throws ForbiddenAction thrown if session doesn't have this weapon
     */
    public static Weapon getWeapon(int pWeaponId) throws ForbiddenAction{
        for(Weapon w : Session.singleton.listWeapon){
            if(w.getWeaponId() == pWeaponId){
                return w;
            }
        }
        throw new ForbiddenAction("You don't have this weapon");
    }
    
    /**
     * Return current network manager. Null if not connected
     * @return Network 
     */
    public static Network getNetwork(){
        return Session.singleton.network;
    }
    
    /**
     * Return current session money
     * @return int money
     */
    public static int getMoney(){
        return Session.singleton.money;
    }
    
    /**
     * Return session name
     * @return String session name
     */
    public static String getName(){
        return Session.singleton.name;
    }
    
    
    //**************************************************************************
    /**
     * Set a new game mode for this Session
     * @param pValue  game mode
     */
    public static void setGameMode(int pValue){
        Session.singleton.gameMode = pValue;
    }
    
    /**
     * Set the player of this account
     * @param pPlayer Session Player
     */
    public static void setPlayer(Player pPlayer){
        Session.singleton.player = pPlayer;
    }
    
    /**
     * Set network for this session
     * @param pNetwork new network, null if no more network 
     */
    public static void setNetwork(Network pNetwork){
        Session.singleton.network = pNetwork;
    }
}
