/*
 * File :       AircraftCarrier
 * Creation :   11 févr. 2015
 * Author :     Anthony CHAFFOT
 * Author :     Jessica Favin
 * Author :     Constantin MASSON
 * 
 * Projet computer science Java L2 - Battleship
 * 
 */

package com.battleship.models;





/**
 * <h1>AircraftCarrier</h1>
 * <p>
 * public class AircraftCarrier<br/>
 * </p>
 * 
 * <p>Extends Boat</p>
 * <p>Type of boat with 5 compartment (therefore 5 lives)</p>
 * 
 * @date    11 févr. 2015
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * @author  Contsantin MASSON
 */
public class AircraftCarrier extends Boat{
    
    protected AircraftCarrier(int pOrientation, FleetGridModel pGrid){
        super("AircraftCarrier", 5, 5, pOrientation, pGrid);
    }
}
