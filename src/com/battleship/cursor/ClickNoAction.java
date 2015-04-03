/*
 * Class :      ClickNoAction
 * Creation:    Apr 3, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.cursor;

import com.battleship.asset.GridCalculator;
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
    public void leftClickClicked(int pX, int pY, GridController pController){
    }
    
    
    @Override
    public void leftClickReleased(int pX, int pY, GridController pController){
    }
    
    
    @Override
    public void leftClickPressed(int pX, int pY, GridController pController){
    }


    @Override
    public void leftMouseDragged(int pX, int pY, GridController pController){
    }
    

    @Override
    public void mouseCursorMoved(int pX, int pY, GridController pController){
        System.out.println("DEBUG in ClickNoAction : "+pX+" - "+pY);
        System.out.println("DEBUG in ClickNoAction : "+GridCalculator.pxToHexaCoor(pX, pY, 50));
    }


    @Override
    public void wheelMovedUp(int pX, int pY, GridController pController){
    }


    @Override
    public void wheelMovedDown(int pX, int pY, GridController pController){
    }
}
