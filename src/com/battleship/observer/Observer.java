
package com.battleship.observer;


/**
 * Pattern Observer
 * @author Anthony
 */
public interface Observer{
    /**
     * Update the view
     * @param obs
     */
    public void update(Observable obs);
}
