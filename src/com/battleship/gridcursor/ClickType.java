/* 
 * Creation:    Apr 3, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.gridcursor;

import com.battleship.controllers.GridController;
import java.awt.Point;



/**
 * <h1>ClickType</h1>
 * <p>public interface ClickType </p>
 * 
 * <p>
 * Deprecated - The ClickType interface provides function to use during click performed. 
 * All coordinate values are pixel coordinate. In order to be used with a grid 
 * coordinate, use tools from controller as GridController
 * 
 * UPDATE : coordinate from Point p are actual grid coordinate!!!
 * </p>
 * 
 * @since   Apr 2, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public interface ClickType {
    
    /**
     * Left click performed: clicked
     * @param p coordinate in the grid
     * @param c controller for this action
     */
    public void mouseClicked_Left (Point p, GridController c);
    
    /**
     * Left click performed: released
     * @param p coordinate in the grid
     * @param c controller for this action
     */
    public void mousePressed_left (Point p, GridController c);
    
    /**
     * Left click performed: pressed
     * @param p coordinate in the grid
     * @param c controller for this action
     */
    public void mouseReleased_left (Point p, GridController c);
    
    /**
     * Mouse entered
     * @param p coordinate in the grid
     * @param c controller for this action
     */
    public void mouseEntered (Point p, GridController c);
    
    /**
     * Mouse exited
     * @param p coordinate in the grid
     * @param c controller for this action
     */
    public void mouseExited (Point p, GridController c);
    
    /**
     * Left click is hold down and dragged
     * @param p coordinate in the grid
     * @param c controller for this action
     */
    public void mouseDragged_left (Point p, GridController c);
    
    
    /**
     * Cursor is moved
     * @param p coordinate in the grid
     * @param c controller for this action
     */
    public void mouseMoved (Point p, GridController c);
    
    
    /**
     * Wheel is moved up
     * @param p coordinate in the grid
     * @param c controller for this action
     */
    public void wheelMovedUp (Point p, GridController c);
    
    
    /**
     * Wheel is moved down
     * @param p coordinate in the grid
     * @param c controller for this action
     */
    public void wheelMovedDown (Point p, GridController c);
}
