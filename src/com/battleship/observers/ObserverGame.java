/* 
 * Creation : Feb 11, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.observers;





/**
 * <h1>ObserverGame</h1>
 * <p>public interface ObserverGame</p>
 *
 * @date    Feb 25, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 * @see     ObservableGame
 */
public interface ObserverGame {
    
    /**
     * This method is called whenever the observed object is changed.
     * @param o     the observable object.
     * @param arg   an argument passed to the notifyObservers method.
     */
    void update(ObservableGame o, Object arg);
}
