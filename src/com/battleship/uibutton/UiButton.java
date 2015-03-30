/* 
 * Creation : 28 March 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.uibutton;

import java.awt.Color;
import com.battleship.views.tools.UiElement;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;






/**
 * <h1>UiButton</h1>
 * <p>
 * public abstract class UiButton<br/>
 * extends JButton<br/>
 * implements MouseListener, UiElement
 * </p>
 * 
 * <p>User Interface button</p>
 * 
 * 
 * @date    Mar 26, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */

public abstract class UiButton extends JButton implements MouseListener, UiElement{
    //**************************************************************************
    // Variables - Constants
    //**************************************************************************
    protected   String      msg;
    
    protected   int         id_default;
    protected   int         id_clicked;
    protected   int         id_pressed;
    protected   int         id_released;
    protected   int         id_entered;
    protected   int         id_exited;
    
    protected   ImageIcon   img_default;
    protected   ImageIcon   img_clicked;
    protected   ImageIcon   img_pressed;
    protected   ImageIcon   img_released;
    protected   ImageIcon   img_entered;
    protected   ImageIcon   img_exited;
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /*
     * UiButton
     */
    protected UiButton(){
        this.setOpaque(false);
        this.setBackground(Color.red);
    }
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Reset button at first state. It is used at the end of an animation to 
     * reset default button state
     */
    public abstract void resetButton();
    
    

    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    public int getIdDefault()   { return this.id_default;   }
    public int getIdClicked()   { return this.id_clicked;   }
    public int getIdPressed()   { return this.id_pressed;   }
    public int getIdReleased()  { return this.id_released;  }
    public int getIdEntered()   { return this.id_entered;   }
    public int getIdExited()    { return this.id_exited;    }
}