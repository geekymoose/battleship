/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.models.game;

import com.battleship.models.weapons.Weapon;
import java.util.ArrayList;





/**
 * <h1>PlayerHuman</h1>
 * <p>
 * public class PlayerHuman<br/>
 * extends Player
 * </p>
 *
 * @date    Mar 24, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public class PlayerHuman extends Player{
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a human with a existing list a weapon (Could be the Session Player) 
     * for example
     * @param pList 
     */
    public PlayerHuman(ArrayList<Weapon> pList){
        super(pList);
    }
    /**
     * Create a new Human player
     */
    public PlayerHuman(){
        super();
    }
}
