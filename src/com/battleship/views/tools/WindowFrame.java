/* 
 * Creation : Mar 11, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.views.tools;





/**
 * <h1>WindowFrame</h1>
 * <p>public interface WindowFrame</p>
 *
 * @date    Mar 11, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public interface WindowFrame {
    
    /**
     * <p>
     * Rooting process. It enable to browse between application page (displayed 
     * on the WindowFrame). The page to display could have parameters sent by 
     * arguments Object param. (The new page should know 
     * its type in order to cast)<br/>
     * According to path value (Given in argument), Application 
     * will change its mainComponent (Means the page displayed will be changed with 
     * the one bound with path constants value
     * </p>
     * 
     * @param path  constant for the page to display
     * @param param parameters to pass, null if no parameters
     * @see Root interface with path constants values
     */
    public void rooting(int path, Object param);
    
    /**
     * Return current WindowFrame theme
     * @return theme used
     */
    public Theme getTheme();
}
