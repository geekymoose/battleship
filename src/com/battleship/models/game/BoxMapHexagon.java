/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.models.game;

import com.battleship.behaviors.Sprite;
import java.util.ArrayList;





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
        int x = this.coordinate.x;
        int y = this.coordinate.y;
        
        switch(pDirection){
            case VERTICAL:
                y += 1;
                break;
            case STANDING_UP:
                if(x%2==0){ y--; }
                x++;
                break;
            case STANDING_DOWN:
                if(x%2!=0){ y++; }
                x++;
                break;
        }
        return this.grid.getBoxMapAt(x, y);
    }

    @Override
    public ArrayList<BoxMap> getNeighbor(){
        int x = this.coordinate.x;
        int y = this.coordinate.y;
        
        ArrayList<BoxMap> l = new ArrayList();
        BoxMap b;
        
        if(x%2==0){
            if((b = this.grid.getBoxMapAt(x, y))        != null){ l.add(b); }
            if((b = this.grid.getBoxMapAt(x, y-1))      != null){ l.add(b); }
            if((b = this.grid.getBoxMapAt(x, y+1))      != null){ l.add(b); }
            if((b = this.grid.getBoxMapAt(x-1, y-1))    != null){ l.add(b); }
            if((b = this.grid.getBoxMapAt(x-1, y))      != null){ l.add(b); }
            if((b = this.grid.getBoxMapAt(x+1, y-1))    != null){ l.add(b); }
            if((b = this.grid.getBoxMapAt(x+1, y))      != null){ l.add(b); }
        }else{
            if((b = this.grid.getBoxMapAt(x, y-1))      != null){ l.add(b); }
            if((b = this.grid.getBoxMapAt(x-1, y))      != null){ l.add(b); }
            if((b = this.grid.getBoxMapAt(x, y))        != null){ l.add(b); }
            if((b = this.grid.getBoxMapAt(x+1, y))      != null){ l.add(b); }
            if((b = this.grid.getBoxMapAt(x-1, y+1))    != null){ l.add(b); }
            if((b = this.grid.getBoxMapAt(x, y+1))      != null){ l.add(b); }
            if((b = this.grid.getBoxMapAt(x+1, y+1))    != null){ l.add(b); }
        }
        return l;
    }
}
