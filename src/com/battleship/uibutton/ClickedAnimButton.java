/* 
 * Creation : 28 March 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.uibutton;

import com.battleship.dynamic.ImageIconEvent;
import java.awt.event.MouseEvent;
import com.battleship.dynamic.UiStaticEvent;
import java.awt.event.MouseAdapter;
import com.battleship.views.tools.Config;



/**
 * <h1>ClickedAnimButton</h1>
 * <p>
 * public class ClickedAnimButton<br/>
 * UiButtonDecorator<br/>
 * implements UiStaticEvent
 * </p>
 *
 * 
 * @date    Mar 29, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class ClickedAnimButton extends UiButtonDecorator implements UiStaticEvent{
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
    public ClickedAnimButton(UiButton pButton){
        super(pButton);
        int delay       = Config.getTimerConst("default-button-timer");
        int idClicked   = this.uibutton.getIdClicked();
        this.event      = new ImageIconEvent(delay, idClicked, this);
        
        this.uibutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
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
