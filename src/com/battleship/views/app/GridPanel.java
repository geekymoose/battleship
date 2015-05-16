/* 
 * Creation:    Apr 1, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.constants.GameConstants;
import com.battleship.controllers.GridController;
import com.battleship.dynamic.ExplosionEvent;
import com.battleship.gridcursor.GridCursor;
import com.battleship.exceptions.ExecError;
import com.battleship.models.game.FleetGridModel;
import com.battleship.observers.ObservableModel;
import com.battleship.observers.ObserverModel;
import com.battleship.views.tools.ContentPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import javax.swing.JPanel;





/**
 * <h1>GridPanel</h1>
 * <p>
 * public abstract class GridPanel<br/>
 * extends ConstantPanel<br/>
 * implements MouseListener, MouseMotionListener
 * </p>
 * 
 * <p>Display a grid with BoxMapView.</p>
 * 
 *
 * @since   Apr 1, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public abstract class GridPanel extends ContentPanel implements MouseListener, 
                                                                MouseMotionListener,
                                                                MouseWheelListener,
                                                                GameConstants,
                                                                ObserverModel{
    //**************************************************************************
    // Variables - Constants
    //**************************************************************************
    protected       GridController  controller;
    protected       GridCursor      cursor;
    protected       BoxMapView      tabBox[][];
    protected       final int       gridWidth;
    protected       final int       gridHeight;
    protected       final int       gridType;
    
    protected       ArrayList<ExplosionEvent> listExplosions;
    
    
    
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    /**
     * Create a new FleetPanel
     * @param pPar  grid content parent (a PagePanel)
     * @param pC    controller for this grid    
     * @param pW    grid width
     * @param pH    grid height
     * @param pT    grid type
     * @param pDim  dimension of one BoxMap
     * @throws ExecError thrown if unable to create grid
     */
    public GridPanel(JPanel pPar, GridController pC, int pW, int pH, int pT, Dimension pDim) 
    throws ExecError{
        super(pPar);
        //this.setPreferredSize(Config.getDimValues_dim("dim-fleetgrid"));
        this.controller         = pC;
        Dimension dim           = new Dimension();
        dim.width               = pDim.width    * (pW);
        dim.height              = pDim.height   * (pH+1);
        this.gridWidth          = pW;
        this.gridHeight         = pH;
        this.gridType           = pT;
        this.listExplosions     = new ArrayList();
        this.cursor             = new GridCursor(this, controller, controller.getOwner());
        this.setPreferredSize(dim);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2);
        //draw grid
        for (int y = 0; y < this.gridHeight; y++) {
            for (int x = 0; x < this.gridWidth; x++) {
                this.tabBox[y][x].draw(g2);
            }
        }
    }


    @Override
    public void updateModel(ObservableModel o, Object arg){
        FleetGridModel m = (FleetGridModel)o;
        for (int y = 0; y < this.gridHeight; y++) {
            for (int x = 0; x < this.gridWidth; x++) {
                this.tabBox[y][x].setTargeted(m.getBoxMapAt(x, y).isTargeted());
                this.tabBox[y][x].setSprite(m.getBoxMapAt(x, y).getContent());
                this.tabBox[y][x].setHover(m.getBoxMapAt(x, y).isHover());
            }
        }
        this.listExplosions = m.getListExplosions();
        this.repaint();
        this.parentPage.repaint();
    }
    
    
    
    
    
    //**************************************************************************
    // Functions for BoxMapView management
    //**************************************************************************
    /**
     * Hide all BoxMap for this grid
     */
    public void hideAllBoxMap(){
        this.setHidenAllBoxMap(true);
    }
    
    /**
     * Set all BoxMap to visible (Not hidden)
     */
    public void visibleAllBoxMap(){
        this.setHidenAllBoxMap(false);
    }
    
    /**
     * Set hidden value for all box map
     * @param pValue 
     */
    private void setHidenAllBoxMap(boolean pValue){
        for(int y=0; y<this.gridHeight; y++){
            for(int x=0; x<this.gridWidth; x++){
                this.tabBox[y][x].setHidden(pValue);
            }
        }
        this.repaint();
    }
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return grid cursor
     * @return GridCursor cursor for this grid panel
     */
    public GridCursor getGridCursor(){
        return this.cursor;
    }
    
    /**
     * Return box Dimension
     * @return Dimension of the box
     */
    public Dimension getBoxDimension(){
        return this.tabBox[0][0].getDimension();
    }
    
    /**
     * Return type grid
     * @return int type grid
     */
    public int getTypeGrid(){
        return this.gridType;
    }
    
    
    
    
    
    //**************************************************************************
    // UI Functions
    //**************************************************************************
    @Override
    public void loadUI(){
        this.reloadUI();
    }
    
    @Override
    public void reloadUI(){
        this.controller.resetHoverAndAim();
        for(BoxMapView tmpTabBox[] : this.tabBox){
            for(BoxMapView box : tmpTabBox){
                box.reloadUI();
            }
        }
        this.repaint();
    }
    
    
    
    //**************************************************************************
    // Functions Mouse listener
    //**************************************************************************
    @Override
    public void mouseClicked(MouseEvent e){
        this.cursor.mouseClicked(e);
    }
    @Override
    public void mousePressed(MouseEvent e){
        this.cursor.mousePressed(e);
    }
    @Override
    public void mouseReleased(MouseEvent e){
        this.cursor.mouseReleased(e);
    }
    @Override
    public void mouseEntered(MouseEvent e){
        this.cursor.mouseEntered(e);
    }
    @Override
    public void mouseExited(MouseEvent e){
        this.cursor.mouseExited(e);
    }
    @Override
    public void mouseDragged(MouseEvent e){
        this.cursor.mouseDragged(e);
    }
    @Override
    public void mouseMoved(MouseEvent e){
        this.cursor.mouseMoved(e);
    }
    @Override
    public void mouseWheelMoved(MouseWheelEvent e){
        this.cursor.mouseWheelMoved(e);
    }
}
