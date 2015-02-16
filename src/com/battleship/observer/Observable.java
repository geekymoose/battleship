
package com.battleship.observer;


/**
 * Pattern Observer
 * @author Anthony
 */
public interface Observable{
    /** 
     * Add an observer of the model
     * @param o Observer
     */
    public void registerObserver(Observer o);
    
    /**
     * Delete an observer
     * @param o Observer
     */
    public void deleteObserver(Observer o);
    
    /**
     * Notify any observers
     */
    public void notifyObserver();
    
}
