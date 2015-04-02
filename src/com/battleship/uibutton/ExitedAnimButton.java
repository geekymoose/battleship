/*
 * Class :      ExitedAnimButton
 * Creation:    Mar 31, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.uibutton;

import com.battleship.dynamic.ImageIconEvent;
import com.battleship.dynamic.UiStaticEvent;
import com.battleship.asset.Config;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;





/**
 * <h1>ExitedAnimButton</h1>
 * <p>public class ExitedAnimButton</p>
 *
 * @author Constantin MASSON
 * @date Mar 31, 2015
 */
public class ExitedAnimButton  extends UiButtonDecorator implements UiStaticEvent{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private ImageIconEvent  event;

    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new event decoration for an UiButton
     * @param pButton button to decorate
     */
    public ExitedAnimButton(UiButton pButton){
        super(pButton);
        int delay       = Config.getGameValues_int("default-button-timer");
        int img         = this.uibutton.getIdExited();
        this.event      = new ImageIconEvent(delay, img, this);
        
        this.uibutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e){
                event.startTimer();
            }
        });
    }

    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    @Override
    public void startUiImageEvent(){
        //Nothing
    }
    
    @Override
    public void updateUiImageEvent(){
        this.uibutton.setIcon(event.getImgIcon());
    }
    
    @Override
    public void stopUiImageEvent(){
        this.uibutton.resetButton();
    } 
}
