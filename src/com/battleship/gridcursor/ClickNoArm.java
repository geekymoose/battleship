/*
 * Class :      ClickNoArm
 * Creation:    Apr 4, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.gridcursor;

import com.battleship.controllers.GridController;
import java.awt.Point;





/**
 * <h1>ClickNoArm</h1>
 * <p>
 * public class ClickNoArm<br/>
 * implements ClickType
 * </p>
 *
 * @since   Apr 4, 2015
 * @author  Constantin MASSON
 */
public class ClickNoArm implements ClickType{
    @Override
    public void mouseClicked_Left(Point p, GridController c){
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
        c.resetHoverAndAim();
    }

    @Override
    public void mouseDragged_left(Point p, GridController c){
    }

    @Override
    public void mouseMoved(Point p, GridController c){
        c.hoverBoxMap(p);
    }

    @Override
    public void wheelMovedUp(Point p, GridController c){
    }

    @Override
    public void wheelMovedDown(Point p, GridController c){
    }
}
