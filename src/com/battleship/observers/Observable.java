/* 
 * Creation : Feb 11, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.observers;

import java.util.Observer;



/**
 * <h1>Observable</h1>
 * <p>public interface Observable</p>
 * 
 * <p>
 * This class represents an Observable object. 
 * </p>
 *
 * @date    Feb 11, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 * @see     java.lang.Observer
 * 
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
     * @param obj element to update in the observer object
     */
    public void notifyObserver(Object obj);
}
