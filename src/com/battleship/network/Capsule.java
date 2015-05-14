/* 
 * Creation : 1 mai 2015
 */
package com.battleship.network;

import java.io.Serializable;



/**
 * @since   1 mai 2015
 * @author  Anthony CHAFFOT
 */
public class Capsule implements Serializable{
    private final Request rqt;
    private final Object obj;
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public Capsule(Request p_rqt, Object p_obj){
        this.rqt = p_rqt;
        this.obj = p_obj;
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************

    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************

    /**
     * getRequest()
     * @return Request
     */
        public Request getRequest(){
        return rqt;
    }

    /**
     * getObject()
     * @return Object
     */
    public Object getObject(){
        return obj;
    }
}
