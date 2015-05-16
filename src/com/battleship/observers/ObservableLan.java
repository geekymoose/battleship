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
    public void addLanObserver(ObserverLan o);
    
    /**
     * Delete an observer
     * @param o Observer
     */
    public void deleteLanObserver(ObserverLan o);
    
    /**
     * Delete all observer
     */
    public void deleteAllLanObserver();
    
    /**
     * Notify any observers
     * @param obj element to update in the observer object
     */
    public void notifyLanObservers(Object obj);
}
