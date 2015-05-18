/* 
 * Creation : 30 April 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.asset;

import java.awt.Dimension;
import java.awt.Point;



/**
 * <h1>GridCalculator</h1>
 * <p>public abstract class GridCalculator</p>
 * 
 * <p>
 * GridCalculator is a class with function used for grid calculation 
 * management. For example, it write function to get cell under cursor etc. 
 * It is also useful for Image placement in grid 
 * </p>
 *
 * @since   Apr 2, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public abstract class GridCalculator {
    //**************************************************************************
    // Grid Image placement functions
    //**************************************************************************
    /**
     * Return Upper Left Corner of an Hexagon BoxMapView, in function of its position 
     * in a grid
     * @param pPosition     position in grid coordinates where to place image
     * @param dim           box dimension
     * @return Point upper left corner of this box
     */
    public static Point hexaBoxUpperLeftCorner(Point pPosition, Dimension dim){
        Point   p   = new Point();
        int     dx  = 1; 
        int     dy  = -2;
        if(pPosition.x%2 != 0){
            dy += dim.height/2;
        }
        p.x = dx + (pPosition.x * (dim.width-8));
        p.y = dy + (pPosition.y * dim.height);
        return p;
    }
    
    /**
     * Return Upper Left Corner for a Square BoxMapView, in function of its position 
     * in a grid
     * @param pPosition     position in grid coordinates where to place image
     * @param dim           Box dimension
     * @return Point upper left corner of this box
     */
    public static Point squareBoxpUpperLeftCorner(Point pPosition, Dimension dim){
        Point   p   = new Point();
        int     dx  = 1;
        int     dy  = 1;
        p.x = dx + (pPosition.x * dim.width);
        p.y = dy + (pPosition.y * dim.height);
        return p;
    }
    
    
    //**************************************************************************
    // Grid positions functions
    //**************************************************************************
    /**
     * Convert absolute pixel position to coordinate position for square. 
     * Dimension is the size for one box, in order to process the position using 
     * position coordinates
     * (pxToSquareCoor -> pixelToSquareCoordinate)
     *
     * @param pX    x absolute position in pixel
     * @param pY    y absolute position in pixel 
     * @param pDim  BoxMap Dimension
     * @return Point position in coordinate mode
     */
    public static Point pxToSquareCoor(int pX, int pY, Dimension pDim){
        int x = pX/pDim.width;
        int y = pY/pDim.height;
        return new Point(x, y);
    }
    
    /**
     * Convert absolute pixel position to coordinate position. (for Hexagon grid)
     * (pxToHexaCoor -> pixelToHexagonCoordinate)
     *
     * @param mx    x absolute position in pixel
     * @param my    y absolute position in pixel 
     * @param pDim  BoxMap Dimension
     * @return Point position
     */
    public static Point pxToHexaCoor(int mx, int my, Dimension pDim) {
        //This function was inspired from http://www.quarkphysics.ca/scripsi/hexgrid/
        Point p = new Point(-1, -1);
        /*
         * h = basic dimension: height (distance between two adj centresr aka size)
         * r = radius of inscribed circle
         * s = (h/2)/cos(30)= (h/2) / (sqrt(3)/2) = h / sqrt(3)
         * t = (h/2) tan30 = (h/2) 1/sqrt(3) = h / (2 sqrt(3)) = r / sqrt(3)
         */
        int h  = pDim.height;
        int r  = h / 2;
        int s  = (int) (h / 1.73205);
        int t  = (int) (r / 1.73205);
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
