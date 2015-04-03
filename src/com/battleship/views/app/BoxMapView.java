/* 
 * Creation:    Apr 1, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.asset.Config;
import com.battleship.asset.ThemeManager;
import com.battleship.behaviors.Sprite;
import com.battleship.views.tools.UiElement;
import java.awt.BasicStroke;
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
 * @date    Apr 1, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public abstract class BoxMapView implements UiElement{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    protected   Sprite      sprite;
    protected   Image[]     img;
    protected   int         state;
    
    protected   int         borderSize;
    protected   Color       borderColor;
    protected   Dimension   dimension;
    protected   Point       coordinate;
    
    protected   boolean     isHidden;
    protected   boolean     isTargeted;
    
    
    
    
    
    

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
        this.isTargeted = false;
        this.borderSize = Config.getDimValues_int("boxmap-border-size");
        this.borderColor= Color.BLACK;
        this.sprite     = pSprite;
    }
    
    
    
    
    

    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Draw the BoxMap
     * @param g2 
     */
    public void draw(Graphics2D g2){
        g2.setStroke(new BasicStroke(this.borderSize));
        g2.setColor(this.borderColor);
        
        
        
        
        
        //g2.drawImage(this.img, 0, 0, this.img.getWidth(), this.img.getHeight(), null);
        if(this.isHidden){
            //Image image = this.img[this.sprite.getImgId()];
            //g2.drawImage(this.img, 0, 0, this.img.getWidth(), this.img.getHeight(), null);
        }
        
        
    }
    
    /*
     * Draw the box with hidden status
     * @param g2
     */
    protected abstract void drawHidden(Graphics2D g2);
    
    /**
     * Draw box with decoration used when targeted
     * @param g2 
     */
    protected abstract void drawTargeted(Graphics2D g2);

    
    @Override
    public void loadUI(){
        this.reloadUI();
    }


    @Override
    public void reloadUI(){
        for(int k=0; k<Sprite.NB_IMG; k++){
            this.img[k] = ThemeManager.getTheme().getImg(this.sprite.getImgId(k));
        }
    }
    
    
    
    
    
    

    //**************************************************************************
    // Getters - Setters
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
     * Return current BoxMap dimension
     * @return Dimension
     */
    public Dimension getDimension(){
        return this.dimension;
    }
}
