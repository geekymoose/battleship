/* 
 * Creation : Mar 11, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.views.tools;

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
public abstract class ContentPanel extends JPanel implements UiElement{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    protected   JPanel       parentPage;
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new Content panel include in a PagePanel. (Or in nothing if 
     * null given)
     * @param pParentPage PagePanel which contains this ContentPanel
     */
    public ContentPanel(JPanel pParentPage){
        this.parentPage = pParentPage;
    }
    
    public ContentPanel(){
        this.parentPage = null;
    }
    
    
    
    
    
    //**************************************************************************
    //  Functions
    //**************************************************************************
    @Override
    public void loadUI(){
    
    }

    @Override
    public void reloadUI(){
        
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
    
    /**
     * Set content parent
     * @param pParent 
     */
    public void setParent(JPanel pParent){
        this.parentPage = pParent;
    }
}
