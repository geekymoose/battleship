/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.behaviors;





/**
 * <h1>Sprite</h1>
 * <p>public interface Sprite</p>
 * 
 * <p>
 * A Sprite is a item which is placed on a BoxMap. It could be hit by a shot.
 * </p>
 *
 * @since   Feb 9, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 * 
 * @see BoxMap
 */
public interface Sprite {
    //**************************************************************************
    // Constants
    //**************************************************************************
    public static final int     AIRCRAFT_CARRIER    = 0;
    public static final int     BATTLESHIP          = 1;
    public static final int     CRUISER             = 2;
    public static final int     DESTROYER           = 3;
    public static final int     SUBMARINE           = 4;
    public static final int     WATER               = 42;
    
    public final int            ALIVE_BOAT          = 1;
    public final int            ALIVE_WATER         = 2;
    public final int            DEAD_BOAT           = 3;
    public final int            DEAD_WATER          = 4;
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Hit sprite by a shot.
     * @return true if sprite hit successfully, otherwise, return false
     */
    public boolean hit();
    
    /**
     * Check if this sprite could be hit (For example, this sprite could have
     * already been hit.
     * @return true if can be hit, otherwise, return false
     */
    public boolean canBeHit();
    
    /**
     * Return if sprite
     * @return int
     */
    public int getId();
    
    /**
     * Return current sprite state. For example, sprite can be a boat, water, hit
     * @return int state of the sprite
     */
    public int getState();
    
    /**
     * Return the value of this sprite
     * @return int value
     */
    public int getValue();
    
    /**
     * Return name of the first explosion image for this sprite
     * @return int first image name (Is a number)
     */
    public int getExplosion();
}
