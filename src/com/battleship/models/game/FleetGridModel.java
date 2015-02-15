/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.models.game;

import com.battleship.models.sprites.Boat;
import java.util.ArrayList;





/**
 * <h1>FleetGridModel</h1>
 * <p>
 * public abstract class FleetGridModel
 * </p>
 * 
 * <p>
 * Grid for a fleet. Each player has got a fleet placed on a gridFleetModel. 
 * FleetGridModel is bound with one player (Its owner).
 * </p>
 * 
 * <h2>Grid Type</h2>
 * <p> There are different kind of FleetGrid<p>
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
public abstract class FleetGridModel{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    protected int                   gridWidth;             
    protected int                   gridHeight;    
    protected Player                owner;
    protected BoxMap[][]            tabBoxMap;
    protected ArrayList<Boat>       listBoats;
    protected int                   nbAliveBoats;
    
    
    
    
    
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
        this.owner          = pOwner;
        this.listBoats      = new ArrayList();
        this.nbAliveBoats   = 0;
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
        /*
         * Check if coordinates are on the grid, otherwise, return null
         */
        if (pX<0 || pX>=this.gridWidth || pY<0 || pY>=this.gridHeight){
            return null;
        }
        return this.tabBoxMap[pY][pX];
    }
    
    /**
     * @deprecated Not sure we going to use it
     * Return the next box map according to the direction given.
     * @param pCurrentBoxMap    Current position (Position is a BoxMap)
     * @param pDirection        Direction
     * @return next BoxMap, null if there is no next BoxMap (Border reached)
     */
    public abstract BoxMap getNextBoxMap(BoxMap pCurrentBoxMap, int pDirection);
    
    
    
    
    
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
}
