/*
 * Creation:    May 15, 2015
 * 
 */

package com.battleship.observers;



/**
 * <h1>ObservableLan</h1>
 * <p>public interface ObservableLan</p>
 *
 * @date    May 15, 2015
 * @author  Constantin MASSON
 */
public interface ObservableLan {
    /**
     * Add an observer of the model
     * @param o Observer
     */
    public void addObserver(ObserverLan o);
    
    /**
     * Delete an observer
     * @param o Observer
     */
    public void deleteObserver(ObserverLan o);
    
    /**
     * Delete all observer
     */
    public void deleteAllObserver();
    
    /**
     * Notify any observers
     * @param obj element to update in the observer object
     */
    public void notifyObservers(Object obj);
}
