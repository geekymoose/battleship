/*
 * Class :      NuclearBomb
 * Creation:    Feb 15, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.models.weapons;

import com.battleship.models.game.Player;





/**
 * <h1>NuclearBomb</h1>
 * <p>public class NuclearBomb<br/>
 * extends Weapon
 * </p>
 * <p>
 * Type of weapon that has a shotTypeNuclearBomb shotType meaning it can only 
 * shoot like a nuclear bomb.
 * The center compartment (the compartment that was clicked on) and all 
 * the neighbouring compartment are hit, up to two compartments away
 * (twenty-five compartments hit with a square grid and nineteen with an hexagon grid).
 * </p>
 *
 * 
 * @date Feb 15, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public class NuclearBomb extends Weapon{
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    public NuclearBomb(Player pOwner, int pAmmo){
        super(new shotTypeNuclearBomb(), pOwner, pAmmo);
    }
}
