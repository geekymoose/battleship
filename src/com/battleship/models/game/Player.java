/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.models.game;

import com.battleship.asset.RandomManager;
import com.battleship.asset.Session;
import com.battleship.behaviors.Target;
import com.battleship.constants.GameConstants;
import com.battleship.models.sprites.*;
import com.battleship.models.weapons.*;
import java.awt.Point;
import java.util.ArrayList;





/**
 * <h1>Player</h1>
 * <p>
 * public class Player<br/>
 * extends Model<br/>
 * implements GameConstants
 * </p>
 * 
 * <p>
 * This class represent a player during a battleship game. Data could be loaded 
 * from account or new created but player instance is only for one game. <br/>
 * There are different kind of player : AI and human player
 * </p>
 *
 * 
 * @date    Feb 9, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 * 
 * @see PlayerAI
 * @see PlayerHuman
 */
public abstract class Player extends Model implements GameConstants{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private     static int              counterPlayer = 0;
    private     int                     idPlayer;
    private     String                  name;
    private     FleetGridModel          fleetGrid;
    private     GameModel               game; //Game where player is playing
    private     ArrayList<Weapon>       listWeapons;
    private     ArrayList<Boat>         listBoatsOwned;
    private     int                     score;
    private     int                     scoreCombo;
    private     int                     currentWeaponIndex;
    private     Boat                    currentSelectedBoat; //selectedBoat
    
    
    
    
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a Player owning several weapon
     * @param pList list of weapon to add 
     */
    public Player(ArrayList<Weapon> pList){
        this();
        this.listWeapons = pList;
    }
    
    /**
     * Create a new player. Player name is unknown, score = 0 
     * List weapon is fill with the default weapon and fleetGrid is not set
     */
    public Player() {
        this.idPlayer               = Player.counterPlayer;
        Player.counterPlayer++;
        this.name                   = "MisterSwadow";
        this.score                  = 0;
        this.scoreCombo             = 1;
        this.listWeapons            = new ArrayList();
        this.fleetGrid              = null;
        this.game                   = null;
        
        //Add default weapon and set current weapon to this weapon
        this.listWeapons.add(new Missile(this, INFINITE_AMO));
        this.currentWeaponIndex     = 0;
        this.currentSelectedBoat    = null;
        this.listBoatsOwned         = new ArrayList();
        this.recoverOwnedShip();
    }
    
    /**
     * Load and add in player boats list all boat owned by this player. 
     * Player could have more than max number of boats to place in a game, in 
     * this case, player has to make a choice about which boats he want to use. 
     */
    private void recoverOwnedShip(){
        //Note : at this stage of development, all player own these boats
        this.listBoatsOwned.add(new AircraftCarrier());
        this.listBoatsOwned.add(new Battleship());
        this.listBoatsOwned.add(new Cruiser());
        this.listBoatsOwned.add(new Destroyer());
        this.listBoatsOwned.add(new Submarine());
    }
    
    
    
    
    
    //**************************************************************************
    // Game Functions
    //**************************************************************************
    /**
     * Switch player weapon. Get the next player weapon.
     */
    public void switchWeaponNext() {
        this.currentWeaponIndex++;
        if(this.currentWeaponIndex>=this.listWeapons.size()){
            this.currentWeaponIndex = 0;
        }
        this.fleetGrid.stopAiming();
        this.notifyObservers(null);
    }
    
    /**
     * Switch player weapon. Get the previous player weapon.
     */
    public void switchWeaponPrevious(){
        this.currentWeaponIndex--;
        if(this.currentWeaponIndex<0){
            this.currentWeaponIndex = (this.listWeapons.size()-1);
        }
        this.fleetGrid.stopAiming();
        this.notifyObservers(null);
    }
    
    /**
     * Switch weapon to the one at position pValue. Position start a 0, means that 
     * if player got 3 weapons, get number 2 return the last weapon. Do nothing 
     * in case of invalid value
     * @param pValue weapon index to switch with
     */
    public void switchWeaponPosition(int pValue){
        if(pValue<0 || pValue >= this.listWeapons.size()){
            return;
        }
        this.currentWeaponIndex = pValue;
        this.fleetGrid.stopAiming();
        this.notifyObservers(null);
    }
    
    /**
     * Switch weapon with the weapon given in parameter (Parameter is the 
     * id of the weapon user want. If player doesn't have this weapon, no 
     * switching is done
     * @param pWeaponId id of the weapon
     */
    public void switchWeaponWith(int pWeaponId){
        int index = 0;
        for(Weapon w : this.listWeapons){
            if(w.getWeaponId() == pWeaponId){
                this.currentWeaponIndex = index;
            }
            index++;
        }
        this.fleetGrid.stopAiming();
        this.notifyObservers(null);
    }
    
    /**
     * Add new weapon in current player weapon list. If weapon is already owned 
     * by player, just add ammo.
     * @param pWeapon weapon to add, if already exists, add ammo
     */
    public void addWeapon(Weapon pWeapon){
        if(pWeapon != null){
            for (Weapon w : this.listWeapons){
                if(pWeapon.getClass() == w.getClass()){
                    w.addAmmo(pWeapon.getAmmo());
                    return;
                }
            }
            this.listWeapons.add(pWeapon);
            pWeapon.setOwner(this);
        }
        this.notifyObservers(null);
    }
    
    /**
     * Try to launch a shot at position pX, pY on array of target given in parameter. 
     * Coordinate must be valid
     * @param pX        x target coordinate in the Target matrix
     * @param pY        y target coordinate in the Target matrix
     * @param pWhere    target matrix
     * @return true if is able to shoot, otherwise, return false
     */
    public boolean shootAt(int pX, int pY, Target[][] pWhere) {
        Target target =  pWhere[pY][pX];
        if(target.isValidTarget()){
            if (this.listWeapons.get(this.currentWeaponIndex).fireAt(pX, pY, pWhere, this.fleetGrid)==true){
                int targetValue = target.getValue();
                if(targetValue != GameConstants.NO_VALUE){
                    this.scoreCombo++;
                    this.score += (targetValue * this.scoreCombo);
                } else{
                    this.scoreCombo = 1;
                }
                return true;
            }
        }
        return false;
    }
    
    /**
     * Aim a target. Coordinate must be valid
     * @param pX        x target coordinate in the Target matrix
     * @param pY        y target coordinate in the Target matrix
     * @param pWhere    target matrix
     * @return true if can be aimed by player, otherwise, return false
     */
    public boolean aimAt(int pX, int pY, Target[][] pWhere){
        this.listWeapons.get(this.currentWeaponIndex).aimAt(pX, pY, pWhere, fleetGrid);
        return true;
    }
    
    
    
    
    
    //**************************************************************************
    // Boats - Fleet Functions
    //**************************************************************************
    /**
     * Select a boat . If no boat at id value in player boat, select is null 
     * (Means no boat selected)
     * @param pBoatSelectedId id of boat to select
     */
    public void selectBoat(int pBoatSelectedId){
        for(Boat b : this.listBoatsOwned){
            if(b.getBoatId() == pBoatSelectedId){
                this.currentSelectedBoat = b;
                return;
            }
        }
        this.currentSelectedBoat = null; //If no boat at pBoatSelectedId 
    }
    
    /**
     * Try to place the current selected boat at a specific position. 
     * return true if placed, otherwise, return false
     * @param p box map coordinate where to place
     * @param pOrientation boat orientation
     * @return true if placed, otherwise, return false
     */
    public boolean placeBoatAt(Point p, int pOrientation){
        BoxMap box = this.fleetGrid.getBoxMapAt(p.x, p.y);
        if(this.currentSelectedBoat != null && box != null){
            return this.currentSelectedBoat.placeAt(box, pOrientation);
        }
        return false;
    }
    
    /**
     * Place all boat in random position on the fleet grid
     */
    public void placeAllRandomBoat(){
        for(Boat b : this.listBoatsOwned){
            boolean placed = this.placeRandomBoat(b);
            if(placed == false){
                this.placeAllRandomBoat();
                return;
            }
        }
    }
    
    /**
     * Place one boat in random position on the player fleet grid
     * @param pBoat boat to place
     */
    private boolean placeRandomBoat(Boat pBoat){
        //Set a random orientation
        Integer o       = this.getFleet().getRandomOrientation();
        BoxMap  box;
        Point   p       = new Point();
        int     count   = 0;
        do{
            count++;
            p.x         = RandomManager.getRandomBetween(0, this.fleetGrid.gridWidth-1);
            p.y         = RandomManager.getRandomBetween(0, this.fleetGrid.gridHeight-1);
            box         = this.fleetGrid.getBoxMapAt(p.x, p.y);
        } while(pBoat.isValidPosition(box, o) == false && count<300);
        if(count>=300){
            return false;
        }
        pBoat.placeAt(box, o);
        return true;
    }
    
    
    
    
    

    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return current player's name
     * @return name of this player
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Return current score
     * @return int score
     */
    public int getScore(){
        return this.score;
    }
    
    /**
     * Return player fleet grid
     * @return 
     */
    public FleetGridModel getFleet(){
        return this.fleetGrid;
    }
    
    /**
     * Return current selected boat
     * @return Boat
     */
    public Boat getSelectedBoat(){
        return this.currentSelectedBoat;
    }
    
    /**
     * Return current selected weapon
     * @return Weapon selected
     */
    public Weapon getCurrentWeapon(){
        return this.listWeapons.get(this.currentWeaponIndex);
    }
    
    /**
     * Return player id
     * @return 
     */
    private int getIdPlayer(){
        return this.idPlayer;
    }
    
    //**************************************************************************
    /**
     * Set player name
     * @param pValue new player name, if is not a String or empty, throw exception
     */
    public void setName(String pValue) {
        /*
         * ATTENTION
         * To finish!!
         * Add exception and check if valid name (Not empty, valid lenght...)
         */
        this.name = pValue;
    }
    
    /**
     * Set player fleet grid (If already have one, the old will be lost). 
     * If null given, old grid is deleted and replaced by null
     * @param pGrid 
     */
    public void setFleetGrid(FleetGridModel pGrid){
        this.fleetGrid = pGrid;
        if(this.fleetGrid != null){
            this.fleetGrid.setOwner(this); //Change owner
        }
    }
    
    /**
     * Set the GameModel de player
     * @param pGame 
     */
    public void setGameModel(GameModel pGame){
        this.game = pGame;
    }
}
