/* 
 * Creation : Feb 11, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.observers;



/**
 * <h1>ObservableModel</h1>
 * <p>public interface ObservableModel</p>
 * 
 * <p>
 This class represents an ObservableModel object. 
 </p>
 *
 * @date    Feb 11, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 * @see     ObserverModel
 * 
 */
public interface ObservableModel{
    /**
     * Add an observer of the model
     * @param o Observer
     */
    public void addObserver(ObserverModel o);
    
    /**
     * Delete an observer
     * @param o Observer
     */
    public void deleteObserver(ObserverModel o);
    
    /**
     * Notify any observers
     * @param obj element to update in the observer object
     */
    public void notifyObservers(Object obj);
}
