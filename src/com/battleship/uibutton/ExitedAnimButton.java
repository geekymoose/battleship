/*
 * Class :      ExitedAnimButton
 * Creation:    Mar 31, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.uibutton;

import com.battleship.dynamic.ImageIconEvent;
import com.battleship.dynamic.UiEventApp;
import com.battleship.asset.Config;
import com.battleship.dynamic.EventApp;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;





/**
 * <h1>ExitedAnimButton</h1>
 * <p>public class ExitedAnimButton</p>
 *
 * @since   Mar 31, 2015
 * @author  Constantin MASSON
 */
public class ExitedAnimButton  extends UiButtonDecorator implements UiEventApp{
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
        this.event      = new ImageIconEvent(delay, img, EventApp.STATIC_EVENT, this);
        
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
    public void startUiEvent(EventApp pEvent){
        //Nothing
    }
    
    @Override
    public void updateUiEvent(EventApp pEvent){
        this.uibutton.setIcon(event.getImgIcon());
    }
    
    @Override
    public void stopUiEvent(EventApp pEvent){
        this.uibutton.resetButton();
    } 
}
