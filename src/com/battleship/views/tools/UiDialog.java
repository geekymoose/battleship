/* 
 * Creation : 28 March 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.tools;

import javax.swing.JOptionPane;





/**
 * <h1>UiDialog</h1>
 * <p>public class UiDialog</p>
 * <p>Manage display for JDialog</p>
 *
 * 
 * @date    Mar 28, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public abstract class UiDialog {
    //**************************************************************************
    // Functions warngin with return param
    //**************************************************************************
    /**
     * Display a warning dialog message and as for confirmation
     * @param pTitle    dialog title (Displayed at the window top)
     * @param pMsg      Message to display
     * @return          choice
     */
    public static int showConfirmWarning(String pTitle, String pMsg){
        JOptionPane opt = new JOptionPane();
        int choice = opt.showConfirmDialog(null, pMsg, pTitle,
                                            JOptionPane.YES_NO_CANCEL_OPTION, 
                                            JOptionPane.QUESTION_MESSAGE);
        return choice;
    }
    
    
    
    
    //**************************************************************************
    // Functions warngin without return param
    //**************************************************************************
    /**
     * Display a JDialog with warning message
     * @param pTitle    dialog title (Displayed at the window top)
     * @param pMsg      Message to display
     */
    public static void showWarning(String pTitle, String pMsg){
        JOptionPane opt = new JOptionPane();
        opt.showMessageDialog(null, pMsg, pTitle, JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * Display a JDialog with error message
     * @param pTitle    dialog title (Displayed at the window top)
     * @param pMsg      Message to display
     */
    public static void showError(String pTitle, String pMsg){
        JOptionPane opt = new JOptionPane();
        opt.showMessageDialog(null, pMsg, pTitle, JOptionPane.ERROR_MESSAGE);
    }
}
