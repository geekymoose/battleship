/*
 * Class :      GridController
 * Creation:    Apr 2, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.controllers;

import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import com.battleship.models.game.FleetGridModel;
import com.battleship.views.app.GridPanel;
import java.awt.Dimension;
import java.awt.Point;





/**
 * <h1>GridController</h1>
 * <p>public class GridController</p>
 *
 * @date    Apr 2, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public class GridController extends Controller{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private FleetGridModel model = (FleetGridModel)this.m; //It's Ugly.. I know :p
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    public GridController(Object pModel) throws ExecError{
        super(pModel);
        DebugTrack.showInitMsg("Create GridController controller");
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    public void targetBoxMap(Point p){
        this.model.targetBoxMap(p);
    }
    
    public void resetFleetGrid(){
        this.model.resetFleetGrid();
    }
    
    
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return box Dimension
     * @return Dimension
     */
    public Dimension getBoxDimension(){
        return ((GridPanel)this.v).getBoxDimension();
    }
}
