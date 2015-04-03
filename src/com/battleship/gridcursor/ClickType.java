/* 
 * Creation:    Apr 3, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.gridcursor;

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
     * @param x x pixel coordinate
     * @param y y pixel coordinate
     * @param c controller for this action
     */
    public void mouseClicked_Left (int x, int y, GridController c);
    
    /**
     * Left click performed: released
     * @param x x pixel coordinate
     * @param y y pixel coordinate
     * @param c controller for this action
     */
    public void mousePressed_left (int x, int y, GridController c);
    
    /**
     * Left click performed: pressed
     * @param x x pixel coordinate
     * @param y y pixel coordinate
     * @param c controller for this action
     */
    public void mouseReleased_left (int x, int y, GridController c);
    
    /**
     * Mouse entered
     * @param x x pixel coordinate
     * @param y y pixel coordinate
     * @param c controller for this action
     */
    public void mouseEntered (int x, int y, GridController c);
    
    /**
     * Mouse exited
     * @param x x pixel coordinate
     * @param y y pixel coordinate
     * @param c controller for this action
     */
    public void mouseExited (int x, int y, GridController c);
    
    /**
     * Left click is hold down and dragged
     * @param x x pixel coordinate
     * @param y y pixel coordinate
     * @param c controller for this action
     */
    public void mouseDragged_left (int x, int y, GridController c);
    
    
    /**
     * Cursor is moved
     * @param x x pixel coordinate
     * @param y y pixel coordinate
     * @param c controller for this action
     */
    public void mouseMoved (int x, int y, GridController c);
    
    
    /**
     * Wheel is moved up
     * @param x x pixel coordinate
     * @param y y pixel coordinate
     * @param c controller for this action
     */
    public void wheelMovedUp (int x, int y, GridController c);
    
    
    /**
     * Wheel is moved down
     * @param x x pixel coordinate
     * @param y y pixel coordinate
     * @param c controller for this action
     */
    public void wheelMovedDown (int x, int y, GridController c);
}
