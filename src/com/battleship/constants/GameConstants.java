/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.constants;





/**
 * <h1>GameConstants</h1>
 * <p>public interface GameConstants</p>
 * <p>All constants used for the game</p>
 *
 * @date    Feb 9, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public interface GameConstants {
    //**************************************************************************
    // Grid constants
    //**************************************************************************
    //Grid type
    public static int       GRID_TYPE_SQUARE            = 1;
    public static int       GRID_TYPE_HEXAGON           = 2;
    
    
    
    
    //**************************************************************************
    // Game constants
    //**************************************************************************
    public static int       WAS_HIT                     = 1;
    public static int       NOT_HIT                     = 2;
    
    //Position constants
    public static int       VERTICAL                    = 1;
    public static int       HORIZONTAL                  = 2;
    public static int       STANDING_UP                 = 3;
    public static int       STANDING_DOWN               = 4;
    
    
    
    //**************************************************************************
    // Game constants
    //**************************************************************************
    public static int       MODE_AI                     = 1;
    public static int       MODE_V2                     = 2;
    public static int       MODE_LAN                    = 3;
    public static int       MODE_INTERNET               = 4;
    
    public static int       CREATE_GAME                 = 1;
    public static int       JOIN_GAME                   = 2;
    
    
    
    
    //**************************************************************************
    // Weapon constants
    //**************************************************************************
    public static int       INFINITE_AMO                = -42;
    
    
    
    
    
    //**************************************************************************
    // Boats Selection constants
    //**************************************************************************
    public static int       NO_BOAT_SELECTED    = -1;
    public static int       AIRCRAFT_CARRIER    = 0;
    public static int       BATTLESHIP          = 1;
    public static int       CRUISER             = 2;
    public static int       DESTROYER           = 3;
    public static int       SUBMARINE           = 4;
    public static int       WATER               = 42;
}
