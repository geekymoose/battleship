/* 
 * Creation:    Apr 1, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.views.app;

import com.battleship.behaviors.Sprite;
import com.battleship.constants.GameConstants;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;




/**
 * <h1>BoxMapViewSquare</h1>
 * <p>
 * public class BoxMapViewSquare<br/>
 * extends BoxMapView
 * </p>
 * <p>Display a BoxMap for Square grid</p>
 *
 * @date    Apr 1, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * 
 * @see BoxMapView
 */
public class BoxMapViewSquare extends BoxMapView{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private Rectangle rect;
    
    
    
    
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * 
     * @param coordinateX   coordinate in the grid
     * @param coordinateY   coordinate in the grid
     * @param pDim          BoxDefault dimension
     * @param pSprite       Default sprite
     */
    public BoxMapViewSquare(int coordinateX, int coordinateY, Dimension pDim, Sprite pSprite){
        super(coordinateX, coordinateY, pDim, pSprite);
        this.rect = this.createRectangle(this.coordinate);
    }
    
    /**
     * 
     * @param coordinateX   coordinate in the grid
     * @param coordinateY   coordinate in the grid
     * @param pDim          BoxDefault dimension
     * @param pSprite       Default sprite
     * @param c             Color of the borders
     */
    public BoxMapViewSquare(int coordinateX, int coordinateY, Dimension pDim, Sprite pSprite, Color c){
        super(coordinateX, coordinateY, pDim, pSprite, c);
        this.rect = this.createRectangle(this.coordinate);
    }
    
    /*
     * @param pCoordinate box coordinate in the grid
     */
    private Rectangle createRectangle(Point pCoordinate){
        Point p     = new Point();
        p.x         = this.dimension.width * this.coordinate.x;
        p.y         = this.dimension.height * this.coordinate.y;
        return new Rectangle(p, this.dimension);
    }
    
    
    
    
    

    //**************************************************************************
    // Functions
    //**************************************************************************
    
    
    
    
    
    //**************************************************************************
    // Draw fucntions
    //**************************************************************************
    @Override
    protected void drawDefault(Graphics2D g2){
        g2.setStroke(new BasicStroke(this.borderSize));
        
        switch(this.sprite.getId()){
            case GameConstants.WATER:
                g2.setColor(Color.CYAN);
                g2.fillRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
                break;
            case GameConstants.AIRCRAFT_CARRIER:
                g2.setColor(Color.YELLOW);
                g2.fillRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
                break;
            case GameConstants.BATTLESHIP:
                g2.setColor(Color.GRAY);
                g2.fillRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
                break;
            case GameConstants.CRUISER:
                g2.setColor(Color.BLACK);
                g2.fillRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
                break;
            case GameConstants.SUBMARINE:
                g2.setColor(Color.BLUE);
                g2.fillRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
                break;
            case GameConstants.DESTROYER:
                g2.setColor(Color.MAGENTA);
                g2.fillRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
                break;
        }
        
        g2.setColor(this.borderColor);
        g2.drawRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
    }
    
    @Override
    protected void drawHidden(Graphics2D g2){
        g2.setColor(Color.GRAY);
        g2.fillRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
        g2.setColor(this.borderColor);
        g2.drawRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
    }

    @Override
    protected void drawTargeted(Graphics2D g2){
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
    }
}
