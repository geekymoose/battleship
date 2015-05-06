/* 
 * Creation : 28 March 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.dynamic;

import java.awt.event.ActionListener;
import javax.swing.Timer;


/**
 * <h1>TimerManager</h1>
 * <p>
 * public class TimerManager<br/>
 * implements ActionListener
 * </p>
 * 
 * <p>Create a Timer class with function to start and stop timer</p>
 *
 * @date    Mar 26, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * 
 * @see javax.swing.Timer
 */
public abstract class TimerManager implements ActionListener{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private final Timer timer;
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new TimerManager
     * @param pDelay delay to apply between to actionPerformed call
     */
    public TimerManager(int pDelay){
        if(pDelay<=0){
            pDelay = 1;
        }
        this.timer = new Timer(pDelay, this);
    }
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Start the timer. If already running, will stop timer before
     */
    public void startTimer(){
        this.timer.stop();
        this.timer.start();
    }
    
    /**
     * Stop the timer
     */
    public void stopTimer(){
        this.timer.stop();
    }
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return current delay
     * @return int current timer delay
     */
    public int getDelay(){
        return this.timer.getDelay();
    }
    
    /**
     * Set new delay, if less than 1, set to 1
     * @param pDelay new delay
     */
    public void setDelay(int pDelay){
        if(pDelay<=0){
            pDelay = 1;
        }
        this.timer.setDelay(pDelay);
    }
}
