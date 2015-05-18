/* 
 * Creation:    Apr 1, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.asset.Config;
import com.battleship.behaviors.Sprite;
import com.battleship.views.tools.UiElement;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;



/**
 * <h1>BoxMapView</h1>
 * <p>
 * public class BoxMapView<br/>
 * implements UiElement
 * </p>
 * <p>Display a BoxMap</p>
 *
 * @since   Apr 1, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public abstract class BoxMapView implements UiElement{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    protected   int         borderSize;
    protected   Color       borderColor;
    protected   Dimension   dimension;
    protected   Point       coordinate;
    
    protected   boolean     isHidden;
    protected   boolean     isHover;
    protected   boolean     isTargeted;
    protected   Sprite      sprite;
    
    //IMAGES
    //Visible images
    protected   Image       imgBoatAlive;
    protected   Image       imgBoatDead;
    protected   Image       imgWaterAlive;
    protected   Image       imgWaterDead;
    
    
    //Hidden image
    protected   Image       imgHiddenBoatDead;
    protected   Image       imgHiddenWaterAlive;
    protected   Image       imgHiddenWaterDead;
    
    //Targeted image
    protected   Image       imgTargeted;
    
    //Hover image
    protected   Image       imgHoverBoatAlive;
    protected   Image       imgHoverBoatDead;
    protected   Image       imgHoverWaterAlive;
    protected   Image       imgHoverWaterDead;
    
    protected   Image       imgHoverHiddenValid;
    protected   Image       imgHoverHiddenNotValid;
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new BoxMapView at position given (Grid coordinates)
     * @param x x coordinate
     * @param y y coordinate
     * @param pDimension BoxMap default dimension
     * @param pSprite Default sprite
     */
    protected BoxMapView(int x, int y, Dimension pDimension, Sprite pSprite){
        this.coordinate = new Point(x, y);
        this.dimension  = pDimension;
        this.isHidden   = false;
        this.isHover    = false;
        this.isTargeted = false;
        this.borderSize = Config.getDimValues_int("boxmap-border-size");
        this.borderColor= Color.BLACK;
        this.sprite     = pSprite;
        this.loadUI();
    }
    
    /**
     * Create a new BoxMapView at position given (Grid coordinates)
     * @param x x coordinate
     * @param y y coordinate
     * @param pDimension BoxMap default dimension
     * @param pSprite Default sprite
     * @param c Color for the borders 
     */
    protected BoxMapView(int x, int y, Dimension pDimension, Sprite pSprite, Color c){
        this.coordinate = new Point(x, y);
        this.dimension  = pDimension;
        this.isHidden   = false;
        this.isHover    = false;
        this.isTargeted = false;
        this.borderSize = Config.getDimValues_int("boxmap-border-size");
        this.borderColor= c;
        this.sprite     = pSprite;
        this.loadUI();
    }
    

    //**************************************************************************
    // Draw Functions
    //**************************************************************************
    /**
     * Draw the BoxMap
     * @param g2 Graphics2D
     */
    public void draw(Graphics2D g2){
        if(this.isHidden){
            this.drawHidden(g2);
        } else {
            this.drawDefault(g2);
        }
        if(this.isTargeted){
            this.drawTargeted(g2);
        } else if (this.isHover){
            this.drawHover(g2);
        }
    }
    
    /**
     * Draw default box
     * @param g2 Graphics 2D used
     */
    protected abstract void drawDefault(Graphics2D g2);
    
    /*
     * Draw the box with hidden status
     * @param g2
     */
    protected abstract void drawHidden(Graphics2D g2);
    
    /**
     * Draw box with decoration used when targeted
     * @param g2 Graphics 2D used
     */
    protected abstract void drawTargeted(Graphics2D g2);
    
    /**
     * Draw box with decoration used when cursor is hover
     * @param g2 Graphics 2D used
     */
    protected abstract void drawHover(Graphics2D g2);
    
    
    //**************************************************************************
    // UI Functions
    //**************************************************************************
    @Override
    public void loadUI(){
        this.reloadUI();
    }
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return current BoxMap dimension
     * @return Dimension
     */
    public Dimension getDimension(){
        return this.dimension;
    }
    
    /**
     * Return box position
     * @return Point coordinate of position
     */
    public Point getPosition(){
        return new Point(this.coordinate);
    }
    
    
    //**************************************************************************
    /**
     * Change current visible value. Value false will apply a hidden context to 
     * current BoxMapView
     * @param pValue boolean value
     */
    public void setHidden(boolean pValue){
        this.isHidden = pValue;
    }
    
    /**
     * Set targeted value
     * @param pValue  true if targeted or false if not
     */
    public void setTargeted(boolean pValue){
        this.isTargeted = pValue;
    }
    
    /**
     * Set hover value
     * @param pValue true if hover or false if not
     */
    public void setHover(boolean pValue){
        this.isHover = pValue;
    }
    
    /**
     * Set a new sprite for this box map. Do nothing if is null
     * @param pSprite new sprite
     */
    public void setSprite(Sprite pSprite){
        if(pSprite != null){
            this.sprite = pSprite;
        }
    }
}
