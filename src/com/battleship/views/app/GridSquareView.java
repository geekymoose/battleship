/* 
 * Creation:    Apr 1, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.views.app;

import com.battleship.controllers.GridController;
import com.battleship.dynamic.ExplosionEvent;
import com.battleship.exceptions.ExecError;
import com.battleship.models.sprites.Water;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;





/**
 * <h1>GridSquareView</h1>
 * <p>public class GridSquareView</p>
 *
 * @author Constantin MASSON
 * @date Apr 1, 2015
 */
public class GridSquareView extends GridPanel{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private Dimension dimBox;
    
    
    
    
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new square grid in view
     * @param p     parent PagePanel where grid is placed
     * @param c     grid controller
     * @param w     grid width
     * @param h     grid height
     * @param d     dimension of one BoxMap
     * @param t     grid type
     * @throws ExecError thrown if error during creation
     */
    public GridSquareView(JPanel p, GridController c, int w, int h, int t, Dimension d) throws ExecError{
        super(p, c,w, h, t, d);
        this.dimBox = d;
        this.tabBox = new BoxMapViewSquare[this.gridHeight][this.gridWidth];
        for (int y = 0; y < this.gridHeight; y++) {
            for (int x = 0; x < this.gridWidth; x++) {
                this.tabBox[y][x] = new BoxMapViewSquare(x, y, d, new Water());
            }
        }
    }
    
    /**
     * Create a new square grid in view
     * @param pParent       parent PagePanel where grid is placed
     * @param pController   grid controller
     * @param pW            grid width
     * @param pH            grid height
     * @param pDim          dimension of one BoxMap
     * @param pType         grid type
     * @param c             Color of the borders
     * @throws ExecError thrown if error during creation
     */
    public GridSquareView(JPanel pParent, GridController pController, 
                        int pW, int pH, int pType, Dimension pDim, Color c) throws ExecError{
        super(pParent, pController,pW, pH, pType, pDim);
        this.dimBox = pDim;
        this.tabBox = new BoxMapViewSquare[this.gridHeight][this.gridWidth];
        for (int y = 0; y < this.gridHeight; y++) {
            for (int x = 0; x < this.gridWidth; x++) {
                this.tabBox[y][x] = new BoxMapViewSquare(x, y, pDim, new Water(), c);
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(ExplosionEvent e : this.listExplosions){
            int x = (e.getPosition().x * tabBox[0][0].getDimension().width)+5;
            int y = (e.getPosition().y * tabBox[0][0].getDimension().height)+8;
            g.drawImage(e.getCurrentImg(), x, y, this);
        }
    }
}
