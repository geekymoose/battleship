/* 
 * Creation : 28 March 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.uibutton;

import com.battleship.views.tools.ThemeManager;
import com.battleship.views.tools.UiElement;
import java.awt.event.MouseEvent;
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
    /**
     * Create a default UiButton without any image and element
     */
    public UiButton(){
        this.setOpaque(false);
        this.initImageId(0, 0, 0, 0, 0, 0);
        this.addMouseListener(this);
    }
    
    /**
     * Create a default UiButton with unique image
     * @param pImg  image
     */
    public UiButton(int pImg){
        this.setOpaque(false);
        this.initImageId(pImg, pImg, pImg, pImg, pImg, pImg);
        this.addMouseListener(this);
    }
    
    /**
     * Create a UiButton with mouse hover image event. 
     * @param pDef      Default image
     * @param pPress    Pressed image
     * @param pHover    Mouse hover image
     */
    protected UiButton(int pDef, int pPress, int pHover){
        this.setOpaque(false);
        this.initImageId(pDef, pPress, pPress, pHover, pHover, pDef);
        this.addMouseListener(this);
    }
    
    /**
     * Create an UiButton. If image id given is 0, no image will be set
     * released, entered and exited
     * @param pDef      default image
     * @param pClick    clicked image
     * @param pPress    pressed image
     * @param pRe       released image
     * @param pEnt      entered image
     * @param pEx       exited image
     */
    protected UiButton(int pDef, int pClick, int pPress, int pRe, int pEnt, int pEx){
        this.setOpaque(false);
        this.initImageId(pDef, pClick, pPress, pRe, pEnt, pEx);
        this.addMouseListener(this);
    }
    
    /*
     * Initialize iamge from id given
     */
    private void initImageId(int pDef, int pClick, int pPress, int pRe, int pEnt, int pEx){
        this.id_default     = pDef;
        this.id_clicked     = pClick;
        this.id_pressed     = pPress;
        this.id_released    = pRe;
        this.id_entered     = pEnt;
        this.id_exited      = pEx;
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Reset button at first state. It is used at the end of an animation to 
     * reset default button state
     */
    public abstract void resetButton();
    
    
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
    
    
    
    
    
    //**************************************************************************
    // Functions mouse event
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