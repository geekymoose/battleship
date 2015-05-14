/*
 * Class :      EventApp
 * Creation:    May 5, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.dynamic;

import java.awt.event.ActionEvent;
import java.util.ArrayList;





/**
 * <h1>EventApp</h1>
 * <p>
 * public class EventApp<br/>
 * extends TimerManager
 * </p>
 * 
 * <p>
 * EventApp create an event with specific data to process as an event (Could be, 
 * for example, Image or ImageIcon). An event can be see as a list of state, having 
 * a current state as an Image etc.
 * <br/>
 * EventApp is abstract, it must work with specific element to process 
 * as image or ImageIcon. It will be initialized by the child class used. 
 * Event using EventTemporary must implements UiEventApp
 * </p>
 * 
 * <ul>
 *  <li>LOOP_EVENT : event continue till user finish it manually</li>
 *  <li>STATIC_EVENT : event is played one time</li>
 * </ul>
 * 
 *
 * @since   May 5, 2015
 * @author  Constantin MASSON
 * 
 * @param <E> object managed by this event (As Image or ImageIcon for example)
 * 
 * @see TimerManager
 * @see UiElement
 */
public abstract class EventApp <E> extends TimerManager{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    public      static final int    LOOP_EVENT          = 42;
    public      static final int    STATIC_EVENT        = 1024;
    
    protected   int                 firstStatesPos;
    protected   int                 currentStatePos;
    protected   UiEventApp          container;
    
    private     int                 eventType;
    private     ArrayList<E>        listStates; //List of states (private!)
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create new EventApp with specific delay, first state and container. 
     * 
     * Create a new EventApp, each break time between two state is defined 
     * by pDelay value. Second argument is the position of the first state in 
     * the list of state.(Usually equals to 0). If number higher than number 
     * of states given, even will stop itself while he start (If static event), 
     * or restart at the first state (If loop event) 
     * The third is the event type, could be a loop (Played while not stopped) 
     * or a static event (play one time)
     * The fourth argument is where event will be apply, if null given, event is 
     * not placed in a specific content
     * 
     * @param pDelay        Delay to apply at this event
     * @param pFirst        position of the first state (Usually 0)
     * @param pEventType    set event type (LOOP_EVENT or STATIC_EVENT)
     * @param pCont         Where event is apply
     */
    public EventApp(int pDelay, int pFirst, int pEventType, UiEventApp pCont){
        super(pDelay);
        this.setEventType(pEventType);
        this.firstStatesPos     = pFirst;
        this.currentStatePos    = this.firstStatesPos;
        this.container          = pCont;
        this.listStates         = new ArrayList();
    }
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    @Override
    public void startTimer(){
        super.startTimer();
        this.container.startUiEvent(this);
    }
    @Override
    public void stopTimer(){
        super.stopTimer();
        this.container.stopUiEvent(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        this.currentStatePos++;
        if(this.currentStatePos >= this.getNbStates()){
            this.currentStatePos = this.firstStatesPos;
            //Stop if is static event
            if(this.eventType==STATIC_EVENT){
                this.stopTimer();
            }
        }
        else{
            this.container.updateUiEvent(this);
        }
    }
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return number of states for this animation
     * @return int number of states
     */
    public int getNbStates(){
        return this.listStates.size();
    }
    
    /**
     * Return current kind of event. LOOP_EVENT or STATIC_EVENT
     * @return int kind of event
     */
    public int getEventType(){
        return this.eventType;
    }
    
    /**
     * Return list of states
     * @return ArrayList of E state
     */
    public ArrayList<E> getListStates(){
        return this.listStates;
    }
    
    /**
     * Return current state of event
     * @return E current state
     */
    public E getCurrentState(){
        return this.listStates.get(this.currentStatePos);
    }
    
    /**
     * Return first state of this event
     * @return E first state
     */
    public E getFirstState(){
        return this.listStates.get(this.firstStatesPos);
    }
    
    
    //**************************************************************************
    /**
     * Set current kind of event. Could be LOOP_EVENT or STATIC_EVENT. if not 
     * valid, set to STATIC_EVENT
     * @param pEventType type of event
     */
    public void setEventType(int pEventType){
        boolean validType       = pEventType==LOOP_EVENT || pEventType==STATIC_EVENT;
        this.eventType          = validType ? pEventType : STATIC_EVENT;
    }
    
    /**
     * Set the list of state with new list. Actualize current number of states. 
     * if null given, current list of state is reset
     * @param pList ArrayList of states
     */
    public void setListStates(ArrayList<E> pList){
        this.listStates = (pList != null)? pList : new ArrayList();
    }
}
