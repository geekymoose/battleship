/*
 * Class :      ShotType
 * Creation:    Feb 15, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.models.weapons;

import com.battleship.behaviors.Target;





/**
 * <h1>ShotType</h1>
 * <p>public interface ShotType</p>
 *
 *
 * 
 * @date    Feb 15, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public interface ShotType {
    /**
     * Process a fire on a target in Square grid
     * @param pX abscissa of the compartment shot in the grid pTarget
     * @param pY ordinate of the compartment shot in the grid pTarget
     * @param pTarget grid of Target
     * @return true if target hit, otherwise, return false
     */
    public boolean fireSquareGrid(int pX, int pY, Target[][] pTarget);
    
    /**
     * Process a fire on a target in hexagon grid
     * @param pX abscissa of the compartment shot in the grid pTarget
     * @param pY ordinate of the compartment shot in the grid pTarget
     * @param pTarget grid of Target
     * @return true if target hit, otherwise, return false
     */
    public boolean fireHexagonGrid(int pX, int pY, Target[][] pTarget);
}
