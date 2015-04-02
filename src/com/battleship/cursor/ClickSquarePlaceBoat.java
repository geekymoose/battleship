/* 
 * Creation:    Apr 1, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.cursor;

import com.battleship.controllers.GridController;


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
    }


    @Override
    public void wheelMovedUp(int pX, int pY, GridController pController){
    }


    @Override
    public void wheelMovedDown(int pX, int pY, GridController pController){
    }
}
