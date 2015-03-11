/*
 * Class :      ApplicationView
 * Creation:    Mar 11, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.views.interfaces;





/**
 * <h1>ApplicationView</h1>
 * <p>public interface ApplicationView</p>
 *
 * @author Constantin MASSON
 * @date Mar 11, 2015
 */
public interface ApplicationView {
    /**
     * Display the choose Game Panel
     * This panel enable user to choose game mode as 2v2 / player vs AI and so on
     */
    public void displayChooseGamePanel();
    
    /**
     * Display the ConfigGamePanel
     * This panel enable to choose the data for a game as grid type
     */
    public void displayConfigGamePanel();
}
