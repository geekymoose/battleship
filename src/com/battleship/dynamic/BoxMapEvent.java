/*
 * Class :      BoxMapEvent
 * Creation:    May 19, 2015
 * Author :     Constantin MASSON
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
 * <h1>BoxMapEvent</h1>
 * <p>
 * public class BoxMapEvent<br/>
 * extends DynamicEvent<br/>
 * implements UiElement
 * </p>
 * 
 * <p>BoxMapEvent is a event displayed in a BoxMap, for example a moving water
 *
 * @since   May 19, 2015
 * @author  Constantin MASSON
 */
public class BoxMapEvent extends DynamicEvent implements UiElement{
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    private int img;
    
    
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
     * @param pPos          explosion center of this event
     * @param pImg          name of image to display
     * 
     */
    public BoxMapEvent(int pDelay, int pFirst, int pEventType, UiEventApp pCont, Point pPos, int pImg){
        super(pDelay, pFirst, pEventType, pCont, pPos);
        this.img = pImg;
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
        list = ThemeManager.getTheme().getDynamicImg(this.img);
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
