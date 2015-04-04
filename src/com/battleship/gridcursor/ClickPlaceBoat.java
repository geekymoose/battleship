/* 
 * Creation:    Apr 1, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.gridcursor;

import com.battleship.controllers.GridController;
import java.awt.Point;


/**
 * <h1>ClickPlaceBoat</h1>
 * <p>
 public class ClickPlaceBoat<br/>
 * implements ClickType
 * </p>
 * 
 * @date    Apr 2, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class ClickPlaceBoat implements ClickType{
    @Override
    public void mouseClicked_Left(Point p, GridController c){
        c.placeBoatAt(p);
    }
    
    @Override
    public void mousePressed_left(Point p, GridController c){
    }

    @Override
    public void mouseReleased_left(Point p, GridController c){
    }

    @Override
    public void mouseEntered(Point p, GridController c){
    }

    @Override
    public void mouseExited(Point p, GridController c){
    }

    @Override
    public void mouseDragged_left(Point p, GridController c){
    }

    @Override
    public void mouseMoved(Point p, GridController c){
        c.targetBoxMap(p);
    }

    @Override
    public void wheelMovedUp(Point p, GridController c){
    }

    @Override
    public void wheelMovedDown(Point p, GridController c){
        c.switchNextOrientation();
    }
}
