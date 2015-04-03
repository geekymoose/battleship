/*
 * Class :      ClickNoAction
 * Creation:    Apr 3, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.gridcursor;

import com.battleship.controllers.GridController;





/**
 * <h1>ClickNoAction</h1>
 * <p>public class ClickNoAction</p>
 *
 * @author Constantin MASSON
 * @date Apr 3, 2015
 */
public class ClickNoAction implements ClickType{
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
    }
    
    @Override
    public void wheelMovedUp(int x, int y, GridController c){
    }
    
    @Override
    public void wheelMovedDown(int x, int y, GridController c){
    }
}
