/* 
 * Creation : Apr 4, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.asset;

import com.battleship.models.game.GameModel;
import com.battleship.models.game.Player;
import com.battleship.models.weapons.*;
import com.battleship.views.app.RadarPanel;



/**
 * <h1>CheatCode</h1>
 * <p>public abstract class CheatCode</p>
 * <p>Cheat code</p>
 *
 * @since   Apr 4, 2015
 * @author  Constantin MASSON
 */
public abstract class CheatCode{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private     static boolean         cheatAuthorized     = true;
    private     static GameModel       game;
    private     static RadarPanel      radar;
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    public static void processStrCode(String str){
        if(!cheatAuthorized){
            return;
        }
        int     idPlayer    = CheatCode.game.getIdPlayerTurn();
        Player  player      = CheatCode.game.getConfig().getPlayers()[idPlayer];
        
        switch(str){
            case "whoisthebest":
                CheatCode.radar.displayCurrentGrid();
                break;
            case "givemeanuke":
                player.addWeapon(new NuclearBomb(player, 1));
                player.switchWeaponNext();
                break;
            case "givemeatorpedo":
                player.addWeapon(new Torpedo(player, 1));
                player.switchWeaponNext();
                break;
            case "givemeabomb":
                player.addWeapon(new Bomb(player, 1));
                player.switchWeaponNext();
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
