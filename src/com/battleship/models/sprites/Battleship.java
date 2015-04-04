/* 
 * Creation : Feb 11, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.models.sprites;





/**
 * <h1>Battleship</h1>
 * <p>
 * public class Battleship<br/>
 * extends Boat
 * </p>
 * 
 * <h2>Battleship features</h2>
 *  <ul>
 *      <li>Size : 4</li>
 *      <li>Number Lives : 4</li>
 *  </ul>
 * 
 * @date    Feb 11. 2015
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * @author  Contsantin MASSON
 */
public class Battleship extends Boat{
    
    
    /**
     * Create a new Battleship
     */
    public Battleship(){
        super("Battleship", 4, 4);
    }
}
