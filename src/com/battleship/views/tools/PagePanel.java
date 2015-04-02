/* 
 * Creation : Mar 11, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.views.tools;

import com.battleship.exceptions.ExecError;
import javax.swing.JPanel;





/**
 * <h1>PagePanel</h1>
 * <p>
 * public abstract class PagePanel<br/>
 * extends JPanel
 * </p>
 * 
 * <p>
 * Represent a displayable panel linked with WindowFrame.
 * An PagePanel must be placed into an WindowFrame, contentPanel can be placed 
 * in a PagePanel. Only one PagePanel is displayed on the WindowFrame at the 
 * same time.
 * </p>
 *
 * @date    Mar 13, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public abstract class PagePanel extends JPanel implements UiElement{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    protected   WindowFrame     frame;
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new PagePanel
     * @param pFrame Frame which contains this PagePanel
     * @throws ExecError Thrown if WindowFrame is null
     */
    public PagePanel(WindowFrame pFrame) throws ExecError{
        if (pFrame==null){
            throw new ExecError();
        }
        this.frame = pFrame;
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Initialize the Panel. It is useful when the view juste created has to 
     * be synchronized with its model. But it is impossible during the 
     * model / view / controller creation. This function init hast to be called 
     * juste after this process 
     */
    public abstract void initPage();
    
    /**
     * Go at next Page. if no next page, do nothing
     */
    protected abstract void goNextPage();
    
    /**
     * Go at the previous page, if no previous page, do nothing
     */
    protected abstract void goPreviousPage();


    @Override
    public void loadUI(){
    
    }

    @Override
    public void reloadUI(){
        
    }
    
    
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Get current parent frame where Page Panel is placed in
     * @return Window Frame 
     */
    public WindowFrame getFrame(){
        return this.frame;
    }
}
