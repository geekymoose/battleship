/* 
 * Creation : 28 March 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.dynamic;


/**
 * <h1>UiEventApp</h1>
 * <p>public interface UiEventApp</p>
 * <p>Interface used with all </p>
 * 
 * <p>
 * Interface for element using an event (Like StaticImageEvent). It gives 
 * function used by event element
 * </p>
 * 
 * 
 * @since   Mar 26, 2015
 * @author  Constantin MASSON
 * 
 * @see StaticImageEvent
 */
public interface UiEventApp {
    
    /**
     * Called when event start
     * @param pEvent event just started
     */
    public void startUiEvent(EventApp pEvent);
    
    /**
     * Called when event is updated
     * @param pEvent event to update
     */
    public void updateUiEvent(EventApp pEvent);
    
    /**
     * Called when event stop
     * @param pEvent event just stopped
     */
    public void stopUiEvent(EventApp pEvent);
}