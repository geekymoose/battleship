/*
 * Class :      ExplosionEvent
 * Creation:    May 5, 2015
 * Author :     Constantin MASSON
 * 
 * 
 * IMPORTANT NOTE
 * Event with image work directly with image name. For example, from constructor, 
 * pFirst is the name of the first image to display. CurrentImg the current image 
 * to display.
 * In order to display event, actionPerformed increment the currentImg value by 
 * one. It is important to understand that currentImg is also the name of this img!!! 
 * It is working only if img have a numeric! (Some update will maybe add name 
 * with ABC characters, but later)
 * 
 */

package com.battleship.dynamic;

import com.battleship.asset.ThemeManager;
import com.battleship.views.tools.UiElement;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;



/**
 * <h1>ExplosionEvent</h1>
 * <p>public class ExplosionEvent</p>
 *
 * @since   May 5, 2015
 * @author  Constantin MASSON
 */
public class ExplosionEvent extends DynamicEvent implements UiElement{
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
    public ExplosionEvent(int pDelay, int pFirst, int pEventType, UiEventApp pCont, Point pPosition){
        super(pDelay, pFirst, pEventType, pCont, pPosition);
        this.loadUI();
    }
    

    //**************************************************************************
    // Functions
    //**************************************************************************
    @Override
    public void loadUI(){
        this.reloadUI();
    }
    
    @Override
    public void reloadUI(){
        ArrayList<Image> list;
        list = ThemeManager.getTheme().getDynamicImg(105001);
        super.setListStates(list);
    }
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    @Override
    public Point getUpperLeftCorner(){
        //return new Point(this.posX-(this.width/2), this.posY-(this.height/2));
        int w   = ((Image)super.getListStates().get(this.currentStatePos)).getWidth(null);
        int h   = ((Image)super.getListStates().get(this.currentStatePos)).getHeight(null);
        return new Point(this.position.x-(w/2), this.position.y-(h/2));
    }
    
    /**
     * Return current event image
     * @return Image
     */
    public Image getCurrentImg(){
        return (Image)super.getCurrentState();
    }
    
    /**
     * Return dimension of the current image event
     * @return Dimension of current image event
     */
    public Dimension getImgDim(){
        Dimension dim = new Dimension();
        dim.width   = ((Image)super.getListStates().get(this.currentStatePos)).getWidth(null);
        dim.height  = ((Image)super.getListStates().get(this.currentStatePos)).getHeight(null);
        return dim;
    }
}
