/* 
 * Creation : 29 March 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.uibutton;

import com.battleship.views.tools.UiElement;





/**
 * <h1>UiButtonDecorator</h1>
 * <p>
 * public abstract class UiButtonDecorator<br/>
 * extends JComponent<br/>
 * implements UiElement, MouseListener
 * </p>
 * 
 * <p>
 * Decorate an UiButton. To use the UiButton, getUiButton need to be called to 
 * return the button
 * </p>
 *
 * @since   Mar 29, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * 
 * @see UiButton
 */
public abstract class UiButtonDecorator implements UiElement{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    protected   UiButton        uibutton; //Button to decorate
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new decorator
     * @param pButton button to decorate
     */
    protected UiButtonDecorator(UiButton pButton){
        this.uibutton = pButton;
    }
    
    
    //**************************************************************************
    // Override methodes
    //**************************************************************************
    @Override
    public void loadUI(){
        this.uibutton.loadUI();
    }
    
    @Override
    public void reloadUI(){
        this.uibutton.reloadUI();
    }
    
    /**
     * Return the button decorated by this decorator
     * @return UiButton decorated
     */
    public UiButton getUiButton(){
        return this.uibutton;
    }
}
