/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.models.game;

import com.battleship.asset.RandomManager;
import com.battleship.behaviors.Target;





/**
 * <h1>PlayerAI</h1>
 * <p>
 * public class PlayerAI<br/>
 * extends Player
 * </p>
 * <p>Player is controlled by artificial intelligence.</p>
 *
 * @since   Mar 24, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public class PlayerAI extends Player{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    
    
    
    
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new AI player
     */
    public PlayerAI(){
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
        int box = ((BoxMap)pWhere[posY][posX]).content.getId();
    }
}
