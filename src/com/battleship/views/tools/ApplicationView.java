/* 
 * Creation : Mar 11, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.views.tools;





/**
 * <h1>ApplicationView</h1>
 * <p>public interface ApplicationView</p>
 *
 * @date    Mar 11, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public interface ApplicationView {
    
    /**
     * Rooting process. According to path value (Given in argument), Application 
     * will change its mainComponent (Means the page displayed will be changed with 
     * the one bound with path constants value
     * @param path constant for the page to display
     * @see Root interface with path constants values
     */
    public void rooting(int path);
}
