/* 
 * Creation : 28 March 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.uibutton;

import com.battleship.dynamic.ImageIconEvent;
import com.battleship.dynamic.UiEventApp;
import com.battleship.asset.Config;
import com.battleship.dynamic.EventApp;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;





/**
 * <h1>HoverAnimButton</h1>
 * <p>public class HoverAnimButton</p>
 *
 * @author Constantin MASSON
 * @date Mar 31, 2015
 */
public class HoverAnimButton extends UiButtonDecorator implements UiEventApp{
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
    public HoverAnimButton(UiButton pButton){
        super(pButton);
        int delay       = Config.getGameValues_int("default-button-timer");
        int idEntered   = this.uibutton.getIdEntered();
        this.event      = new ImageIconEvent(delay, idEntered, EventApp.STATIC_EVENT, this);
        
        this.uibutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
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
