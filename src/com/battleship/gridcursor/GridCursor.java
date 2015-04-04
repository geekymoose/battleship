/* 
 * Creation:    Apr 1, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.gridcursor;

import com.battleship.asset.GridCalculator;
import com.battleship.constants.GameConstants;
import com.battleship.controllers.GridController;
import com.battleship.main.DebugTrack;
import com.battleship.views.app.GridPanel;
import java.awt.Dimension;
import java.awt.Point;
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
public class GridCursor implements GameConstants{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private     final GridController    controller;
    private     GridPanel               view;
    
    private     int                     typeGrid;
    
    private     final ClickType         clickPlaceBoat;
    private     final ClickType         clickNoAction;
    private     final ClickType         clickNoArm;
    private     final ClickType         clickShoot;
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
        this.clickPlaceBoat         = new ClickPlaceBoat();
        this.clickNoAction          = new ClickNoAction();
        this.clickNoArm             = new ClickNoArm();
        this.clickShoot             = new ClickShoot();
        
        //Default value
        this.clickType              = this.clickNoAction;
        this.controller             = pController;
        this.view                   = pGrid;
        
        this.typeGrid               = this.view.getTypeGrid();
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    public Point processCoordinate(MouseEvent e, Dimension dim){
        switch(typeGrid){
            case GameConstants.GRID_TYPE_SQUARE:
                return GridCalculator.pxToSquareCoor(e.getX(), e.getY(), dim);
            case GameConstants.GRID_TYPE_HEXAGON:
                return GridCalculator.pxToHexaCoor(e.getX(), e.getY(), dim);
        }
        return null; //Should never happend
    }
    
    
    
    
    
    //**************************************************************************
    // Functions to call ClickType
    //**************************************************************************
    /**
     * Perform a left click: clicked
     * @param e Event performed
     */
    public void mouseClicked (MouseEvent e){
        Point p = processCoordinate(e, controller.getBoxDimension());
        if(SwingUtilities.isLeftMouseButton(e)){
            this.clickType.mouseClicked_Left(p, controller);
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
        Point p = processCoordinate(e, controller.getBoxDimension());
        if(SwingUtilities.isLeftMouseButton(e)){
            this.clickType.mousePressed_left(p, controller);
        }
    }
    
    
    /**
     * Perform a left click: pressed
     * @param e Event performed
     */
    public void mouseReleased(MouseEvent e){
        Point p = processCoordinate(e, controller.getBoxDimension());
        if(SwingUtilities.isLeftMouseButton(e)){
            this.clickType.mouseReleased_left(p, controller);
        }
    }
    
    public void mouseEntered(MouseEvent e){
        Point p = processCoordinate(e, controller.getBoxDimension());
    }
    
    public void mouseExited(MouseEvent e){
        Point p = processCoordinate(e, controller.getBoxDimension());
    }
    
    
    /**
     * Perform a dragged mouse mode. Get the coordinate of the current mouse
     * position
     * @param e Event performed
     */
    public void mouseDragged(MouseEvent e){
        Point p = processCoordinate(e, controller.getBoxDimension());
        if(SwingUtilities.isLeftMouseButton(e)){
            this.clickType.mouseDragged_left(p, controller);
        }
    }
    
    
    /**
     * Perform a move mouse mode.
     * @param e Event performed
     */
    public void mouseMoved(MouseEvent e){
        Point p = processCoordinate(e, controller.getBoxDimension());
        DebugTrack.showObjectToString(p);
        this.clickType.mouseMoved(p, controller);
    }
    
    
    /**
     * Perform a wheel move: process the move type (Down or up)
     * @param e Event performed
     */
    public void mouseWheelMoved(MouseWheelEvent e){
        Point p = processCoordinate(e, controller.getBoxDimension());
        this.clickType.wheelMovedDown(p, controller);
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
    public void setClickNoAction()  { clickType = clickNoAction; }
    public void setClickPlaceBoat() { clickType = clickPlaceBoat; }
    public void setClickNoArm()     { clickType = clickNoArm; }
    public void setClickShoot()     { clickType = clickShoot; }
}
