/* 
 * Creation : Feb 11, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.models.sprites;

import com.battleship.behaviors.Sprite;
import com.battleship.models.game.BoxMap;
import com.battleship.models.game.FleetGridModel;
import java.util.ArrayList;



/**
 * <h1>Boat</h1>
 * <p>public abstract class Boat</p>
 *
 * <p>Define a boat item. Every one in the fleet must extends boat.</p>
 * <ul>
 *  <li>AircraftCarrier</li>
 *  <li>Battleship</li>
 *  <li>Submarine</li>
 *  <li>Cruiser</li>
 *  <li>Destroyer</li>
 * </ul>
 *
 *
 * @since   Feb 11. 2015
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * @author  Contsantin MASSON
 */
public abstract class Boat{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    protected   final int           idBoat;
    protected String                boatname;
    protected int                   nbLives;
    protected int                   value;
    protected int                   orientation;
    protected final Compartment[]   tabCompartments;
    protected FleetGridModel        grid;
    
    
    
    
    
    //**************************************************************************
    // Constructors - Initialization
    //**************************************************************************
    /**
     * Create a new Boat. Boat has a default orientation, even if it is not
     * already placed on the grid. The FleetGridModel is the boat location (In
     * other words, it is the grid where he is).
     * Default orientation is horizontal.
     *
     * @param pBoatId   Boat identification number
     * @param pName     Boat name
     * @param pNbLives  Total number lives
     * @param pSize     Number of compartments occupy by the boat
     * @param pValue    value of this boat (Used for score value)
     */
    protected Boat(int pBoatId, String pName, int pNbLives, int pSize, int pValue){
        this.idBoat             = pBoatId;
        this.boatname           = pName;
        this.nbLives            = pNbLives;
        this.value              = pValue;
        this.orientation        = 0; //Orientation is set when palced
        this.grid               = null; //Not used atm
        this.tabCompartments    = new Compartment[pSize];
        for(int i = 0; i < pSize; i++) {
            tabCompartments[i]  = new Compartment(null);
        }
    }
    
    
    
    
    
    //**************************************************************************
    // Check Functions - Usefull functions
    //**************************************************************************
    /**
     * Check if boat is dead
     * @return true if the boat has sinked (is dead), otherwise, false
     */
    public boolean isDead(){
        return (nbLives <= 0);
    }

    /**
     * Takes one life from the boat, then, check if is dead
     *
     * @return true if the boat is dead, false otherwise
     */
    public boolean lostOneLife(){
        this.nbLives--;
        return this.isDead();
    }
    
    
    
    
    
    //**************************************************************************
    // Functions for placement
    //**************************************************************************
    /**
     * Check if boat is placed on a grid
     * @return true if placed, otherwise, return false
     */
    public boolean isPlaced(){
        return this.tabCompartments[0] != null;
    }
    
    /**
     * Check if position started at pBox map position is a valid position. 
     * Using specific orientation
     * @param pBox          First box position
     * @param pOrientation  Boat orientation
     * @return true if valid, otherwise, return false
     */
    public boolean isValidPosition(BoxMap pBox, int pOrientation){
        BoxMap  next    = pBox;
        //Check if position is valid
        for(int k=0; k<this.tabCompartments.length; k++){
            if(next==null){
                return false;
            }
            else if((!next.isEmpty()) && next.getContent().getId() != this.idBoat){
                return false;
            }
            //Check if position is not stick with another boat
            ArrayList<BoxMap> l = next.getNeighbor();
            for (BoxMap b : l){
                if(b.getContent().getId() != this.idBoat && b.getContent().getId() != Sprite.WATER){
                    return false;
                }
            }
            next = next.getNextBoxMap(pOrientation);
        }
        return true;
    }
    
    /**
     * Place boat in a grid. At a BoxMap position. If position is not valid, nothing 
     * is done and false is returned. Orientation used is the current one. 
     * If boat was already placed on the grid, water replace old position
     * @param pBox          first box position for this boat (Front box position)
     * @param pOrientation  Boat orientation
     * @return true if placed successfully, otherwise, return false
     */
    public boolean placeAt(BoxMap pBox, int pOrientation){
        if(!isValidPosition(pBox, pOrientation)){
            return false;
        }
        this.resetPosition();
        this.orientation = pOrientation;
        BoxMap box = pBox;
        this.tabCompartments[0].setBoxPosition(box); //Place first position
        for(int k=1; k<this.tabCompartments.length; k++){
            box = box.getNextBoxMap(pOrientation);
            this.tabCompartments[k].setBoxPosition(box);
        }
        pBox.getFleetGrid().addBoat(this);//Add this boat in the grid
        return true;
    }
    
    /**
     * Reset the boat position, it will be no more placed on a grid.
     */
    public void resetPosition(){
        for(int k=0; k<this.tabCompartments.length; k++){
            this.tabCompartments[k].setBoxPosition(null);
        }
    }
    
    
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return boat identification number
     * @return int id number
     */
    public int getBoatId(){
        return this.idBoat;
    }
    
    /**
     * Return boat orientation
     * @return int orientation
     */
    public int getOrientation(){
        return this.orientation;
    }
    
    /**
     * Return BoxMap position of the front compartment
     * @return coordinates of the upper left boat compartment
     */
    public BoxMap getFrontPosition(){
        return tabCompartments[0].getBoxPosition();
    }

    /**
     * Return current number lives
     * @return current number lives
     */
    public int getNbLives(){
        return nbLives;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    //**************************************************************************
    // Inner class : Compartment
    //**************************************************************************
    /**
     * <h1>Boat.Compartment</h1>
     * <p>
     * protected class Compartment<br/>
     * implements Sprite
     * </p>
     * <p>
     * Boat is compound of compartment. Each compartment must be placed on the
     * map and could be hit, destroyed etc
     * </p>
     * 
     * @date    Feb 11. 2015
     * @author  Anthony CHAFFOT
     * @author  Jessica FAVIN
     * @author  Contsantin MASSON
     * 
     * @see Boat
     * @see Sprite
     */
    private class Compartment implements Sprite {
        private     boolean     isDestroyed;
        private     BoxMap      boxPosition;
        private     int         idImgExplosion;
        
        

        //**********************************************************************
        // Constructors - Initialization
        //**********************************************************************
        /**
         * Create a new compartment on BoxMap pPosition. Box map could be null, 
         * it means the boat is not placed at the moment
         *
         * @param pPosition BoxMap where to place this compartment
         */
        protected Compartment(BoxMap pPosition){
            this.isDestroyed    = false;
            this.boxPosition    = pPosition;
            this.idImgExplosion = 105001;
        }


        /**
         * Check if compartment is destroyed
         *
         * @return true if destroyed, otherwise, return false
         */
        protected boolean isDestroyed(){
            return isDestroyed;
        }
        

        //**********************************************************************
        // Getters - Setters
        //**********************************************************************
        /**
         * Return the current position. Position is a BoxMap
         * @return BoxMap which is the position
         */
        protected BoxMap getBoxPosition(){
            return this.boxPosition;
        }
        
        /**
         * Set compartment position. If boat was already located on a grid, 
         * old position will be lost (And water replace this old position).
         * New position should be valid (Exists etc). 
         * If null given, this compartment is no longer in a grid
         * @param pPosition boxMap where compartment has to be placed
         */
        protected void setBoxPosition(BoxMap pPosition){
            if(this.boxPosition!=null){
                this.boxPosition.setContent(new Water());
            }
            this.boxPosition = pPosition;
            if(pPosition != null){
                pPosition.setContent(this);
            }
        }
        

        //**********************************************************************
        // Sprite implements
        //**********************************************************************
        @Override
        public boolean hit(){
            if(this.isDestroyed){
                return false; //Already hit dude
            }
            Boat.this.lostOneLife();
            this.isDestroyed = true;
            return true; //Boat hit
        }

        @Override
        public boolean canBeHit(){
            return !isDestroyed;
        }

        @Override
        public int getId(){
            return idBoat;
        }
        
        @Override
        public int getState(){
            return (this.isDestroyed() ? Sprite.DEAD_BOAT : Sprite.ALIVE_BOAT);
        }
        
        @Override
        public int getValue(){
            return value;
        }

        @Override
        public int getExplosion(){
            return this.idImgExplosion;
        }
    } //----------------------------------END INNER CLASS-----------------------
}
