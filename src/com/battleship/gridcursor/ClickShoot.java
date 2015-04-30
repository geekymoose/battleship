/*
 * Class :      ClickShoot
 * Creation:    Apr 4, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.gridcursor;

import com.battleship.controllers.GridController;
import com.battleship.models.game.Player;
import java.awt.Point;





/**
 * <h1>ClickShoot</h1>
 * <p>
 * public class ClickShoot<br/>
 * implements ClickType
 * </p>
 *
 * @author Constantin MASSON
 * @date Apr 4, 2015
 */
public class ClickShoot implements ClickType{
    private final Player owner;
    
    public ClickShoot(Player pOwner){
        this.owner = pOwner;
    }
    
    
    @Override
    public void mouseClicked_Left(Point p, GridController c){
    }
    
    @Override
    public void mousePressed_left(Point p, GridController c){
    }

    @Override
    public void mouseReleased_left(Point p, GridController c){
        c.shootBoxMap(p, owner);
    }

    @Override
    public void mouseEntered(Point p, GridController c){
    }

    @Override
    public void mouseExited(Point p, GridController c){
        c.resetAim();
    }

    @Override
    public void mouseDragged_left(Point p, GridController c){
    }

    @Override
    public void mouseMoved(Point p, GridController c){
        c.aimBoxMap(p, owner);
    }

    @Override
    public void wheelMovedUp(Point p, GridController c){
    }

    @Override
    public void wheelMovedDown(Point p, GridController c){
    }
}
