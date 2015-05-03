/* 
 * Creation : Feb 11, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.models.sprites;

import com.battleship.asset.Config;
import com.battleship.constants.GameConstants;





/**
 * <h1>Submarine</h1>
 * <p>
 * public class Submarine<br/>
 * extends Boat
 * </p>
 * 
 * <h2>Submarine features</h2>
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
public class Submarine extends Boat {
    
    /**
     * Create a new Submarine
     */
    public Submarine(){
        super(GameConstants.SUBMARINE, "Submarine", 3, 3,
              Config.getGameValues_int("value-submarine"));
    }
}
