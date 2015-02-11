/*
 * File :       Battleship
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
 * <h1>Battleship</h1>
 * <p>
 * public class Battleship<br/>
 * </p>
 * 
 * <p>Extends Boat</p>
 * <p>Type of boat with 4 compartment (therefore 4 lives)</p>
 * 
 * @date    11 févr. 2015
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * @author  Contsantin MASSON
 */
public class Battleship extends Boat{
    
    protected Battleship(int pOrientation, FleetGridModel pGrid){
        super("Battleship", 4, 4, pOrientation, pGrid);
    }
}
