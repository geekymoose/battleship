/*
 * Class :      CheatCode
 * Creation:    Apr 4, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.asset;

import com.battleship.models.game.GameModel;
import com.battleship.views.app.RadarPanel;





/**
 * <h1>CheatCode</h1>
 * <p>public class CheatCode</p>
 *
 * @author Constantin MASSON
 * @date Apr 4, 2015
 */
public abstract class CheatCode {
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private     static boolean         cheatAuthorized     = true;
    
    private     static GameModel       game;
    private     static RadarPanel      radar;
    
    
    
    
    
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    
    
    
    
    

    //**************************************************************************
    // Functions
    //**************************************************************************
    public static void processStrCode(String str){
        if(!cheatAuthorized){
            return;
        }
        switch(str){
            case "whoisthebest":
                CheatCode.radar.displayCurrentGrid();
                break;
        }
    }
    
    
    
    
    

    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    public static void setData(GameModel model){
        CheatCode.game = model;
    }
    
    public static void setData(RadarPanel model){
        CheatCode.radar = model;
    }
    
}
