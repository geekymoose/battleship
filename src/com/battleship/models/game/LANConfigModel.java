/*
 * Creation :   24 mars 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.models.game;

import com.battleship.main.DebugTrack;





/**
 * <h1>LANConfigModel</h1>
 * <p>
 * public class LANConfigModel<br/>
 * </p>
 * 
 * <p>Description</p>
 * 
 * @date    24 mars 2015
 * @author  Contsantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class LANConfigModel extends Model {
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new empty LANConfig. All data are loaded with default values.
     */
    public LANConfigModel(){
        DebugTrack.showInitMsg("Create LANConfigModel Model");
        this.defaultConfig();
    }
    
    /**
     * Set configuration to default values
     */
    public void defaultConfig(){
        //DEFAULT STUFF
        this.notifyObservers(null);
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Reset the current configuration. Every value will be set toward default 
     * value.
     */
    public void resetConfig(){
        this.defaultConfig();
    }
    
    /**
     * Check if config is valid
     * @return true if valid, otherwise, return false
     */
    public boolean isValid(){
        //It should be always true atm, bu could be false with name player etc
        return true;
    }
    
    
    
    
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    
}
