/* 
 * Creation : Feb 11, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.models.sprites;

import com.battleship.asset.Config;
import com.battleship.constants.GameConstants;





/**
 * <h1>Cruiser</h1>
 * <p>
 * public class Cruiser<br/>
 * extends Boat
 * </p>
 * 
 * <h2>Cruiser features</h2>
 *  <ul>
 *      <li>Size : 3</li>
 *      <li>Number Lives : 3</li>
 *  </ul>
 * 
 * @date    Feb 11. 2015
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * @author  Contsantin MASSON
 */
public class Cruiser extends Boat {
    
    /**
     * Create a new Cruiser
     */
    public Cruiser(){
        super(GameConstants.CRUISER, "Cruiser", 3, 3,
              Config.getGameValues_int("value-cruiser"));
    }
}
