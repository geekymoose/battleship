/*
 * Class :      Missile
 * Creation:    Feb 15, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.models.weapons;

import com.battleship.models.game.Player;





/**
 * <h1>Missile</h1>
 * <p>
 * public class Missile<br/>
 * extends Weapon
 * </p>
 * <p>
 * Type of weapon that has a shotTypeMissile shotTypr meaning it can only shoot 
 * like a missile.
 * One compartment hit with a shot (the compartment that was clicked on).
 * </p>
 *
 * 
 * @date Feb 15, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public class Missile extends Weapon{
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    public Missile(Player pOwner, int pAmmo){
        super(new shotTypeMissile(), pOwner, pAmmo);
    }
}
