/* 
 * Creation : 29 March 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.uibutton;

import com.battleship.views.tools.ThemeManager;
import java.awt.event.MouseEvent;
import javax.swing.AbstractButton;





/**
 * <h1>UiMessageButton</h1>
 * <p>
 * public class UiMessageButton<br/>
 * extends UiButton
 * </p>
 * <p>
 * A button with text and a background
 * </p>
 * 
 *
 * @date    Mar 29, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * 
 * @see UiButtonDecorator
 */
public class UiMessageButton extends UiButton{
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new Simple button with basic images for default, clicked, pressed 
     * released, entered and exited
     * @param pMsg      Message to display on this button
     * @param idDef     default image
     * @param idClick   clicked image
     * @param idPress   pressed image
     * @param idRelea   released image
     * @param idEnt     entered image
     * @param idEx      exited image
     */
    public UiMessageButton(String pMsg, int idDef, int idClick, int idPress, int idRelea, int idEnt, int idEx){
        super();
        this.msg            = pMsg;
        this.id_default     = idDef;
        this.id_clicked     = idClick;
        this.id_pressed     = idPress;
        this.id_released    = idRelea;
        this.id_entered     = idEnt;
        this.id_exited      = idEx;
        
        this.reloadUI(); //Load for the first time
        this.addMouseListener(this);
    }
    
    /**
     * Create a simple button with only a text
     * @param pMsg 
     */
    public UiMessageButton(String pMsg){
        this(pMsg, 0,0,0,0,0,0);
    }
    
    
    @Override
    public void reloadUI(){
        if(this.id_default != 0){
            this.img_default    = ThemeManager.getTheme().getImgIcon(this.id_default);
            this.setIcon(img_default);
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
        
        this.setText(this.msg);
        this.setHorizontalTextPosition(AbstractButton.CENTER);
        this.setVerticalTextPosition(AbstractButton.CENTER);
    }

    
    @Override
    public void resetButton(){
        this.setIcon(this.img_default);
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    @Override
    public void mouseClicked(MouseEvent e){
        this.setIcon(this.img_default);
    }
    
    @Override
    public void mousePressed(MouseEvent e){
        this.setIcon(this.img_pressed);
    }

    @Override
    public void mouseReleased(MouseEvent e){
        this.setIcon(this.img_released);
    }
    
    @Override
    public void mouseEntered(MouseEvent e){
        this.setIcon(this.img_entered);
    }
    
    @Override
    public void mouseExited(MouseEvent e){
        this.setIcon(this.img_exited);
    }
}
