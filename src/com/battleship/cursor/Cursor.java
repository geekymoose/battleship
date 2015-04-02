/* 
 * Creation:    Apr 1, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.cursor;

import com.battleship.controllers.GridController;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.SwingUtilities;





/**
 * <h1>Cursor</h1>
 * <p>
 * public class Cursor
 * </p>
 * <p>
 * This class is used to manage cursor action for vectorial drawing software
 * When user click on the draw panel, the action performed could be different
 * depending on typeClick
 * </p>
 * 
 * @date    Apr 2, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class Cursor{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private     final ClickType     clickHexaPlaceBoat;
    private     final ClickType     clickSquarePlaceBoat;
    private     ClickType           clickType;
    
    
    
    
    
    //**************************************************************************
    // Constructor
    //**************************************************************************
    /**
     * Create a new cursor
     * The default behavior is to draw a point
     */
    public Cursor(){
        this.clickHexaPlaceBoat     = new ClickHexaPlaceBoat();
        this.clickSquarePlaceBoat   = new ClickSquarePlaceBoat();
        
        //Default value
        this.clickType              = null;
    }
    
    
    
    
    
    //**************************************************************************
    // Functions to call ClickType
    //**************************************************************************
    /**
     * Perform a left click: clicked
     * @param e Event performed
     * @param pController controller where to do action
     */
    public void clickClicked (MouseEvent e, GridController pController){
        if(SwingUtilities.isLeftMouseButton(e)){
            this.clickType.leftClickClicked(e.getX(), e.getY(), pController);
        }
        else if(SwingUtilities.isRightMouseButton(e)){
        }
    }
    
    
    /**
     * This function is called if left click is performed and manage the action
     * to do (Depend of clickType value)
     * @param e Event performed
     * @param pController controller where to do action
     */
    public void clickReleased(MouseEvent e, GridController pController){
        if(SwingUtilities.isLeftMouseButton(e)){
            this.clickType.leftClickReleased(e.getX(), e.getY(), pController);
        }
    }
    
    
    /**
     * Perform a left click: pressed
     * @param e Event performed
     * @param pController controller where to do action
     */
    public void clickPressed(MouseEvent e, GridController pController){
        if(SwingUtilities.isLeftMouseButton(e)){
            this.clickType.leftClickPressed(e.getX(), e.getY(), pController);
        }
    }
    
    
    /**
     * Perform a dragged mouse mode. Get the coordinate of the current mouse
     * position
     * @param e Event performed
     * @param pController controller where to do action
     */
    public void mouseDragged(MouseEvent e, GridController pController){
        if(SwingUtilities.isLeftMouseButton(e)){
            this.clickType.leftMouseDragged(e.getX(), e.getY(), pController);
        }
    }
    
    
    /**
     * Perform a move mouse mode.
     * @param e Event performed
     * @param pController controller where to do action
     */
    public void mouseCursorMoved(MouseEvent e, GridController pController){
        this.clickType.mouseCursorMoved(e.getX(), e.getY(), pController);
    }
    
    
    /**
     * Perform a wheel move: process the move type (Down or up)
     * @param e Event performed
     * @param pController controller where to do action
     */
    public void mouseWheelMoved(MouseWheelEvent e, GridController pController){
        if(e.isAltDown()){
            this.clickType.wheelMovedDown(e.getX(), e.getY(), pController);
        }
        else{
            this.clickType.wheelMovedUp(e.getX(), e.getY(), pController);
        }
    }
    
    
    
    
    //**************************************************************************
    // Setters for ClickType
    //**************************************************************************
    public void setClickHexaPlaceBoat(){
        this.clickType = this.clickHexaPlaceBoat;
    }
    public void setClickSquarePlaceBoat(){
        this.clickType = this.clickSquarePlaceBoat;
    }
}
