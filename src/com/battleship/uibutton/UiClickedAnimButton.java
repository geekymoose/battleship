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
 * <h1>UiClickedAnimButton</h1>
 * <p>
 public class UiClickedAnimButton<br/>
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
public class UiClickedAnimButton extends UiButtonDecorator implements UiStaticEvent{
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
    public UiClickedAnimButton(UiButton pButton){
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
    
    /**
     * @deprecated not working yet
     * @param pIdFirstClicked First animation image
     * @param pButton 
     */
    public UiClickedAnimButton(int pIdFirstClicked, UiButton pButton){
        this(pButton);
        //this.uibutton.setImgClicked(pIdFirstClicked); -> to add in UiButton
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
