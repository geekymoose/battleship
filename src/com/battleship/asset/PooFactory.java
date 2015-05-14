/*
 * Creation:    May 14, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.asset;

import com.battleship.models.weapons.Bomb;
import com.battleship.models.weapons.Missile;
import com.battleship.models.weapons.NuclearBomb;
import com.battleship.models.weapons.Torpedo;
import com.battleship.models.weapons.Weapon;



/**
 * <h1>PooFactory</h1>
 * <p>public class PooFactory</p>
 *
 * @since   May 14, 2015
 * @author  Constantin MASSON
 */
public class PooFactory {
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    

    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Return a new instance of weapon created from id given in parameter.
     * @param pValue id of the weapon to create
     * @param pAmmo  default ammo value
     * @return new weapon or null if error
     */
    public static Weapon createWeaponFromId(int pValue, int pAmmo){
        if(pAmmo < 0 && pAmmo != Weapon.INFINITE_AMO){
            return null;
            //throw new ForbiddenAction("Ammo can be negative");
        }
        switch(pValue){
            case Weapon.MISSILE:
                return new Missile(null, pAmmo);
            case Weapon.BOMB:
                return new Bomb(null, pAmmo);
            case Weapon.NUKE:
                return new NuclearBomb(null, pAmmo);
            case Weapon.TORPEDO:
                return new Torpedo(null, pAmmo);
            default:
                return null;
                //throw new ForbiddenAction("Weapon doesn't exists");
        }
    }
}
