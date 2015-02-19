/*
 * Class :      Torpedo
 * Creation:    Feb 15, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.models.weapons;

import com.battleship.models.game.Player;





/**
 * <h1>Torpedo</h1>
 * <p>
 * public class Torpedo<br/>
 * extends Weapon
 * </p>
 * <p>
 * Type of weapon that has a shotTypeTorpedo shotType meaning it can only shoot 
 * like a torpedo.
 * Five compartments hit with a shot forming an X around the center compartment.
 * (the compartment that was clicked on).
 * </p>
 *
 *
 * 
 * @date Feb 15, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public class Torpedo {
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    public Torpedo(Player pOwner, int pAmmo){
        super(new shotTypeTorpedo(), pOwner, pAmmo);
    }
}
