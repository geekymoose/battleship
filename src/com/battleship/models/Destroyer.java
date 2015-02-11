/*
 * File :       Destroyer
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
 * <h1>Destroyer</h1>
 * <p>
 * public class Destroyer<br/>
 * </p>
 * 
 * <p>Extends Boat</p>
 * <p>Type of boat with 2 compartment (therefore 2 lives)</p>
 * 
 * @date    11 févr. 2015
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * @author  Contsantin MASSON
 */
public class Destroyer extends Boat {
    
    protected Destroyer(int pOrientation, FleetGridModel pGrid){
        super("Destroyer", 2, 2, pOrientation, pGrid);
    }
}
