/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.behaviors;





/**
 * <h1>Target</h1>
 * <p>public interface Target</p>
 * 
 * <p>Interface that can be use to create a class which can be targeted by 
 * a weapon.</p>
 *
 * 
 * @date    Feb 15, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public interface Target{
    /**
     * Hit target
     * @return true if hit successfully, otherwise, return false
     */
    public boolean hit();
    
    
    /**
     * Check if target is valid, it means the function check is target could be 
     * hit by a shot or other harm attack
     * @return true if valid target, otherwise, return false
     */
    public boolean isValidTarget();
    
    
    /**
     * Check if Target is targeted by a weapon or something which can aim this a target
     * @return true if targeted, otherwise, return false
     */
    public boolean isTargeted();
    
    
    /**
     * Aim at the current Target (isTargeted will return true)
     * @return true if could be aim, otherwise, return false
     */
    public boolean aim();
    
    
    /**
     * Reset aim status (Not targeted anymore)
     */
    public void stopAim();
    
    /**
     * Return the value of this target
     * @return 
     */
    public int getValue();
}
