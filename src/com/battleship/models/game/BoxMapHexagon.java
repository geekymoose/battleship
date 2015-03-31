/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.models.game;

import com.battleship.behaviors.Sprite;
import com.battleship.views.tools.Config;





/**
 * <h1>BoxMapHexagon</h1>
 * <p>
 * public class BoxMapHexagon<br/>
 * extends BoxMap
 * </p>
 * 
 * <p>Create a BoxMap for hexagon grid</p>
 *
 * @date    Feb 15, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 * 
 * @see     GridFleetModel
 * @see     BoxMap
 */
public class BoxMapHexagon extends BoxMap{
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new BoxMap for hexagon grid
     * @param pX        x coordinate
     * @param pY        y coordinate
     * @param pSprite   sprite to add inside this BoxMap
     * @param pGrid     grid where the BoxMap is (This grid contains the new BoxMap)
     */
    public BoxMapHexagon(int pX, int pY, Sprite pSprite, FleetGridModel pGrid){
        super(pX, pY, pSprite, pGrid);
    }
    
    
    
    
    

    //**************************************************************************
    // Functions
    //**************************************************************************
    @Override
    public BoxMap getNextBoxMap(int pDirection){
        //coordinate for the next BoxMap
        int x = this.posX;
        int y = this.posY;
        
        if(pDirection==Config.getGameValues_int("vertical-boat")){
            y += 1;
        } else if (pDirection == Config.getGameValues_int("standing-right")){
            x += 1;
        } else if (pDirection == Config.getGameValues_int("standing-left")){
            x -= 1;
        } else {
            return null;
        }
        return this.grid.getBoxMapAt(x, y);
    }
}
