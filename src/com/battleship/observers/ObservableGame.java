/* 
 * Creation : Feb 11, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.observers;



/**
 * <h1>ObservableGame</h1>
 * <p>public interface ObservableGame</p>
 * 
 * <p>
 * This class represents an ObservableGame object. 
 * </p>
 *
 * @date    Feb 11, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 * @see     ObserverGame
 * 
 */
public interface ObservableGame{
    /**
     * Add an observer of the model
     * @param o Observer
     */
    public void registerObserver(ObserverGame o);
    
    /**
     * Delete an observer
     * @param o Observer
     */
    public void deleteObserver(ObserverGame o);
    
    /**
     * Notify any observers
     * @param obj element to update in the observer object
     */
    public void notifyObserver(Object obj);
}
