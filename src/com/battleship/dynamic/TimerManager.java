/* 
 * Creation : 28 March 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.dynamic;

import java.awt.event.ActionListener;
import javax.swing.Timer;


/**
 * <h1>TimerManager</h1>
 * <p>public class TimerManager</p>
 * <p>Create a Timer class with function to start and stop timer</p>
 *
 * @date    Mar 26, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public abstract class TimerManager implements ActionListener{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private     Timer       timer;
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    public TimerManager(int pDelay){
        this.timer              = new Timer(pDelay, this);
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
}
