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
    //Dimension
    public static int       GRID_MIN_WIDTH              = 10;
    public static int       GRID_MIN_HEIGHT             = 10;
    public static int       GRID_MAX_WIDTH              = 15;
    public static int       GRID_MAX_HEIGHT             = 15;
    public static int       GRID_DEFAULT_WIDTH          = 10;
    public static int       GRID_DEFAULT_HEIGHT         = 10;
    
    //Grid type
    public static int       GRID_TYPE_SQUARE            = 1;
    public static int       GRID_TYPE_HEXAGON           = 2;
    
    
    
    //**************************************************************************
    // Game constants
    //**************************************************************************
    public static int       NB_MIN_PLAYERS              = 1;
    public static int       NB_MAX_PLAYERS              = 2;
    
    public static int       MODE_AI                     = 1;
    public static int       MODE_V2                     = 2;
    public static int       MODE_LAN                    = 3;
    public static int       MODE_INTERNET               = 4;
    
    public static int       CREATE_GAME                 = 1;
    public static int       JOIN_GAME                   = 2;
    
    
    
    //**************************************************************************
    // Weapon constants
    //**************************************************************************
    public static int       INFINITE                    = -42;
}
