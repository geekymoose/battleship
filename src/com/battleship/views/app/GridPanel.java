/* 
 * Creation:    Apr 1, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.constants.GameConstants;
import com.battleship.exceptions.ExecError;
import com.battleship.views.tools.ContentPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
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
 * @date    Apr 1, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public abstract class GridPanel extends ContentPanel implements MouseListener, 
                                                                MouseMotionListener,
                                                                GameConstants{
    //**************************************************************************
    // Variables - Constants
    //**************************************************************************
    protected       BoxMapView  tabBox[][];
    protected       final int   gridWidth;
    protected       final int   gridHeight;
    protected       final int   gridType;
    
    
    
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    /**
     * Create a new FleetPanel
     * @param pParent   grid content parent (a PagePanel)
     * @param pGridW    grid width
     * @param pGridH    grid height
     * @param pDim      dimension of one BoxMap
     * @param pType     grid type
     * @throws ExecError thrown if unable to create grid
     */
    public GridPanel(JPanel pParent, int pGridW, int pGridH, Dimension pDim, int pType) 
    throws ExecError{
        super(pParent);
        //this.setPreferredSize(Config.getDimValues_dim("dim-fleetgrid"));
        Dimension dim   = new Dimension();
        dim.width       = pDim.width    * (pGridW);
        dim.height      = pDim.height   * (pGridH + 1);
        this.setPreferredSize(dim);
        
        
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.gridWidth  = pGridW;
        this.gridHeight = pGridH;
        this.gridType   = pType;
        
        
        switch(pType){
            case GRID_TYPE_SQUARE :
                this.tabBox = new BoxMapViewSquare[pGridH][pGridW];
                for (int y = 0; y < pGridH; y++) {
                    for (int x = 0; x < pGridW; x++) {
                        this.tabBox[y][x] = new BoxMapViewSquare(x, y, pDim);
                    }
                }
                break;
            case GRID_TYPE_HEXAGON :
                this.tabBox = new BoxMapViewHexagon[pGridH][pGridW];
                for (int y = 0; y < pGridH; y++) {
                    for (int x = 0; x < pGridW; x++) {
                        this.tabBox[y][x] = new BoxMapViewHexagon(x, y, pDim);
                    }
                }
                break;
        }
        
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
    
    
    /**
     * Convert absolute pixel position to coordinate position. 
     * (pxToCoor -> pixelToCoordinate)
     *
     * @param pX    x value
     * @param pY    y value 
     * @return
     */
    protected abstract Point pxToCoor(int pX, int pY);
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Hide all BoxMap for this grid
     */
    public void hideAllBoxMap(){
        for(int y=0; y<this.gridHeight; y++){
            for(int x=0; x<this.gridWidth; x++){
                this.tabBox[y][x].setVisible(false);
            }
        }
    }
    
    
    
    //**************************************************************************
    // Functions Mouse listener
    //**************************************************************************
    @Override
    public void mouseClicked(MouseEvent e){
    }
    @Override
    public void mousePressed(MouseEvent e){
    }
    @Override
    public void mouseReleased(MouseEvent e){
    }
    @Override
    public void mouseEntered(MouseEvent e){
    }
    @Override
    public void mouseExited(MouseEvent e){
    }
    @Override
    public void mouseDragged(MouseEvent e){
    }
    @Override
    public void mouseMoved(MouseEvent e){
    }
}
