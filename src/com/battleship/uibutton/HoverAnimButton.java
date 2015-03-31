/* 
 * Creation : 28 March 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.uibutton;

import com.battleship.dynamic.ImageIconEvent;
import com.battleship.dynamic.UiStaticEvent;
import com.battleship.views.tools.Config;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;





/**
 * <h1>HoverAnimButton</h1>
 * <p>public class HoverAnimButton</p>
 *
 * @author Constantin MASSON
 * @date Mar 31, 2015
 */
public class HoverAnimButton extends UiButtonDecorator implements UiStaticEvent{
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
        this.event      = new ImageIconEvent(delay, idEntered, this);
        
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
