/* 
 * Creation:    Apr 1, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.views.app;

import com.battleship.exceptions.ExecError;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
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
     * @param pParent   parent PagePanel where grid is placed
     * @param pW        grid width
     * @param pH        grid height
     * @param pDim      dimension of one BoxMap
     * @param pType     grid type
     * @throws ExecError thrown if error during creation
     */
    public GridSquareView(JPanel pParent, int pW, int pH, Dimension pDim, int pType) throws ExecError{
        super(pParent, pW, pH, pDim, pType);
        this.dimBox = pDim;
    }

    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    @Override
    protected Point pxToCoor(int pX, int pY){
        int x = pX/this.dimBox.width;
        int y = pY/this.dimBox.height;
        return new Point(x, y);
    }
    
    
    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        Point p = pxToCoor(e.getX(), e.getY());
    }
}
