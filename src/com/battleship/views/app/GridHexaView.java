/* 
 * Creation:    Apr 1, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.controllers.GridController;
import com.battleship.exceptions.ExecError;
import com.battleship.observers.ObservableModel;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
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
     * @param pParent       parent PagePanel where grid is placed
     * @param pController   grid controller
     * @param pW            grid width
     * @param pH            grid height
     * @param pDim          dimension of one BoxMap
     * @param pType         grid type
     * @throws ExecError thrown if error during creation
     */
    public GridHexaView(JPanel pParent, GridController pController, 
                        int pW, int pH, int pType, Dimension pDim) throws ExecError{
        super(pParent, pController,pW, pH, pType, pDim);
        this.tabBox = new BoxMapViewHexagon[this.gridHeight][this.gridWidth];
        for (int y = 0; y < this.gridHeight; y++) {
            for (int x = 0; x < this.gridWidth; x++) {
                this.tabBox[y][x] = new BoxMapViewHexagon(x, y, pDim);
            }
        }
    }


    @Override
    public void update(ObservableModel o, Object arg){
     
    }
}
