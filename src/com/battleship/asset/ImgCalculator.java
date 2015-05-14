/* 
 * Creation : 30 April 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.asset;

import com.battleship.views.app.BoxMapView;
import java.awt.Dimension;
import java.awt.Point;





/**
 * <h1>ImgCalculator</h1>
 * <p>public abstract class ImgCalculator</p>
 *
 * <p>
 * Manage image displaying. Useful to place en image in a grid, using 
 * position coordinates. It is also used to recover data from image as 
 * upper left corner and so on
 * </p>
 * 
 * @since   Apr 30, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public abstract class ImgCalculator {
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private static Dimension    dimBox;
    
    
    
    
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    public ImgCalculator(){
    
    }
    
    
    
    
    

    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Return Upper Left Corner for a Hexagon BoxMapView, in function of its position 
     * in a grid
     * @param pBox  Box where to place image
     * @param dim   Box dimension
     * @return upper left corner of this box
     */
    public static Point hexaBoxMapUpperLeftCorner(BoxMapView pBox, Dimension dim){
        Point   p   = new Point();
        //To be honest, some value as +1 -2 and -8 are, just cuz I saw it is working
        int     dx  = GridCalculator.BORDERS_FROM_TOP_AND_LEFT+1;
        int     dy  = GridCalculator.BORDERS_FROM_TOP_AND_LEFT-2;
        
        if(pBox.getPosition().x%2 != 0){
            dy += dim.height/2;
        }
        p.x = dx + (pBox.getPosition().x * (dim.width-8));
        p.y = dy + (pBox.getPosition().y * dim.height);
        return p;
    }
    
    
    /**
     * Return Upper Left Corner for a Square BoxMapView, in function of its position 
     * in a grid
     * @param pBox  Box where to place image
     * @param dim   Box dimension
     * @return upper left corner of this box
     */
    public static Point squareBoxMapUpperLeftCorner(BoxMapView pBox, Dimension dim){
        Point   p   = new Point();
        int     dx  = 1;
        int     dy  = 1;
        p.x = dx + (pBox.getPosition().x * dim.width);
        p.y = dy + (pBox.getPosition().y * dim.height);
        return p;
    }
    
    
    
    
    

    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
}
