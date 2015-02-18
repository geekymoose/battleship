/*
 * Class :      ShotType
 * Creation:    Feb 15, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.models.weapons;

import com.battleship.behaviors.Target;
import com.battleship.models.game.FleetGridModel;





/**
 * <h1>ShotType</h1>
 * <p>public interface ShotType</p>
 *
 *
 * 
 * @date Feb 15, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public interface ShotType {
    /**
     * 
     * @param pX abscissa of the compartment shot in the grid pTarget
     * @param pY ordinate of the compartment shot in the grid pTarget
     * @param pTarget grid of Target (from the FleetGridModel)
     * @return true if the target was hit
     */
    public boolean fireSquareGrid(int pX, int pY, Target[][] pTarget);
    /**
     * 
     * @param pX abscissa of the compartment shot in the grid pTarget
     * @param pY ordinate of the compartment shot in the grid pTarget
     * @param pTarget grid of Target (from the FleetGridModel)
     * @return true if the target was hit
     */
    public boolean fireHexagonGrid(int pX, int pY, Target[][] pTarget);
    
}
