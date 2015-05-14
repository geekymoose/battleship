/* 
 * Creation : 31 mars 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.uibutton;

import java.awt.Dimension;



/**
 * @since   31 mars 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class ZozoDecorator  extends UiButtonDecorator{
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new event decoration for an UiButton
     * @param pButton button to decorate
     */
    public ZozoDecorator(UiButton pButton){
        super(pButton);
        this.uibutton.setBorderPainted(false);
        this.uibutton.setContentAreaFilled(false);
        this.uibutton.setDefaultCapable(false);
        this.uibutton.setFocusPainted(false);
        
        int width   = this.uibutton.getIcon().getIconWidth();
        int height  = this.uibutton.getIcon().getIconHeight();
        this.uibutton.setPreferredSize(new Dimension(width,height));
    }
}
