/* 
 * Creation : 29 March 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.uibutton;

import com.battleship.views.tools.ThemeManager;
import java.awt.event.MouseEvent;





/**
 * <h1>UiImgButton</h1>
 * <p>
 * public class UiImgButton<br/>
 * extends UiButton
 * </p>
 * <p>
 * A button with basic image for default, click, pressed, released, entered and 
 * exited. Could be decorated with a UiButtonDecorator. See this class 
 * for further informations.
 * </p>
 *
 * @date    Mar 29, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * 
 * @see UiButtonDecorator
 */
public class UiImgButton extends UiButton{
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new Simple button with basic images for default, clicked, pressed 
     * released, entered and exited
     * @param idDef     default image
     * @param idClick   clicked image
     * @param idPress   pressed image
     * @param idRelea   released image
     * @param idEnt     entered image
     * @param idEx      exited image
     */
    public UiImgButton(int idDef, int idClick, int idPress, int idRelea, int idEnt, int idEx){
        super();
        this.id_default     = idDef;
        this.id_clicked     = idClick;
        this.id_pressed     = idPress;
        this.id_released    = idRelea;
        this.id_entered     = idEnt;
        this.id_exited      = idEx;
        
        this.reloadUI(); //Load for the first time
        this.addMouseListener(this);
    }
    
    
    @Override
    public void reloadUI(){
        this.img_default    = ThemeManager.getTheme().getImgIcon(this.id_default);
        this.img_clicked    = ThemeManager.getTheme().getImgIcon(this.id_clicked);
        this.img_pressed    = ThemeManager.getTheme().getImgIcon(this.id_pressed);
        this.img_released   = ThemeManager.getTheme().getImgIcon(this.id_released);
        this.img_entered    = ThemeManager.getTheme().getImgIcon(this.id_entered);
        this.img_exited     = ThemeManager.getTheme().getImgIcon(this.id_exited);
        this.setIcon(img_default);
    }
    

    @Override
    public void resetButton(){
        this.setIcon(img_default);
    }
    
    
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    @Override
    public void mouseClicked(MouseEvent e){
        this.setIcon(this.img_clicked);
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
