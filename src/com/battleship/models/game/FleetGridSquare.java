/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.models.game;

import com.battleship.models.sprites.Water;





/**
 * <h1>FleetGridSquare</h1>
 * <p>
 * public class FleetGridSquare<br/>
 * extends FleetGridModel
 * </p>
 * 
 *
 * @date    Feb 9, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public class FleetGridSquare extends FleetGridModel{
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new FleetGrid with square coordinates
     * @param pWidth    Grid width
     * @param pHeight   Grid Height
     * @param pOwner    FleetGrid owner
     */
    public FleetGridSquare(int pWidth, int pHeight, Player pOwner){
        super(pWidth, pHeight, pOwner);
        this.tabBoxMap      = new BoxMapSquare[pHeight][pWidth];
        //Create all box map
        for(int y=0; y<this.gridHeight; y++){
            for(int x=0; x<this.gridWidth; x++){
                this.tabBoxMap[y][x] = new BoxMapSquare(x, y, new Water(), this);
            }
        }
    }
}
