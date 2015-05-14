/* 
 * Creation : 28 March 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.views.tools;





/**
 * <h1>UiElement</h1>
 * <p>public interface UiElement</p>
 * <p>
 * An User Interface element. These elements are displayed with image and data 
 * which could be changed (By a theme for example).
 * </p>
 * 
 * 
 * @since   Mar 29, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public interface UiElement {
    
    /**
     * Load all UI element (Image from theme etc)
     */
    public void loadUI();
    
    /**
     * Reload UiElement
     */
    public void reloadUI();
}
