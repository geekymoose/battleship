/* 
 * Creation:    Apr 1, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.asset.GridCalculator;
import com.battleship.controllers.GridController;
import com.battleship.dynamic.ExplosionEvent;
import com.battleship.exceptions.ExecError;
import com.battleship.models.sprites.Water;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;



/**
 * <h1>GridHexaView</h1>
 * <p>
 * public class GridHexaView<br/>
 * extends GridPanel
 * </p>
 * <p>Display an hexagon grid</p>
 *
 * @since   Apr 1, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class GridHexaView extends GridPanel{
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new Hexagon grid in view
     * @param p     parent PagePanel where grid is placed
     * @param c     grid controller
     * @param w     grid width
     * @param h     grid height
     * @param d     dimension of one BoxMap
     * @param t     grid type
     * @throws ExecError thrown if error during creation
     */
    public GridHexaView(JPanel p, GridController c, int w, int h, int t, Dimension d) throws ExecError{
        super(p, c,w, h, t);
        Dimension dim           = new Dimension();
        dim.width               = (d.width    * w) + 1;
        dim.height              = (d.height   * w) + d.height/2 + 2;
        this.setPreferredSize(dim);
        //Create all box
        this.tabBox = new BoxMapViewHexagon[this.gridHeight][this.gridWidth];
        for (int y = 0; y < this.gridHeight; y++) {
            for (int x = 0; x < this.gridWidth; x++) {
                this.tabBox[y][x] = new BoxMapViewHexagon(x, y, d, new Water(), this);
            }
        }
    }
    
    /**
     * Create a new Hexagon grid in view with the choice of the color
     * @param p     parent PagePanel where grid is placed
     * @param c     grid controller
     * @param w     grid width
     * @param h     grid height
     * @param d     dimension of one BoxMap
     * @param t     grid type
     * @param co    Color for the borders of the grid
     * @throws ExecError thrown if error during creation
     */
    public GridHexaView(JPanel p, GridController c, int w, int h, int t, Dimension d, Color co) 
    throws ExecError{
        super(p, c,w, h, t);
        Dimension dim           = new Dimension();
        dim.width               = (d.width    * w) - d.width;
        dim.height              = (d.height   * w) + d.height/2 + 2;
        this.setPreferredSize(dim);
        this.tabBox = new BoxMapViewHexagon[this.gridHeight][this.gridWidth];
        for (int y = 0; y < this.gridHeight; y++) {
            for (int x = 0; x < this.gridWidth; x++) {
                this.tabBox[y][x] = new BoxMapViewHexagon(x, y, d, new Water(), co, this);
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(ExplosionEvent e : this.listExplosions){
            Point p = GridCalculator.placeImgHexa(e.getPosition(), e.getImgDim(), this.getBoxDimension());
            g.drawImage(e.getCurrentImg(), p.x, p.y, this);
        }
    }
}
