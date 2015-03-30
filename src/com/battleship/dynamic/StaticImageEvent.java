/* 
 * Creation : 28 March 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.dynamic;

import java.awt.event.ActionEvent;
import com.battleship.views.tools.UiElement;


/**
 * <h1>StaticImageEvent</h1>
 * <p>
 * public abstract class StaticImageEvent<br/>
 * extends TimerManager<br/>
 * implements UiElement
 * </p>
 * <p>
 * This class create a animation from a list a image. Animation is played one 
 * time, then, event return at the first image and stop.<br/>
 * StaticImageEvent is abstract, it must work with specific image format as ImageIcon 
 * or Image. It will be initialized by the child class used
 * </p>
 * 
 * 
 * @date    Mar 30, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * 
 * @see TimerManager
 * @see UiElement
 */
public abstract class StaticImageEvent extends TimerManager implements UiElement{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    protected   int                 idFirstImg; //Id of the first event image
    protected   int                 currentImg; //Id of the current event image
    protected   int                 nbImg;
    protected   UiStaticEvent       contener;
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new Static Event
     * @param pDelay    Delay to apply at this event
     * @param pFirst    First event image
     * @param pCont     Where event is apply
     */
    public StaticImageEvent(int pDelay, int pFirst, UiStaticEvent pCont){
        super(pDelay);
        this.idFirstImg = pFirst;
        this.contener   = pCont;
        this.nbImg      = 1; //Will be actually set from reloadUI()
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    @Override
    public void actionPerformed(ActionEvent e){
        this.currentImg++;
        if(this.currentImg >= this.nbImg){
            this.currentImg = 0;
            this.stopTimer();
        }
        else{
            this.contener.updateUiImageEvent();
        }
    }
    
    @Override
    public void startTimer(){
        super.startTimer();
        this.contener.startUiImageEvent();
    }
    @Override
    public void stopTimer(){
        super.stopTimer();
        this.contener.stopUiImageEvent();
    }
    
}
