/* 
 * Creation : 29 March 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.uibutton;

import com.battleship.views.tools.ThemeManager;
import javax.swing.AbstractButton;





/**
 * <h1>MsgButton</h1>
 * <p>
 * public class MsgButton<br/>
 * extends UiButton
 * </p>
 * <p>A button with text and a background</p>
 * 
 *
 * @date    Mar 29, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * 
 * @see UiButtonDecorator
 */
public class MsgButton extends UiButton{
    //**************************************************************************
    // Variables - Constants
    //**************************************************************************
    private   String      msg;
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create an MsgButton with a unique image
     * @param pDef image to display
     */
    public MsgButton(String pMsg, int pDef){
        super(pDef);
        this.msg            = pMsg;
        this.reloadUI();
    }
    
    
    /**
     * Create a new UiMessageButton
     * @param pMsg      Message to set
     * @param pDef      Default image
     * @param pPress    Pressed image
     * @param pHover    Mouse hover image
     */
    public MsgButton(String pMsg, int pDef, int pPress, int pHover){
        super(pDef, pPress, pHover);
        this.msg            = pMsg;
        this.reloadUI();
    }
    
    
    /**
     * Create a new Simple button with basic images for default, clicked, pressed 
     * released, entered and exited
     * @param pMsg      Message to display on this button
     * @param pDef      default image
     * @param pClick    clicked image
     * @param pPress    pressed image
     * @param pRe       released image
     * @param pEnt      entered image
     * @param pEx       exited image
     */
    public MsgButton(String pMsg, int pDef, int pClick, int pPress, int pRe, int pEnt, int pEx){
        super(pDef, pClick, pPress, pRe, pEnt, pEx);
        this.msg = pMsg;
        this.reloadUI();
    }
    
    /**
     * Create a simple button with only a text
     * @param pMsg 
     */
    public MsgButton(String pMsg){
        super();
        this.msg = pMsg;
        this.reloadUI();
    }
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    @Override
    public void reloadUI(){
        if(this.id_default != 0){
            this.img_default    = ThemeManager.getTheme().getImgIcon(this.id_default);
        }
        if(this.id_clicked != 0){
            this.img_clicked    = ThemeManager.getTheme().getImgIcon(this.id_clicked);
        }
        if(this.id_pressed != 0){
            this.img_pressed    = ThemeManager.getTheme().getImgIcon(this.id_pressed);
        }
        if(this.id_released != 0){
            this.img_released   = ThemeManager.getTheme().getImgIcon(this.id_released);
        }
        if(this.id_entered != 0){
            this.img_entered    = ThemeManager.getTheme().getImgIcon(this.id_entered);
        }
        if(this.id_exited != 0){
            this.img_exited     = ThemeManager.getTheme().getImgIcon(this.id_exited);
        }
        
        this.setIcon(img_default); //If id_def = 0, img_default = null -> do nothing
        this.setText(this.msg);
        this.setHorizontalTextPosition(AbstractButton.CENTER);
        this.setVerticalTextPosition(AbstractButton.CENTER);
    }

    
    @Override
    public void resetButton(){
        this.setIcon(this.img_default);
    }
}
