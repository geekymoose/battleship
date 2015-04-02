/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.models.game;

import com.battleship.behaviors.Target;
import com.battleship.behaviors.Sprite;
import com.battleship.constants.GameConstants;
import com.battleship.models.sprites.Water;





/**
 * <h1>BoxMap</h1>
 * <p>
 * public class BoxMap<br/>
 * implements Target
 * </p>
 * 
 * <p>
 * This class represents a square on GridFleetModel. There are some different kind 
 * of BoxMap, depending to the grid type 
 * (ex: square or hexagon), BoxMap is only one compartment in this grid.<br/>
 * BoxMap contains a Sprite which is an item as a Boat or water. <br/>
 * BoxMap can be targeted by a shoot, that is why it implements Target.
 * </p>
 *
 * 
 * @date    Feb 9, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 * @see     GridFleetModel
 */
public abstract class BoxMap implements Target, GameConstants{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    protected   Sprite                  content;
    protected   final int               posX;
    protected   final int               posY;
    protected   final FleetGridModel    grid;
    public      boolean                 isTargeted; //Used for Target interface
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new BoxMap with a Sprite inside
     * @param pX        x coordinate
     * @param pY        y coordinate
     * @param pSprite   sprite to add inside this BoxMap
     * @param pGrid     grid where the BoxMap is (This grid contains the new BoxMap)
     */
    protected BoxMap(int pX, int pY, Sprite pSprite, FleetGridModel pGrid){
        this.content    = pSprite;
        this.posX       = pX;
        this.posY       = pY;
        this.grid       = pGrid;
        this.isTargeted = false;
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Return the next box map according to the direction given. 
     * In function of grid, some direction are forgiven. If one is used, 
     * null will be returned. (Note it should not append)
     * @param pDirection direction
     * @return next BoxMap, null if there is no next BoxMap (Border reached)
     */
    public abstract BoxMap getNextBoxMap(int pDirection);
    
    /**
     * Check is this BoxMap is empty : there is only water here (Means content 
     * is instance of Water
     * @return true if empty, otherwise, return false
     */
    public boolean isEmpty(){
        return (this.content instanceof Water);
    }
    
    
    
    
    
    //**************************************************************************
    // Functions from Target implements
    //**************************************************************************
    @Override
    public boolean hit(){
        return this.content.hit();
    }

    @Override
    public boolean isValidTarget(){
        return this.content.canBeHit();
    }
    
    @Override
    public boolean isTargeted(){
        return this.isTargeted;
    }
    
    @Override
    public boolean aim(){
        this.isTargeted = true;
        return true;
    }
    
    @Override
    public void stopAim(){
        this.isTargeted = false;
    }
    
    
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return the content in this BoxMap
     * @return the content in this BoxMap
     */
    public Sprite getContent(){
        return this.content;
    }
    
    /**
     * return x coordinate
     * @return x coordinate
     */
    public int getPosX(){
        return this.posX;
    }
    
    /**
     * Return y coordinate
     * @return y coordinate
     */
    public int getPosY(){
        return this.posY;
    }
}
