/* 
 * Creation : 29 March 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.uibutton;

import com.battleship.views.tools.UiElement;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;





/**
 * <h1>UiButtonDecorator</h1>
 * <p>
 * public abstract class UiButtonDecorator<br/>
 * extends JComponent<br/>
 * implements UiElement, MouseListener
 * </p>
 * 
 * <p>decorate an UiButton</p>
 *
 * @date    Mar 29, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * 
 * @see UiButton
 */
public abstract class UiButtonDecorator extends AbstractButton implements UiElement{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    protected   UiButton        uibutton; //Button to decorate
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new decorator
     * @param pButton 
     */
    protected UiButtonDecorator(UiButton pButton){
        this.uibutton = pButton;
        this.setLayout(new BorderLayout());
        this.add(pButton);
    }
    
    @Override
    public void reloadUI(){
        this.uibutton.reloadUI();
    }
    
    @Override
    public void addActionListener(ActionListener listener){
        this.uibutton.addActionListener(listener);
    }
}
