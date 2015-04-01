/* 
 * Creation:    Apr 1, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.views.app;

import com.battleship.observers.ObservableModel;
import java.awt.Dimension;
import java.awt.Graphics2D;





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
 */
public class BoxMapViewSquare extends BoxMapView{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    
    
    
    
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    public BoxMapViewSquare(int x, int y, Dimension pDim){
        super(x, y, pDim);
    }
    
    
    
    
    

    //**************************************************************************
    // Functions
    //**************************************************************************
    @Override
    public void update(ObservableModel o, Object arg){
    
    }
    
    
    
    
    
    //**************************************************************************
    // Draw fucntions
    //**************************************************************************
    @Override
    public void draw(Graphics2D g2){
    }
    
    
    @Override
    protected void drawHidden(Graphics2D g2){
    }
}
