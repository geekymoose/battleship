/*
 * Class :      GridController
 * Creation:    Apr 2, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.controllers;

import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import com.battleship.models.game.BoxMap;
import com.battleship.models.game.FleetGridModel;
import com.battleship.models.game.Player;
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
    private FleetGridModel  model           = (FleetGridModel)this.m; //It's Ugly.. I know :p
    
    
    
    
    
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
    /**
     * Process a player shoot on this grid. If position given doesn't exists in 
     * grid aimed, do nothing 
     * @param p         position aimed
     * @param pShooter  player that is going to shoot
     */
    public void shootBoxMap(Point p, Player pShooter){
        this.resetAim();
        if((this.model.getBoxMapAt(p.x, p.y)) != null){
            pShooter.shootAt(p.x, p.y, this.model.getTabBoxMap());
        }
    }
    
    /**
     * Player aim at this position
     * @param p         position aimed
     * @param pShooter  player that is going to shoot
     */
    public void aimBoxMap(Point p, Player pShooter){
        this.resetAim();
        if((this.model.getBoxMapAt(p.x, p.y)) != null){
            pShooter.aimAt(p.x, p.y, this.model.getTabBoxMap());
        }
        this.model.notifyObservers(null);
    }
    
    public void hoverBoxMap(Point p){
        this.model.hoverBoxMap(p);
    }
    
    public void resetAim(){
        this.model.hoverBoxMap(null);
    }
    
    public boolean placeBoatAt(Point p){
        return this.model.getOwner().placeBoatAt(p, this.model.getCurrentOrientation());
    }
    
    public void resetFleetGrid(){
        this.model.resetFleetGrid();
    }
    
    public void switchNextOrientation() {
        this.model.switchNextOrientation();
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
    
    /**
     * Return grid owner
     * @return Player owner
     */
    public Player getOwner(){
        return this.model.getOwner();
    }
}
