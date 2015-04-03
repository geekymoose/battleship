/* 
 * Creation:    Apr 1, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.gridcursor;

import com.battleship.asset.GridCalculator;
import com.battleship.controllers.GridController;
import com.battleship.main.DebugTrack;
import java.awt.Point;


/**
 * <h1>ClickSquarePlaceBoat</h1>
 * <p>
 * public class ClickSquarePlaceBoat<br/>
 * implements ClickType
 * </p>
 * 
 * @date    Apr 2, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class ClickSquarePlaceBoat implements ClickType{
    @Override
    public void mouseClicked_Left(int x, int y, GridController c){
    }
    
    @Override
    public void mousePressed_left(int x, int y, GridController c){
    }

    @Override
    public void mouseReleased_left(int x, int y, GridController c){
    }

    @Override
    public void mouseEntered(int x, int y, GridController c){
    }

    @Override
    public void mouseExited(int x, int y, GridController c){
    }

    @Override
    public void mouseDragged_left(int x, int y, GridController c){
    }

    @Override
    public void mouseMoved(int x, int y, GridController c){
        Point p = GridCalculator.pxToSquareCoor(x, y, c.getBoxDimension());
        DebugTrack.showPointData(p);
        c.targetBoxMap(p);
    }
    
    @Override
    public void wheelMovedUp(int x, int y, GridController c){
    }
    
    @Override
    public void wheelMovedDown(int x, int y, GridController c){
    }
}
