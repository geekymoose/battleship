/* 
 * Creation : 28 March 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.dynamic;


/**
 * <h1>UiStaticEvent</h1>
 * <p>public interface UiStaticEvent</p>
 * <p>Interface for element using StaticImageEvent. This interface give functions 
 * used by StaticImageEvent and enable class which implement UiStaticEvent 
 * to be used with StaticImageEvent</p>
 * 
 * 
 * @date    Mar 26, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * 
 * @see StaticImageEvent
 */
public interface UiStaticEvent {
    
    /**
     * Called when event start
     */
    public void startUiImageEvent();
    
    /**
     * Called when event is updated
     */
    public void updateUiImageEvent();
    
    /**
     * Called when event stop
     */
    public void stopUiImageEvent();
}