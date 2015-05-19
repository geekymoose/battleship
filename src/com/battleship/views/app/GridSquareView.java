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
 * <h1>GridSquareView</h1>
 * <p>public class GridSquareView</p>
 *
 * @since   Apr 1, 2015
 * @author  Constantin MASSON
 */
public class GridSquareView extends GridPanel{
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new square grid in view with default border color
     * @param p     parent PagePanel where grid is placed
     * @param c     grid controller
     * @param w     grid width
     * @param h     grid height
     * @param d     dimension of one BoxMap
     * @param t     grid type
     * @throws ExecError thrown if error during creation
     */
    public GridSquareView(JPanel p, GridController c, int w, int h, int t, Dimension d) throws ExecError{
        super(p, c,w, h, t);
        Dimension dim           = new Dimension();
        dim.width               = (d.width    * w) +2;
        dim.height              = (d.height   * w) +2;
        this.setPreferredSize(dim);
        this.tabBox = new BoxMapViewSquare[this.gridHeight][this.gridWidth];
        for (int y = 0; y < this.gridHeight; y++) {
            for (int x = 0; x < this.gridWidth; x++) {
                this.tabBox[y][x] = new BoxMapViewSquare(x, y, d, new Water(), this);
            }
        }
    }
    
    /**
     * Create a new square grid in view with specific border color
     * @param p     parent PagePanel where grid is placed
     * @param c     grid controller
     * @param w     grid width
     * @param h     grid height
     * @param d     dimension of one BoxMap
     * @param t     grid type
     * @param b     border color
     * @throws ExecError thrown if error during creation
     */
    public GridSquareView(JPanel p, GridController c, int w, int h, int t, Dimension d, Color b) 
    throws ExecError{
        super(p, c,w, h, t);
        Dimension dim           = new Dimension();
        dim.width               = (d.width    * w) +2;
        dim.height              = (d.height   * w) +2;
        this.setPreferredSize(dim);
        this.tabBox = new BoxMapViewSquare[this.gridHeight][this.gridWidth];
        for (int y = 0; y < this.gridHeight; y++) {
            for (int x = 0; x < this.gridWidth; x++) {
                this.tabBox[y][x] = new BoxMapViewSquare(x, y, d, new Water(), b, this);
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(ExplosionEvent e : this.listExplosions){
            Point p = GridCalculator.placeImgSquare(e.getPosition(), e.getImgDim(), this.getBoxDimension());
            g.drawImage(e.getCurrentImg(), p.x, p.y, this);
        }
    }
}
