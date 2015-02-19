/* 
 * Creation : Feb 15, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.models.sprites;

import com.battleship.behaviors.Sprite;





/**
 * <h1>Water</h1>
 * <p>
 * public class Water<br/>
 * implements Sprite
 * </p>
 * 
 * <p>
 * Water is a Sprite which represent only water.<br/>
 * water got 2 states
 * </p>
 *  <ul>
 *      <li>Hit</li>
 *      <li>Not hit</li>
 *  </ul>
 * <h3>Hit</h3>
 * <p>This sprite was not already hit by a shot and it could happen soon.</p>
 * <h3>Not Hit</h3>
 * <p>This sprite was already hit by a shot, it is of no use to shot on again.</p>
 *
 * 
 * @date    Feb 15, 2015
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * @author  Contsantin MASSON
 */
public class Water implements Sprite{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private     final int   HIT         = 1;
    private     final int   NOT_HIT     = 2;
    private     int         state;
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new water sprite. At the beginning, this water was not hit, if 
     * on shot reach this water, it is now hit 
     */
    public Water(){
        this.state  = NOT_HIT;
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    @Override
    public boolean hit(){
        this.state = HIT;
        return true;
    }
    
    @Override
    public boolean canBeHit(){
        return this.state==NOT_HIT;
    }
}
