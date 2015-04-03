/* 
 * Creation:    Apr 1, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.gridcursor;

import com.battleship.controllers.GridController;
import com.battleship.main.DebugTrack;
import com.battleship.views.app.GridPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import javax.swing.SwingUtilities;





/**
 * <h1>GridCursor</h1>
 * <p>
 public class GridCursor
 </p>
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
public class GridCursor{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private     final GridController    controller;
    private     GridPanel               view;
    
    private     final ClickType         clickHexaPlaceBoat;
    private     final ClickType         clickSquarePlaceBoat;
    private     final ClickType         clickNoAction;
    private     ClickType               clickType;
    
    
    
    
    
    //**************************************************************************
    // Constructor
    //**************************************************************************
    /**
     * Create a new cursor.
     * @param pGrid         View cursor is linked with
     * @param pController   controller for this Cursor
     */
    public GridCursor(GridPanel pGrid, GridController pController){
        this.clickHexaPlaceBoat     = new ClickHexaPlaceBoat();
        this.clickSquarePlaceBoat   = new ClickSquarePlaceBoat();
        this.clickNoAction          = new ClickNoAction();
        
        //Default value
        this.clickType              = this.clickNoAction;
        this.controller             = pController;
        this.view                   = pGrid;
    }
    
    
    
    
    
    //**************************************************************************
    // Functions to call ClickType
    //**************************************************************************
    /**
     * Perform a left click: clicked
     * @param e Event performed
     */
    public void mouseClicked (MouseEvent e){
        if(SwingUtilities.isLeftMouseButton(e)){
            this.clickType.mouseClicked_Left(e.getX(), e.getY(), controller);
        }
        else if(SwingUtilities.isRightMouseButton(e)){
        }
    }
    
    
    /**
     * This function is called if left click is performed and manage the action
     * to do (Depend of clickType value)
     * @param e Event performed
     */
    public void mousePressed(MouseEvent e){
        if(SwingUtilities.isLeftMouseButton(e)){
            this.clickType.mousePressed_left(e.getX(), e.getY(), controller);
        }
    }
    
    
    /**
     * Perform a left click: pressed
     * @param e Event performed
     */
    public void mouseReleased(MouseEvent e){
        if(SwingUtilities.isLeftMouseButton(e)){
            this.clickType.mouseReleased_left(e.getX(), e.getY(), controller);
        }
    }
    
    public void mouseEntered(MouseEvent e){
        
    }
    
    public void mouseExited(MouseEvent e){
        
    }
    
    
    /**
     * Perform a dragged mouse mode. Get the coordinate of the current mouse
     * position
     * @param e Event performed
     */
    public void mouseDragged(MouseEvent e){
        if(SwingUtilities.isLeftMouseButton(e)){
            this.clickType.mouseDragged_left(e.getX(), e.getY(), controller);
        }
    }
    
    
    /**
     * Perform a move mouse mode.
     * @param e Event performed
     */
    public void mouseMoved(MouseEvent e){
        this.clickType.mouseMoved(e.getX(), e.getY(), controller);
    }
    
    
    /**
     * Perform a wheel move: process the move type (Down or up)
     * @param e Event performed
     */
    public void mouseWheelMoved(MouseWheelEvent e){
        this.clickType.wheelMovedDown(e.getX(), e.getY(), controller);
    }
    
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Link cursor with a specific view grid
     * @param pGrid 
     */
    public void setGridView(GridPanel pGrid){
        this.view = pGrid;
    }
    
    
    
    
    //**************************************************************************
    // Setters for ClickType
    //**************************************************************************
    public void setClickHexaPlaceBoat()     { clickType = clickHexaPlaceBoat; }
    public void setClickSquarePlaceBoat()   { clickType = clickSquarePlaceBoat; }
    public void setClickNoAction()          { clickType = clickNoAction; }
}
