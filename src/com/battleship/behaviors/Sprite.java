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
 * @date    Feb 9, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 * @see BoxMap
 */
public interface Sprite {
    
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
}
