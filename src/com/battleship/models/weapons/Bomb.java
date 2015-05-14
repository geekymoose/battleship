/*
 * Class :      Bomb
 * Creation:    Feb 15, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.models.weapons;

import com.battleship.asset.Config;
import com.battleship.models.game.Player;





/**
 * <h1>Bomb</h1>
 * <p>
 * public class Bomb<br/>
 * extends Weapon
 * </p>
 * <p>
 * Type of weapon that has a shotTypeBomb shotType meaning it can only shoot 
 * like a bomb.
 * The center compartment (the compartment that was clicked on) and all 
 * the neighbor compartment are hit (nine compartments hit with a square grid 
 * and seven with an hexagon grid).
 * </p>
 *
 * 
 * @since   Feb 15, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public class Bomb extends Weapon{
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    public Bomb(Player pOwner, int pAmmo){
        super(Weapon.BOMB, new ShotTypeBomb(), pOwner, pAmmo);
        this.name           = "Bomb";
        this.priceWeapon    = Config.getGameValues_int("price-bomb");
        this.priceAmmo      = Config.getGameValues_int("price-ammo-bomb");
    }
}
