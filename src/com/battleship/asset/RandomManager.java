/*
 * Class :      RandomManager
 * Creation:    Jan 20, 2015
 * Author :     Constantin MASSON
 * 
 */
package com.battleship.asset;



/**
 * <p>This class manage all random tools</p>
 *
 * @since   Apr 2, 2015
 * @author  Constantin MASSON
 */
public abstract class RandomManager {
    
    /**
     * Get random number between pMin and pMax included
     * @param pMin min value (included)
     * @param pMax max value (included)
     * @return random number between min and max (included) with int precision
     */
    public static int getRandomBetween(int pMin, int pMax){
        return (int)(Math.random() * (pMax+1-pMin))+ pMin;
    }
}
