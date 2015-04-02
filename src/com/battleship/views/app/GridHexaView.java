/* 
 * Creation:    Apr 1, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.controllers.GridController;
import com.battleship.exceptions.ExecError;
import com.battleship.observers.ObservableModel;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;



/**
 * <h1>GridHexaView</h1>
 * <p>
 * public class GridHexaView<br/>
 * extends GridPanel
 * </p>
 * <p>Display an hexagon grid</p>
 *
 * @date    Apr 1, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class GridHexaView extends GridPanel{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private     final int   BORDERS_FROM_TOP_AND_LEFT = 10;
    private     int         t, s, r, h; //Constants for dimension hexa
    
    
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new Hexagon grid in view
     * @param pParent       parent PagePanel where grid is placed
     * @param pController   grid controller
     * @param pW            grid width
     * @param pH            grid height
     * @param pDim          dimension of one BoxMap
     * @param pType         grid type
     * @throws ExecError thrown if error during creation
     */
    public GridHexaView(JPanel pParent, GridController pController, 
                        int pW, int pH, int pType, Dimension pDim) throws ExecError{
        super(pParent, pController,pW, pH, pType, pDim);
        this.initValues(pW); //Create all value
    }
    
    /**
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


    @Override
    public void update(ObservableModel o, Object arg){
     
    }
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    @Override
    protected Point pxToCoor(int mx, int my) {
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
    
    
    
    //**************************************************************************
    // Mouse Listener Event
    //**************************************************************************
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        Point p = new Point(pxToCoor(e.getX(), e.getY()));

        
        if (p.x < 10 && p.y < 10 && p.x >= 0 && p.y >= 0) {
            if (p.x < 10 && p.y < 10) {
                //tab[p.x][p.y] = 3;
                //repaint();
            }
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        Point p = new Point(pxToCoor(e.getX(), e.getY()));
        if (p.x < 10 && p.y < 10 && p.x >= 0 && p.y >= 0) {
            /*
            if (hasChanged(p)) {
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        selected[i][j] = false;
                    }
                }
            }
            */
            //tempX = p.x;
            //tempY = p.y;
            //System.out.println("temp = " + temp + "x=" + p.x + " y=" + p.y);
            //selected[p.x][p.y] = true;
            //repaint();
        } else {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    //selected[i][j] = false;
                }
            }
            repaint();
        }

    }
}
