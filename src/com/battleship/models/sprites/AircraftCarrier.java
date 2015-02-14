/* 
 * Creation : Feb 11, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.models.sprites;

import com.battleship.models.game.FleetGridModel;





/**
 * <h1>AircraftCarrier</h1>
 * <p>
 * public class AircraftCarrier<br/>
 * extends Boat
 * </p>
 * 
 * <h2>AircraftCarrier features</h2>
 *  <ul>
 *      <li>Size : 5</li>
 *      <li>Number Lives : 5</li>
 *  </ul>
 * 
 * @date    Feb 11. 2015
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * @author  Contsantin MASSON
 */
public class AircraftCarrier extends Boat{
    
    /**
     * Create a new AircraftCarrier
     * @param pOrientation
     * @param pGrid 
     */
    public AircraftCarrier(int pOrientation, FleetGridModel pGrid){
        super("AircraftCarrier", 5, 5, pOrientation, pGrid);
    }
}
