/*
 * File :       Submarine
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
 * <h1>Submarine</h1>
 * <p>
 * public class Submarine<br/>
 * </p>
 * 
 * <p>Extends Boat</p>
 * <p>Type of boat with 3 compartment (therefore 3 lives)</p>
 * 
 * @date    11 févr. 2015
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * @author  Contsantin MASSON
 */
public class Submarine extends Boat {
    
    protected Submarine(int pOrientation, FleetGridModel pGrid){
        super("Submarine", 3, 3, pOrientation, pGrid);
    }
}
