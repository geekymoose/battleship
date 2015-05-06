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
 * <h1>GridHexaView</h1>
 * <p>
 * public class GridHexaView<br/>
 * extends GridPanel
 * </p>
 * <p>Display an hexagon grid</p>
 *
 * @date    Apr 1, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class GridHexaView extends GridPanel{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    
    
    

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
        super(p, c,w, h, t, d);
        //Create all box
        this.tabBox = new BoxMapViewHexagon[this.gridHeight][this.gridWidth];
        for (int y = 0; y < this.gridHeight; y++) {
            for (int x = 0; x < this.gridWidth; x++) {
                this.tabBox[y][x] = new BoxMapViewHexagon(x, y, d, new Water());
            }
        }
    }
    
    /**
     * Create a new Hexagon grid in view with the choice of the color
     * @param pParent
     * @param pController
     * @param pW
     * @param pH
     * @param pType
     * @param pDim
     * @param c Color for the borders of the grid
     * @throws ExecError 
     */
    public GridHexaView(JPanel pParent, GridController pController, 
                        int pW, int pH, int pType, Dimension pDim, Color c) throws ExecError{
        super(pParent, pController,pW, pH, pType, pDim);
        this.tabBox = new BoxMapViewHexagon[this.gridHeight][this.gridWidth];
        for (int y = 0; y < this.gridHeight; y++) {
            for (int x = 0; x < this.gridWidth; x++) {
                this.tabBox[y][x] = new BoxMapViewHexagon(x, y, pDim, new Water(), c);
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(ExplosionEvent e : this.listExplosions){
            int x   = (e.getPosition().x * (tabBox[0][0].getDimension().width-8))+15;
            int y   = (e.getPosition().y * tabBox[0][0].getDimension().height)+10;
            int dy  = (e.getPosition().x%2==0)? 0:25;
            g.drawImage(e.getCurrentImg(), x, y+dy, this);
        }
    }
}
