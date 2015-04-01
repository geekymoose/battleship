/* 
 * Creation:    Apr 1, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.views.app;

import com.battleship.exceptions.ExecError;
import com.battleship.views.tools.PagePanel;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;





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
    public GridSquareView(PagePanel pParent, int pW, int pH, Dimension pDim, int pType) throws ExecError{
        super(pParent, pW, pH, pDim, pType);
    }

    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    @Override
    protected Point getBoxMapUnder(MouseEvent e){
        return null;
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
