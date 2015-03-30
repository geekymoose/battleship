/* 
 * Creation : 29 March 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.uibutton;

import com.battleship.views.tools.UiElement;
import java.awt.BorderLayout;
import javax.swing.JComponent;





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
public abstract class UiButtonDecorator extends JComponent implements UiElement{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    protected   UiButton        uibutton; //Button to decorate
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    public UiButtonDecorator(UiButton pButton){
        this.uibutton = pButton;
        this.setLayout(new BorderLayout());
        this.add(pButton);
    }
    
    @Override
    public void reloadUI(){
        this.uibutton.reloadUI();
    }
}
