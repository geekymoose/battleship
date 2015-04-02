/* 
 * Creation : 28 March 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.dynamic;

import com.battleship.asset.ThemeManager;
import java.util.ArrayList;
import javax.swing.ImageIcon;



/**
 * <h1>ImageIconEvent</h1>
 * <p>public class ImageIconEvent</p>
 * <p>
 * Display an Image Event for ImageIcon format
 * </p>
 * 
 * 
 * @date    Mar 29, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * 
 * @see StaticImage
 * @see TimerManager
 */
public class ImageIconEvent extends StaticImageEvent{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private     ArrayList<ImageIcon>    listImgEvent;
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create an ImageIconEvent.
     * @param pDelay    Delay to apply at this event
     * @param pIdFirstImg    First event image
     * @param pContener     Where event is apply
     */
    public ImageIconEvent(int pDelay, int pIdFirstImg, UiStaticEvent pContener){
        super(pDelay,pIdFirstImg, pContener);
        this.loadUI();
    }

    @Override
    public void loadUI(){
        this.reloadUI();
    }
    
    @Override
    public void reloadUI(){
        this.listImgEvent   = ThemeManager.getTheme().getDynamicImgIcon(idFirstImg);
        this.nbImg          = this.listImgEvent.size();
    }
    
    
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return current event image
     * @return ImageIcon to display at the moment
     */
    public ImageIcon getImgIcon(){
        return this.listImgEvent.get(this.currentImg);
    }
    
    /**
     * Return first event image
     * @return ImageIcon
     */
    public ImageIcon getFirstIcon(){
        return this.listImgEvent.get(0);
    }
}
