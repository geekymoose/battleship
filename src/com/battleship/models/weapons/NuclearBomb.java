/*
 * Class :      NuclearBomb
 * Creation:    Feb 15, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.models.weapons;

import com.battleship.asset.Config;
import com.battleship.constants.GameConstants;
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
 * the neighbor compartment are hit, up to two compartments away
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
        super(GameConstants.NUKE, new ShotTypeNuclearBomb(), pOwner, pAmmo);
        this.name           = "Nuke";
        this.priceWeapon    = Config.getGameValues_int("price-nuke");
        this.priceAmmo      = Config.getGameValues_int("price-ammo-nuke");
    }
}
