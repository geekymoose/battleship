/*
 * Class :      DynamicEvent
 * Creation:    May 5, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.dynamic;

import java.awt.Point;





/**
 * <h1>DynamicEvent</h1>
 * <p>
 * public class DynamicEvent<br/>
 * extends EventApp
 * </p>
 * 
 * <p>DynamicEvent is an event with playing in a specific position.</p>
 *
 * @since   May 5, 2015
 * @author  Constantin MASSON
 * 
 * @see EventApp
 */
public abstract class DynamicEvent extends EventApp{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    protected   Point   position;
    
    
    
    
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new Dynamic event at specific position. 
     * Position is a Point coordinate of the center of the event
     * @param pDelay        Delay to apply at this event
     * @param pFirst        position of the first state (Usually 0)
     * @param pEventType    set event type (LOOP_EVENT or STATIC_EVENT)
     * @param pCont         Where event is apply
     * @param pPosition     explosion center of this event
     */
    public DynamicEvent(int pDelay, int pFirst, int pEventType, UiEventApp pCont, Point pPosition){
        super(pDelay, pFirst, pEventType, pCont);
        this.position   = pPosition;
    }
    
    

    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return current event center position
     * @return Point position
     */
    public Point getPosition(){
        return this.position;
    }
    
    /**
     * Get the Upper left corner position Point of the character
     * @return Point
     */
    public abstract Point getUpperLeftCorner();
}
