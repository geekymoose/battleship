/* 
 * Creation:    Apr 1, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.views.app;

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
     */
    public BoxMapViewSquare(int coordinateX, int coordinateY, Dimension pDim){
        super(coordinateX, coordinateY, pDim);
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
    public void draw(Graphics2D g2){
        super.draw(g2);
        if(this.isVisible){
            g2.drawRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
        }else{
            this.drawHidden(g2);
        }
        
        if(this.isTargeted){
        }
    }
    
    @Override
    protected void drawHidden(Graphics2D g2){
        g2.setColor(Color.GRAY);
        g2.fillRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
    }

    @Override
    protected void drawTargeted(Graphics2D g2){
        g2.setColor(Color.CYAN);
        g2.fillRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
    }
}
