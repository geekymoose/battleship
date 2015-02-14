/* 
 * Creation : Feb 11, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.models.sprites;

import com.battleship.models.game.BoxMap;
import com.battleship.models.game.FleetGridModel;



/**
 * <h1>Boat</h1>
 * <p>
 * public abstract class Boat
 * </p>
 *
 * <p>
 * Define a boat item. Every one in the fleet must extends boat.</p>
 * <ul>
 * <li>AircraftCarrier</li>
 * <li>Battleship</li>
 * <li>Submarine</li>
 * <li>Cruiser</li>
 * <li>Destroyer</li>
 * </ul>
 *
 *
 * @date Feb 11. 2015
 * @author Anthony CHAFFOT
 * @author Jessica FAVIN
 * @author Contsantin MASSON
 */
public abstract class Boat {
    protected String            kindOfBoat;
    protected int               nbLives;
    protected int               orientation;
    protected Compartment[]     tabCompartments;
    protected FleetGridModel    grid;


    /**
     * Create a new Boat. Boat has a default orientation, even if it is not
     * already placed on the grid. The FleetGridModel is the boat location (In
     * other words, it is the grid where he is)
     *
     * @param pName        Boat name (Commons name, it is rather the kind of 
     *                     boat as Cruiser)
     * @param pNbLives     Total number lives
     * @param pSize        Means nb compartments occupy by the boat
     * @param pOrientation current orientation
     * @param pGrid        where the boat is located (Could be null if not
     *                     placed)
     */
    protected Boat(String pName, int pNbLives, int pSize, int pOrientation, FleetGridModel pGrid){
        this.kindOfBoat         = pName;
        this.nbLives            = pNbLives;
        this.orientation        = pOrientation;
        this.grid               = pGrid;
        this.tabCompartments    = new Compartment[pSize];
        for(int i = 0; i < pSize; i++) {
            //initialisation des compartment??
            //tabCompartment[i]=new Compartment();
        }
    }


    /**
     * Check if boat is dead
     *
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


    /**
     * Calculate the boat position from the front position
     *
     * @return true if position is valid, otherwise, return false
     */
    public boolean calculBoatPositions(){
        //To Do mother fucker
        return false;
    }


    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return BoxMap position of the front compartment 
     * 
     * @return coordinates of the upper left boat compartment
     */
    public BoxMap getFrontPosition(){
        return tabCompartments[0].getBoxPosition();
    }


    /**
     * Return current number lives
     *
     * @return current number lives
     */
    public int getNbLives(){
        return nbLives;
    }


    /**
     * Return current orientation
     *
     * @return current orientation
     */
    public int getOrientation(){
        return orientation;
    }


    /**
     * Change current orientation. Boat position will be recalculated
     *
     * @param pValue new orientation
     */
    public void setOrientation(int pValue){
        this.orientation = pValue;
        this.calculBoatPositions();
    }



    //----------------------------------INTERN CLASS-----------------------------
    /*
     * Boat is compound of compartment. Each compartment must be placed on the
     * map and could be hit, destroyed etc
     */
    protected class Compartment implements Sprite {
        protected boolean   isDestroyed;
        protected BoxMap    boxPosition;


        /**
         * Create a new compartment on BoxMap pPosition. Box map could be null, 
         * it means the boat is not placed at the moment
         *
         * @param pPosition BoxMap where to place this compartment
         */
        protected Compartment(BoxMap pPosition){
            this.isDestroyed = false;
            this.boxPosition = pPosition;
        }


        /**
         * Check if compartment is destroyed
         *
         * @return true if destroyed, otherwise, return false
         */
        protected boolean isDestroyed(){
            return isDestroyed;
        }


        @Override
        public boolean hit(){
            if(this.isDestroyed){
                return false; //Already hit dude
            }
            Boat.this.lostOneLife();
            return true; //Boat hit
        }
        
        
        /**
         * Return the current position. Position is a BoxMap
         * @return BoxMap which is the position
         */
        protected BoxMap getBoxPosition(){
            return this.boxPosition;
        }
    } //----------------------------------END INTERN CLASS----------------------
    
    
    
    
    
    //**************************************************************************
    // Deprecated functions (Might be deleted later)
    //**************************************************************************
    /*
     * @deprecated Hit compartment at position x:y. This coordinates are the
     * grid position of the compartment. Note, if x:y should be a valid
     * coordinates (Means there is actually a compartment of this boat at x:y.
     * But, in case of bad coordinate, nothing is changed.
     * @param pX abscissa in the GridFleetModel
     * @param pY ordinate in the GridFleetModel
     * @return constant WAS_HIT if boat successfully hit, otherwise, return
     *         NOT_HIT
     */
    /*
    private int hitCompartmentAt(int pX, int pY){ //Use to be public
        Compartment currentCompartment;
        Point hitPoint = new Point(pX, pY);
        for(int i = 0; i < tabCompartments.length; i++) {
            currentCompartment = tabCompartments[i];
            if(currentCompartment.getCoordinates().equals(hitPoint)){
                currentCompartment.receiveDamage();
                return BoatsConstants.WAS_HIT;
            }
        }
        return BoatsConstants.NOT_HIT;
    }
    * */
}
