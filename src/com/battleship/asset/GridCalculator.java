/*
 * Class :      GridCalculator
 * Creation:    Apr 3, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.asset;

import java.awt.Dimension;
import java.awt.Point;





/**
 * <h1>GridCalculator</h1>
 * <p>public abstract class GridCalculator</p>
 *
 * @date    Apr 2, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public abstract class GridCalculator {
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private static final int    BORDERS_FROM_TOP_AND_LEFT = 10;
    private static int          t, s, r, h; //Constants for dimension hexa
    private static Dimension    dimBox;
    
    
    
    
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    protected GridCalculator(){
        
    }
    
    /**
     * Initialize dimension for square
     * @param pDim 
     */
    private static void initSquareValues(Dimension pDim){
        dimBox = pDim;
    }
    
    
    /**
     * Set all the sizes
     * Set size for elements
     * @param height
     */
    private static void initHexaValues(Dimension pDim) {
        int height = pDim.height;
        /*
         * h = basic dimension: height (distance between two adj centresr aka size)
         * r = radius of inscribed circle
         * s = (h/2)/cos(30)= (h/2) / (sqrt(3)/2) = h / sqrt(3)
         * t = (h/2) tan30 = (h/2) 1/sqrt(3) = h / (2 sqrt(3)) = r / sqrt(3)
         */
        h  = height;
        r  = h / 2;
        s  = (int) (h / 1.73205);
        t  = (int) (r / 1.73205);
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Convert absolute pixel position to coordinate position for square
     * (pxToSquareCoor -> pixelToSquareCoordinate)
     *
     * @param pX    x value
     * @param pY    y value 
     * @param pDim  BoxMap Dimension
     * @return
     */
    public static Point pxToSquareCoor(int pX, int pY, Dimension pDim){
        initSquareValues(pDim);
        return pxToSquareCoor(pX, pY);
    }
    
    
    /**
     * Convert absolute pixel position to coordinate position for square
     * (pxToSquareCoor -> pixelToSquareCoordinate)
     *
     * @param pX x value
     * @param pY y value 
     * @return
     */
    private static Point pxToSquareCoor(int pX, int pY){
        int x = pX/dimBox.width;
        int y = pY/dimBox.height;
        return new Point(x, y);
    }
    
    
    /**
     * Convert absolute pixel position to coordinate position. (for Hexagon grid)
     * (pxToHexaCoor -> pixelToHexagonCoordinate)
     *
     * @param mx        x value
     * @param my        y value 
     * @param pDim      Box Dimension
     * @return
     */
    public static Point pxToHexaCoor(int mx, int my, Dimension pDim) {
        initHexaValues(pDim);
        return pxToHexaCoor(mx, my);
    }
    
    
    
    /**
     * Convert absolute pixel position to coordinate position. (for Hexagon grid)
     * (pxToHexaCoor -> pixelToHexagonCoordinate)
     *
     * @param mx x value
     * @param my  y value 
     * @return
     */
    private static Point pxToHexaCoor(int mx, int my) {
        Point p = new Point(-1, -1);

        //correction for BORDERS and XYVertex
        mx -= BORDERS_FROM_TOP_AND_LEFT;
        my -= BORDERS_FROM_TOP_AND_LEFT;
        /*
         * This gives a quick value for x. 
         * It works only on odd cols and doesn't handle the triangle sections. 
         * It assumes that the hexagon is a rectangle with width s+t (=1.5*s).
         */
        int x = (int) (mx / (s + t)); 
        
        /*
         * This gives the row easily. 
         * It needs to be offset by h/2 (=r)if it is in an even column
         */
        int y = (int) ((my - (x % 2) * r) / h); 

        /**
         * ****FIX for clicking in the triangle spaces (on the left side
         * only)******
         */
        //dx,dy are the number of pixels from the hex boundary. (ie. relative to the hex clicked in)
        int dx = mx - x * (s + t);
        int dy = my - y * h;

        if (my - (x % 2) * r < 0) {
            return p; // prevent clicking in the open halfhexes at the top of the screen
        }
		//System.out.println("dx=" + dx + " dy=" + dy + "  > " + dx*r/t + " <");

        //even columns
        if (x % 2 == 0) {
            if (dy > r) {	//bottom half of hexes
                if (dx * r / t < dy - r) {
                    x--;
                }
            }
            if (dy < r) {	//top half of hexes
                if ((t - dx) * r / t > dy) {
                    x--;
                    y--;
                }
            }
        } else {  // odd columns
            if (dy > h) {	//bottom half of hexes
                if (dx * r / t < dy - h) {
                    x--;
                    y++;
                }
            }
            if (dy < h) {	//top half of hexes
                //System.out.println("" + (t- dx)*r/t +  " " + (dy - r));
                if ((t - dx) * r / t > dy - r) {
                    x--;
                }
            }
        }
        p.x = x;
        p.y = y;
        return p;
    }
    
    
}
