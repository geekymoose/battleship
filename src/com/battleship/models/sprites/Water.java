/* 
 * Creation : Feb 15, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.models.sprites;

import com.battleship.behaviors.Sprite;
import com.battleship.constants.GameConstants;





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
    private     boolean     isHit;
    private     int         idImgExplosion;
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new water sprite. At the beginning, this water was not hit, if 
     * on shot reach this water, it is now hit 
     */
    public Water(){
        this.isHit = false;
        this.idImgExplosion = 105001;
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    @Override
    public boolean hit(){
        if(this.isHit == true){
            return false;
        }else{
            this.isHit = true;
            return true;
        }
    }
    
    @Override
    public boolean canBeHit(){
        return isHit == false;
    }
    
    @Override
    public int getId(){
        return GameConstants.WATER;
    }
        
    @Override
    public int getState(){
        return (this.isHit ? Sprite.DEAD_WATER : Sprite.ALIVE_WATER);
    }

    @Override
    public int getValue(){
        return GameConstants.NO_VALUE;
    }

    @Override
    public int getExplosion(){
        return this.idImgExplosion;
    }
}
