/* 
 * Creation : Mar 11, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.views.tools;

import com.battleship.exceptions.ExecError;
import javax.swing.JPanel;





/**
 * <h1>ContentPanel</h1>
 * <p>
 * public abstract class ContentPanel<br/>
 * extends JPanel
 * </p>
 * <p>ContentPanel are placed into a PagePanel.</p>
 *
 * @author Constantin MASSON
 * @date Mar 13, 2015
 */
public abstract class ContentPanel extends JPanel{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    protected   JPanel       parentPage;
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new Content panel include in a PagePanel. 
     * @param pParentPage PagePanel which contains this ContentPanel
     * @throws ExecError 
     */
    public ContentPanel(JPanel pParentPage) throws ExecError{
        if(pParentPage == null){
            throw new ExecError();
        }
        this.parentPage = pParentPage;
    }
    
    
    
    
    
    //**************************************************************************
    // Geters - Setters
    //**************************************************************************
    /**
     * Get current parentPage
     * @return PagePanel parent
     */
    public JPanel getParentPage(){
        return this.parentPage;
    }
}
