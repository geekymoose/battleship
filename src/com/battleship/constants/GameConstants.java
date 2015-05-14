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
 * @since   Feb 9, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public interface GameConstants {
    //**************************************************************************
    // Grid constants
    //**************************************************************************
    //Grid type
    public static final int     GRID_TYPE_SQUARE            = 1;
    public static int           GRID_TYPE_HEXAGON           = 2;
    
    
    
    
    //**************************************************************************
    // Game constants
    //**************************************************************************
    public static final int     WAS_HIT                     = 1;
    public static final int     NOT_HIT                     = 2;
    
    public static final int     NO_VALUE                    = -1;
    
    //Position constants
    public static final int     VERTICAL                    = 1;
    public static final int     HORIZONTAL                  = 2;
    public static final int     STANDING_UP                 = 3;
    public static final int     STANDING_DOWN               = 4;
    
    
    
    //**************************************************************************
    // Game constants
    //**************************************************************************
    public static final int     MODE_AI                     = 1;
    public static final int     MODE_V2                     = 2;
    public static final int     MODE_LAN                    = 3;
    public static final int     MODE_INTERNET               = 4;
    
    public static final int     CREATE_GAME                 = 1;
    public static final int     JOIN_GAME                   = 2;
    
    
    
    
    
    //**************************************************************************
    // Id Boats constants
    //**************************************************************************
    public static final int     NO_BOAT_SELECTED        = -1;
    public static final int     AIRCRAFT_CARRIER        = 0;
    public static final int     BATTLESHIP              = 1;
    public static final int     CRUISER                 = 2;
    public static final int     DESTROYER               = 3;
    public static final int     SUBMARINE               = 4;
    public static final int     WATER                   = 42;
    
    
    
    //**************************************************************************
    // Delay constants
    //**************************************************************************
    public static final int     DELAY_GAME              = 10;
    public static final int     DELAY_WATER             = 30;
    public static final int     DELAY_BREAK_V1          = 600;
    public static final int     DELAY_BREAK_V2          = 1000;
    public static final int     DELAY_BREAK_LAN         = 600;
}
