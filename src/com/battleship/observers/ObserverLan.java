/*
 * Creation:    May 15, 2015
 * 
 */

package com.battleship.observers;



/**
 * <h1>ObserverLan</h1>
 * <p>public interface ObserverLan</p>
 *
 * @date    May 15, 2015
 * @author  Constantin MASSON
 */
public interface ObserverLan {
    
    /**
     * This method is called whenever the observed object is changed.
     * @param o     the observable object.
     * @param arg   an argument passed to the notifyObservers method.
     */
    void update(ObservableLan o, Object arg);
}
