/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.models.game;

import com.battleship.asset.RandomManager;
import com.battleship.behaviors.Target;





/**
 * <h1>PlayerLan</h1>
 * <p>
 * public class PlayerLan<br/>
 * extends Player
 * </p>
 * <p>Player is controlled player in remote computer</p>
 *
 * @since   Mar 24, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public class PlayerLan extends Player{
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new AI player
     */
    public PlayerLan(){
        super();
    }
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Process a random shoot on enemy grid
     * @param pWhere where to shoot
     */
    public void processAiShoot(Target[][] pWhere){
        //Note this implementation is naive
        int posX, posY;
        do{
            posX = RandomManager.getRandomBetween(0, (pWhere[0].length-1));
            posY = RandomManager.getRandomBetween(0, (pWhere.length-1));
        } while(pWhere[posY][posX].isValidTarget() == false);
        this.shootAt(posX, posY, pWhere);
    }
}
