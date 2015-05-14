/* 
 * Creation : Feb 11, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.models.sprites;

import com.battleship.asset.Config;
import com.battleship.behaviors.Sprite;





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
 * @since   Feb 11. 2015
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * @author  Contsantin MASSON
 */
public class AircraftCarrier extends Boat{
    
    /**
     * Create a new AircraftCarrier
     */
    public AircraftCarrier(){
        super(Sprite.AIRCRAFT_CARRIER, 
              "AircraftCarrier", 5, 5, 
              Config.getGameValues_int("value-aircraft-carrier"));
    }
}
