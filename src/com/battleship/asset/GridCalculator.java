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
     * @param pPos      position in grid coordinates where to place image
     * @param pImgDim   dimension of the image to place in the grid
     * @param pBoxDim   dimension of a box on the grid
     * @return Point upper left corner of this box
     */
    public static Point placeImgHexa(Point pPos, Dimension pImgDim, Dimension pBoxDim){
        Point   p   = GridCalculator.coordToPxCenterHexa(pPos, pBoxDim);
        p.x -= pImgDim.width/2;
        p.y -= pImgDim.height/2;
        return p;
    }
    
    /**
     * Return Upper Left Corner for a Square BoxMapView, in function of its position 
     * in a grid. It will place image given at position pPos.
     * @param pPos      position in grid coordinates where to place image
     * @param pImgDim   dimension of the image to place in the grid
     * @param pBoxDim   dimension of a box on the grid
     * @return Point upper left corner of this box
     */
    public static Point placeImgSquare(Point pPos, Dimension pImgDim, Dimension pBoxDim){
        Point p = GridCalculator.coordToPxCenterSquare(pPos, pBoxDim);
        p.x -= pImgDim.width/2;
        p.y -= pImgDim.height/2;
        return p;
    }
    
    /**
     * Place an image to display hexa grid
     * @param pPos      position in grid coordinates where to place image
     * @param pImgDim   dimension of the image to place in the grid
     * @param pBoxDim   dimension of a box on the grid
     * @return Point upper left corner of this box
     */
    public static Point placeGridImgHexa(Point pPos, Dimension pImgDim, Dimension pBoxDim){
        /*
         * 
         * This function should be same as placeImgHexa, but there is a development error. 
         * Hexa image are no well managed, and the image as empty border. These border 
         * has to be deleted by the 1 and -2
         */
        Point   p   = new Point();
        int     dx  = 1 + pBoxDim.width/2;
        int     dy  = -2 + pBoxDim.height/2;
        if(pPos.x%2 != 0){
            dy += pBoxDim.height/2;
        }
        p.x = dx + (pPos.x * (pBoxDim.width-8))- pImgDim.width/2;
        p.y = dy + (pPos.y * pBoxDim.height)- pImgDim.height/2;
        return p;
    }
    
    
    //**************************************************************************
    // Get absolute center position from coordinates
    //**************************************************************************
    /**
     * Convert square coordinate position (From a grid) to absolute pixel. 
     * For example, if square at coordinate x = 1 / y = 3 is given, the pixel 
     * position of the square(1,3) will be returned. Center position is returned. 
     * 
     * @param pPos      coordinate position on square grid
     * @param pBoxDim   box dimension
     * @return center absolute position
     */
    public static Point coordToPxCenterSquare(Point pPos, Dimension pBoxDim){
        Point   p   = new Point();
        //Cuz img is 48 px, box dim is 50
        p.x         = 1 + pBoxDim.width/2   + (pPos.x * pBoxDim.width);
        p.y         = 1 + pBoxDim.height/2  + (pPos.y * pBoxDim.height);
        return p;
    }
    
    /**
     * Convert hexa coordinate position (From a grid) to absolute pixel. 
     * Center position is returned. 
     * 
     * @param pPos      coordinate position on hexa grid
     * @param pBoxDim   box dimension
     * @return center absolute position
     */
    public static Point coordToPxCenterHexa(Point pPos, Dimension pBoxDim){
        Point   p   = new Point();
        int     dx  = 3+pBoxDim.width/2;
        int     dy  = pBoxDim.height/2;
        if(pPos.x%2 != 0){
            dy += pBoxDim.height/2;
        }
        p.x = dx + (pPos.x * (pBoxDim.width-8));
        p.y = dy + (pPos.y * pBoxDim.height);
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
            return new Point(-1, -1);
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
        return new Point(x,y);
    }
}
