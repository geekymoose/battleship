/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.models.game;

import com.battleship.models.sprites.Boat;
import java.util.ArrayList;





/**
 * <h1>FleetGridModel</h1>
 * <p>public abstract class FleetGridModel</p>
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
public abstract class FleetGridModel {
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
        this.tabBoxMap      = new BoxMap[pHeight][pWidth];
        this.nbAliveBoats   = 0;
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Return the BoxMap at position pX:pY
     * @param pX
     * @param pY
     * @return BoxMap
     */
    public BoxMap getBoxMapAt(int pX, int pY){
        return this.tabBoxMap[pY][pX];
    }
    
    /**
     * Return the next box map according to the direction given.
     * @param pX
     * @param pY
     * @param pDirection
     * @return 
     */
    public  abstract BoxMap getNextBoxMap(int pX, int pY, int pDirection);
    
    
    
    
    
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
