/* 
 * Creation:    Apr 3, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.cursor;

import com.battleship.controllers.GridController;



/**
 * <h1>ClickType</h1>
 * <p>public interface ClickType </p>
 * 
 * <p>
 * The ClickType interface provides function to use during click performed. 
 * All coordinate values are pixel coordinate. In order to be used with a grid 
 * coordinate, use tools from controller as GridController
 * </p>
 * 
 * @date    Apr 2, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public interface ClickType {
    
    /**
     * Left click performed: clicked
     * @param pX x grid coordinate
     * @param pY y grid coordinate
     * @param pController controller where to do action
     */
    public void leftClickClicked (int pX, int pY, GridController pController);
    
    
    /**
     * Left click performed: released
     * @param pX x grid coordinate
     * @param pY y grid coordinate
     * @param pController controller where to do action
     */
    public void leftClickReleased(int pX, int pY, GridController pController);
    
    
    /**
     * Left click performed: pressed
     * @param pX x grid coordinate
     * @param pY y grid coordinate
     * @param pController controller where to do action
     */
    public void leftClickPressed(int pX, int pY, GridController pController);
    
    
    /**
     * Left click is hold down and dragged
     * @param pX x grid coordinate
     * @param pY y grid coordinate
     * @param pController controller where to do action
     */
    public void leftMouseDragged(int pX, int pY, GridController pController);
    
    
    /**
     * Cursor is moved in the DrawPanel
     * @param pX x grid coordinate
     * @param pY y grid coordinate
     * @param pController controller where to do action
     */
    public void mouseCursorMoved(int pX, int pY, GridController pController);
    
    
    /**
     * Wheel is moved up in the DrawPanel
     * @param pX x grid coordinate
     * @param pY y grid coordinate
     * @param pController controller where to do action
     */
    public void wheelMovedUp(int pX, int pY, GridController pController);
    
    
    /**
     * Wheel is moved down in the DrawPanel
     * @param pX x grid coordinate
     * @param pY y grid coordinate
     * @param pController controller where to do action
     */
    public void wheelMovedDown(int pX, int pY, GridController pController);
}
