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
import java.awt.Polygon;




/**
 * <h1>BoxMapViewHexagon</h1>
 * <p>
 * public class BoxMapViewHexagon<br/>
 * extends BoxMapView
 * </p>
 * <p>Display a BoxMap for Hexagon grid</p>
 *
 * @date    Apr 1, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * 
 * @see BoxMapView
 */
public class BoxMapViewHexagon extends BoxMapView{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private     final int   BORDERS_FROM_TOP_AND_LEFT = 10;
    private     int         t, s, r, h; //Constants for dimension hexa
    private     Polygon     polygon;
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a BoxMapHexagon
     * @param coordinateX   coordinate in the grid
     * @param coordinateY   coordinate in the grid
     * @param pDim          BoxDefault dimension
     * @param pSprite       Default sprite
     */
    public BoxMapViewHexagon(int coordinateX, int coordinateY, Dimension pDim, Sprite pSprite){
        super(coordinateX, coordinateY, pDim, pSprite);
        this.initValues(this.dimension.width);
        this.polygon = this.createHexagon(coordinateX, coordinateY);
    }
    
    /**
     * Create a BoxMapHexagon
     * @param coordinateX   coordinate in the grid
     * @param coordinateY   coordinate in the grid
     * @param pDim          BoxDefault dimension
     * @param pSprite       Default sprite
     * @param c             Color of the borders
     */
    public BoxMapViewHexagon(int coordinateX, int coordinateY, Dimension pDim, Sprite pSprite, Color c){
        super(coordinateX, coordinateY, pDim, pSprite, c);
        this.initValues(this.dimension.width);
        this.polygon = this.createHexagon(coordinateX, coordinateY);
    }
    
    
    /*
     * Set all the sizes
     * Set size for elements
     * @param height
     */
    private void initValues(int height) {
        /*
         * h = basic dimension: height (distance between two adj centresr aka size)
         * r = radius of inscribed circle
         * s = (h/2)/cos(30)= (h/2) / (sqrt(3)/2) = h / sqrt(3)
         * t = (h/2) tan30 = (h/2) 1/sqrt(3) = h / (2 sqrt(3)) = r / sqrt(3)
         */
        this.h  = height;
        this.r  = h / 2;
        this.s  = (int) (h / 1.73205);
        this.t  = (int) (r / 1.73205);
    }
    
    
    /*
     * Create new Hexagon. Created from BoxMap coordinate. Actual Box center 
     * is calculated and polygon is created for this box
     *
     * @param coordinateX x coordinate in the grid
     * @param coordinateY y coordinate in the grid
     * @return a Polygon with all the coordinates of the hexagon
     */
    private Polygon createHexagon(int coordinateX, int coordinateY) {
        int x0 = coordinateX * (s + t);
        int y0 = coordinateY * h + (coordinateX % 2) * h / 2;
        
        int x = x0 + BORDERS_FROM_TOP_AND_LEFT;
        int y = y0 + BORDERS_FROM_TOP_AND_LEFT;
        if (s == 0 || h == 0) {
            System.out.println("ERROR: size of hex has not been set");
            return new Polygon();
        }

        int[] cx, cy;
        //this is for the whole hexagon to be below and to the right of this point
        cx = new int[]{x + t, x + s + t, x + s + t + t, x + s + t, x + t, x};
        cy = new int[]{y, y, y + r, y + r + r, y + r + r, y + r};
        return new Polygon(cx, cy, 6);
    }
    
    
    
    
    
    //**************************************************************************
    // Draw fucntions
    //**************************************************************************
    @Override
    protected void drawDefault(Graphics2D g2){
        g2.setStroke(new BasicStroke(this.borderSize));
        
        switch(this.sprite.getId()){
            case GameConstants.WATER:
                g2.setColor(Color.CYAN);
                g2.fillPolygon(this.polygon);
                break;
            case GameConstants.AIRCRAFT_CARRIER:
                g2.setColor(Color.YELLOW);
                g2.fillPolygon(this.polygon);
                break;
            case GameConstants.BATTLESHIP:
                g2.setColor(Color.GRAY);
                g2.fillPolygon(this.polygon);
                break;
            case GameConstants.CRUISER:
                g2.setColor(Color.BLACK);
                g2.fillPolygon(this.polygon);
                break;
            case GameConstants.SUBMARINE:
                g2.setColor(Color.BLUE);
                g2.fillPolygon(this.polygon);
                break;
            case GameConstants.DESTROYER:
                g2.setColor(Color.MAGENTA);
                g2.fillPolygon(this.polygon);
                break;
        }
        
        g2.setColor(this.borderColor);
        g2.drawPolygon(this.polygon);
    }
    
    
    @Override
    protected void drawHidden(Graphics2D g2){
        g2.setColor(Color.GRAY);
        g2.fillPolygon(this.polygon);
        g2.setColor(this.borderColor);
        g2.drawPolygon(this.polygon);
    }

    @Override
    protected void drawTargeted(Graphics2D g2){
        g2.setColor(Color.DARK_GRAY);
        g2.fillPolygon(this.polygon);
    }
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************



}
