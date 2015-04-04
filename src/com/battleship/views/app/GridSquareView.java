/* 
 * Creation:    Apr 1, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.views.app;

import com.battleship.controllers.GridController;
import com.battleship.exceptions.ExecError;
import com.battleship.models.sprites.Water;
import java.awt.Dimension;
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
}
