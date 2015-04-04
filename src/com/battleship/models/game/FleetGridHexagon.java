/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.models.game;

import com.battleship.constants.GameConstants;
import com.battleship.models.sprites.Water;




/**
 * <h1>FleetGridHexagon</h1>
 * <p>
 * public class FleetGridHexagon<br/>
 * extends FleetGridModel
 * </p>
 * 
 *
 * @date    Feb 9, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public class FleetGridHexagon extends FleetGridModel{
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new FleetGrid with hexagon coordinates
     * @param pWidth    Grid width
     * @param pHeight   Grid Height
     * @param pOwner    FleetGrid owner
     */
    public FleetGridHexagon(int pWidth, int pHeight, Player pOwner){
        super(pWidth, pHeight, pOwner);
        this.tabBoxMap      = new BoxMapHexagon[pHeight][pWidth];
        
        //Creata all boxMap
        for(int y=0; y<this.gridHeight; y++){
            for(int x=0; x<this.gridWidth; x++){
                this.tabBoxMap[y][x] = new BoxMapHexagon(x, y, new Water(), this);
            }
        }
        
        //Set available orientations
        this.listOrientations.add(GameConstants.VERTICAL);
        this.listOrientations.add(GameConstants.STANDING_UP);
        this.listOrientations.add(GameConstants.STANDING_DOWN);
        
    }
}