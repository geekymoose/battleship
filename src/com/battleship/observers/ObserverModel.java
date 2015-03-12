/* 
 * Creation : Feb 11, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.observers;





/**
 * <h1>ObserverModel</h1>
 * <p>public interface ObserverModel</p>
 *
 * @date    Feb 25, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 * @see     ObservableModel
 */
public interface ObserverModel {
    
    /**
     * This method is called whenever the observed object is changed.
     * @param o     the observable object.
     * @param arg   an argument passed to the notifyObservers method.
     */
    void update(ObservableModel o, Object arg);
}
