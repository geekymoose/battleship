/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.models.game;

import com.battleship.models.sprites.Boat;
import java.awt.Point;
import java.util.ArrayList;



/**
 * <h1>FleetGridModel</h1>
 * <p>
 * public abstract class FleetGridModel<br/>
 * extends Model
 * </p>
 * 
 * <p>
 * Grid for a fleet. Each player has got a fleet placed on a gridFleetModel. 
 * FleetGridModel is bound with one player (Its owner).
 * </p>
 * 
 * <h2>Grid Type</h2>
 * <p> There are different kind of FleetGrid</p>
 *  <ul>
 *      <li>FleetGridSquare</li>
 *      <li>FleetGridHexagon</li>
 *  </ul>
 * <h3>FleetGridSquare</h3>
 * <p>Use square coordinates like commons array displaying.</p>
 * <h3>FleetGridHexagon</h3>
 * <p>Hexagon grid</p>
 *
 * 
 * @date    Feb 9, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public abstract class FleetGridModel extends Model{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    protected int                   gridWidth;             
    protected int                   gridHeight;    
    protected int                   nbAliveBoats;
    protected Player                owner;
    protected BoxMap[][]            tabBoxMap;
    protected ArrayList<Boat>       listBoats;
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Initialize commons data for a new FleetGridMode
     * @param pWidth    Grid width
     * @param pHeight   Grid Height
     * @param pOwner    Fleet owner (It is a Player)
     */
    protected FleetGridModel(int pWidth, int pHeight, Player pOwner){
        this.gridWidth      = pWidth;
        this.gridHeight     = pHeight;
        this.nbAliveBoats   = 0;
        this.owner          = pOwner;
        this.listBoats      = new ArrayList();
        //this.owner.setFleetGrid(this); //Add this grid for this player
    }
    
    /**
     * Reset all boxMap at water value
     */
    public void resetFleetGrid(){
        for(int y=0; y<this.gridHeight; y++){
            for(int x=0; x<this.gridWidth; x++){
                this.getBoxMapAt(x, y).restContent();
            }
        }
        this.notifyObservers(null);
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Return the BoxMap at position x:y 
     * If this position is not in the grid, return null
     * @param pX x coordinate
     * @param pY y coordinate
     * @return BoxMap at x:y if exists, otherwise, return null
     */
    public BoxMap getBoxMapAt(int pX, int pY){
        if (pX<0 || pX>=this.gridWidth || pY<0 || pY>=this.gridHeight){
            return null;
        }
        return this.tabBoxMap[pY][pX];
    }
    
    /**
     * Target one box map at position point p
     * @param p coordinate where target is located in the grid
     */
    public void targetBoxMap(Point p){
        this.stopAiming();
        BoxMap box = this.getBoxMapAt(p.x, p.y);
        if(box!=null){
            box.aim();
        }
    }
    
    /**
     * Target several BoxMap
     * @param tab 
     */
    public void targetSeveralBoxMap(Point[] tab){
        for(Point p : tab){
            BoxMap box = this.getBoxMapAt(p.x, p.y);
            if(box!=null){
                box.aim();
            }
        }
        this.notifyObservers(null);
    }
    
    /**
     * stop aiming all tab
     */
    private void stopAiming(){
        for(int y=0; y<this.gridHeight; y++){
            for(int x=0; x<this.gridWidth; x++){
                this.tabBoxMap[y][x].stopAim();
            }
        }
    }
    
    /**
     * Check if this grid is valid, means all boats are placed in
     * @return 
     */
    public boolean isValidFleetGrid(){
        //return this.listBoats.size() == 5;
        return true; //DEBUG 
    }
    
    
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return the Fleet Grid by using a matrix
     * @return matrix with BoxMap
     */
    public BoxMap[][] getTabBoxMap(){
        return this.tabBoxMap;
    }
    
    /**
     * Set owner of this fleet. Beware, if owner already exists, will be replaced 
     * by this new owner. If null given, fleet will be owned by no one. (Owner is 
     * null)
     * @param pOwner
     */
    public void setOwner(Player pOwner){
        this.owner = pOwner;
    }
}
