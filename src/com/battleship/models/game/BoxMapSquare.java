/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.models.game;

import com.battleship.behaviors.Sprite;
import static com.battleship.constants.GameConstants.VERTICAL;
import java.util.ArrayList;





/**
 * <h1>BoxMapSquare</h1>
 * <p>
 * public class BoxMapSquare<br/>
 * extends BoxMap
 * </p>
 *
 * @since   Feb 15, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 * 
 * @see     GridFleetModel
 * @see     BoxMap
 */
public class BoxMapSquare extends BoxMap{
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
    public BoxMapSquare(int pX, int pY, Sprite pSprite, FleetGridModel pGrid){
        super(pX, pY, pSprite, pGrid);
    }
    
    
    
    
    

    //**************************************************************************
    // Functions
    //**************************************************************************
    @Override
    public BoxMap getNextBoxMap(int pDirection){
        //coordinate for the next BoxMap
        int x = this.coordinate.x;
        int y = this.coordinate.y;
        
        switch(pDirection){
            case VERTICAL:
                y += 1;
                break;
            case HORIZONTAL:
                x += 1;
                break;
        }
        return this.grid.getBoxMapAt(x, y);
    }

    @Override
    public ArrayList<BoxMap> getNeighbor(){
        int x = this.coordinate.x;
        int y = this.coordinate.y;
        ArrayList<BoxMap> l = new ArrayList();
        for(int i=-1; i<2; i++){
            for(int j=-1; j<2; j++){
                BoxMap b = this.grid.getBoxMapAt(x+i, y+j);
                if(b!=null){
                    l.add(b);
                }
            }
        }
        return l;
    }
}
