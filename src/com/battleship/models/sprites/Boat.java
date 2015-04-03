/* 
 * Creation : Feb 11, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.models.sprites;

import com.battleship.behaviors.Sprite;
import com.battleship.models.game.BoxMap;
import com.battleship.models.game.FleetGridModel;



/**
 * <h1>Boat</h1>
 * <p>
 * public abstract class Boat<br/>
 * implements Sprite
 * </p>
 *
 * <p>
 * Define a boat item. Every one in the fleet must extends boat.</p>
 * <ul>
 *  <li>AircraftCarrier</li>
 *  <li>Battleship</li>
 *  <li>Submarine</li>
 *  <li>Cruiser</li>
 *  <li>Destroyer</li>
 * </ul>
 *
 *
 * @date    Feb 11. 2015
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * @author  Contsantin MASSON
 */
public abstract class Boat{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    protected String            kindOfBoat;
    protected int               nbLives;
    protected int               orientation;
    protected Compartment[]     tabCompartments;
    protected FleetGridModel    grid;
    
    
    
    
    
    //**************************************************************************
    // Constructors - Initialization
    //**************************************************************************
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
    
    
    
    
    
    //**************************************************************************
    // Functions
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


    /**
     * Calculate the boat position from the front position
     *
     * @return true if position is valid, otherwise, return false
     */
    public boolean calculBoatPositions(){
        boolean isValid = true;
        for(int k=1; k<this.tabCompartments.length;k++){
            BoxMap next = this.tabCompartments[k-1].boxPosition.getNextBoxMap(orientation);
            if(next==null || !next.isEmpty()){
                isValid = false;
            }
            this.tabCompartments[k].boxPosition = next;
        }
        return isValid;
    }
    
    
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
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

    /**
     * Return current orientation
     * @return current orientation
     */
    public int getOrientation(){
        return orientation;
    }

    /**
     * Change current orientation. Boat position will be recalculated
     * @param pValue new orientation
     */
    public void setOrientation(int pValue){
        this.orientation = pValue;
        this.calculBoatPositions();
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
    protected class Compartment implements Sprite {
        protected   boolean     isDestroyed;
        protected   BoxMap      boxPosition;
        
        //Image data (Identification)
        protected   int[]       id_img;


        /**
         * Create a new compartment on BoxMap pPosition. Box map could be null, 
         * it means the boat is not placed at the moment
         *
         * @param pPosition BoxMap where to place this compartment
         */
        protected Compartment(BoxMap pPosition){
            this.isDestroyed    = false;
            this.boxPosition    = pPosition;
            this.id_img         = new int[NB_IMG];
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
        

        //**********************************************************************
        // Sprite implements 
        //**********************************************************************
        @Override
        public boolean hit(){
            if(this.isDestroyed){
                return false; //Already hit dude
            }
            Boat.this.lostOneLife();
            return true; //Boat hit
        }

        @Override
        public boolean canBeHit(){
            return isDestroyed;
        }

        @Override
        public int getImgId(int idImg){
            return (idImg<0 || idImg>NB_IMG)? id_img[0] : id_img[idImg];
        }

        @Override
        public void setImg(int pDef, int pDestroyed, int pHover, int pNovalid, int pValid){
            this.id_img[0] = pDef;
            this.id_img[1] = pDestroyed;
            this.id_img[2] = pHover;
            this.id_img[3] = pNovalid;
            this.id_img[4] = pValid;
        }


        @Override
        public void setHiddenImg(int pDef, int pDestroyed, int pHover, int pNovalid, int pValid){
            this.id_img[5] = pDef;
            this.id_img[6] = pDestroyed;
            this.id_img[7] = pHover;
            this.id_img[8] = pNovalid;
            this.id_img[9] = pValid;
        }
    } //----------------------------------END INNER CLASS-----------------------
}
