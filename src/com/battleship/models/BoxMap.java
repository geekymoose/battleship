/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.models;

import com.battleship.behaviors.Target;





/**
 * <h1>BoxMap</h1>
 * <p>
 * public class BoxMap<br/>
 * implements Target
 * </p>
 * 
 * <p>
 * This class represents a square on GridFleetModel. 
 * No matter the kind of grid (ex: square
 * or hexagon), BoxMap is only one compartment in this grid. (Grid itself know box position,
 * therefore BoxMap do not need to know its x:y position)<br/>
 * BoxMap can be targeted by a shoot, that is why it implements.
 * </p>
 * 
 *
 * @date    Feb 9, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 * @see GridFleetModel
 */
public class BoxMap implements Target{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private     Object      content;
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new BoxMap
     */
    public BoxMap(){
    
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    @Override
    public void hit(){
    
    }

    @Override
    public boolean isValideTarget(){
        return false;
    }
    
    
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return the content in this BoxMap
     * @return the content in this BoxMap
     */
    public Object getContent(){
        return this.content;
    }
}
