/*
 * File :       Boat
 * Creation :   11 févr. 2015
 * Author :     Anthony CHAFFOT
 * Author :     Jessica Favin
 * Author :     Constantin MASSON
 * 
 * Projet computer science Java L2 - Battleship
 * 
 */

package com.battleship.models;

import com.battleship.constants.BoatsConstants;
import java.awt.Point;





/**
 * <h1>Boat</h1>
 * <p>
 public class Boat<br/>
 * </p>
 * 
 * <p>Boats for the player's fleet</p>
 * <p>Each player has 5 types of boats</p>
 * <ul>
 * <li>AircraftCarrier</li>
 * <li>Battleship</li>
 * <li>Submarine</li>
 * <li>Cruiser</li>
 * <li>Destroyer</li>
 * </ul>
 * 
 * @date    11 févr. 2015
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * @author  Contsantin MASSON
 */
public abstract class Boat {
   protected String kindOfBoat;
   protected int nbLives;
   protected Compartment[] tabCompartments;
   protected int orientation;
   protected FleetGridModel grid;
   
   /**
    * 
    * @param pName
    * @param pNbLives
    * @param pSize
    * @param pOrientation
    * @param pGrid
    *
    */
   protected Boat(String pName, int pNbLives, int pSize, int pOrientation, FleetGridModel pGrid){
      this.kindOfBoat=pName;
      this.nbLives=pNbLives;
      this.tabCompartments=new Compartment[pSize];
      for(int i=0; i<pSize; i++){
         //initialisation des compartment??
         //tabCompartment[i]=new Compartment();
      }
      this.orientation=pOrientation;
      this.grid=pGrid;
   }
   
   /**
    * @return true if the boat has sinked (is considered dead), false otherwise
    */
   public boolean isDead(){
      return (nbLives<=0);
   }
   
   /**
    * takes one life from the boat 
    * @return true if the boat is dead, false otherwise (using isDead() method)
    */
   public boolean lostOneLife(){
      nbLives--;
      return this.isDead();
      
   }
   
   public boolean calculBoatPositions(){
      
      //hasNext pSize orientation calcul automatiquement le suivant avec l'orientation et en fonction de la grid
      //case vide
       return false;
   }
   
   /**
    * @param pX abscissa in the GridFleetModel
    * @param pY ordinate in the GridFleetModel
    * @return constant WAS_HIT if boat successfylly hit, NOT_HIT oterwise
    */
   public int hitCompartmentAt(int pX, int pY){
      Compartment currentCompartment;
      Point hitPoint= new Point(pX,pY);
      for(int i=0; i<tabCompartments.length; i++){
         currentCompartment=tabCompartments[i];
         if(currentCompartment.getCoordinates().equals(hitPoint)){
            currentCompartment.receiveDamage();
            return BoatsConstants.WAS_HIT;
         }
      }
      return BoatsConstants.NOT_HIT;
   }
   
   /**
    * @return the coordinates of the upper left compartment of the boat
    */
   public Point getFrontPosition(){
      return tabCompartments[0].getCoordinates();
   }
   
   /**
    * @return --list-- of the coordinates of all the compartment of the boat
    */
   public Point[] getCompartmentPositions(){
      Point[] positions=new Point[tabCompartments.length];
      for(int i=0; i<positions.length; i++){
         positions[i]=new Point(tabCompartments[i].getCoordinates());
      }
      return positions;
   }
   
   /**
    * @return nbLives
    */
   public int getNbLives(){
      return nbLives;
   }
   
   /**
    * @return orientation
    */
   public int getOrientation(){
      return orientation;
   }
   
   /**
    * sets the orientation to the value of the parameter
    * @param pValue
    */
   public void setOrientation(int pValue){
      this.orientation=pValue;
   }
   //----------------------------------INTERN CLASS-----------------------------
   public class Compartment{
   
      protected boolean isDestroyed;
      protected int posX;
      protected int posY;
      
      /**
       * @param pX abscissa of the compartment in the GridFleetModel
       * @param pY
       */
      public Compartment(int pX, int pY){
         this.isDestroyed=false;
         this.posX=pX;
         this.posY=pY;
      }
      
      /**
       * kills the compartment by setting isDestriyed attribut to false
       */
      protected void receiveDamage(){
         this.isDestroyed=false;
      }
      
      /**
       * @return the coordinates of the compartment int the GridFleetModel
       */
      protected Point getCoordinates(){
         return new Point(posX,posY);
      }
      
      /**
       * tells if the compartment has been hit or not
       * @return isDestroyed attribut
       */
      protected boolean isDestroyed(){
         return isDestroyed;
      }
      
   }
   //----------------------------------END OF INTERN CLASS----------------------
     

}
