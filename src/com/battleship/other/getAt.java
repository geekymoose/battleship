/*
 * File :       getAt
 * Creation :   28 févr. 2015
 * Author :     Anthony CHAFFOT
 * Author :     Jessica Favin
 * Author :     Constantin MASSON
 * 
 * Projet computer science Java L2 - Battleship
 * 
 */
package com.battleship.other;

import com.battleship.models.game.FleetGridSquare;
import com.battleship.models.sprites.Boat;
import java.awt.Point;

/**
 * <h1>getAt</h1>
 * <p>
 * public class getAt<br/>
 * </p>
 *
 * <p>
 * Description</p>
 *
 * @date 28 févr. 2015
 * @author Anthony CHAFFOT
 * @author Jessica FAVIN
 * @author Contsantin MASSON
 */
public class getAt {
/////////////////////////////////////////////////////////////////////////

    public Point getUpperLeftCoordinates(Boat pBoat) {
        if (pBoat.grid instanceof FleetGridSquare) {
            return getUpperLeftCoordinatesSquare(pBoat);
        } else {
            return getUpperLeftCoordinatesHexagon(pBoat);
        }
    }

    public Point getCenterImg(Boat pBoat) {
        if (pBoat.grid instanceof FleetGridSquare) {
            return getCenterImgSquare(pBoat);
        } else {
            return getCenterImgHexagon(pBoat);
        }
    }

    public Point getFrontPositionImg(Boat pBoat) {
        if (pBoat.grid instanceof FleetGridSquare) {
            return getFrontPositionImgSquare(pBoat
            );
        } else {
            return getFrontPositionImgHexagon(pBoat);
        }
    }
///////////////////////////////////////////////////////////////////////////

    public Point getCenterImgSquare(Boat pBoat) {
        //vertical boat
        if (pBoat.getOrientation()==1) {
            return new Point(pBoat.getFrontPosition().getPosX()*40+20,
                    pBoat.getFrontPosition().getPosY()*40+pBoat.tabCompartments.length*40 / 2);
        } //horizontal boat
        else {
            return new Point(pBoat.getFrontPosition().getPosX()*40+pBoat.tabCompartments.length*40/2,
                    pBoat.getFrontPosition().getPosY()*40+20);
        }
    }

    public Point getUpperLeftCoordinatesSquare(Boat pBoat) {
        return new Point(pBoat.getFrontPosition().getPosX()*40,
                pBoat.getFrontPosition().getPosY()*40);
    }

    public Point getFrontPositionImgSquare(Boat pBoat) {
        return new Point(pBoat.getFrontPosition().getPosX()*40+20,
                pBoat.getFrontPosition().getPosY()*40+20);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    //This section is completely effed up cause I don't know how to calculate the coordinates//
    ///////////////////////////////////////////////////////////////////////////////////////////
    public Point getCenterImgHexagon(Boat pBoat) {
        //vertical boat
        if (pBoat.getOrientation()==1) {
            return new Point(pBoat.getFrontPosition().getPosX()+20,
                    pBoat.getFrontPosition().getPosY()+pBoat.tabCompartments.length*40/2);
        } //horizontal boat
        else {
            //set to the right
            if (pBoat.getOrientation()==3) {
                return new Point(pBoat.getFrontPosition().getPosX()+pBoat.tabCompartments.length*40/2,
                        pBoat.getFrontPosition().getPosY()+20);
            } //set to the left
            else {
                return new Point(pBoat.getFrontPosition().getPosX()-pBoat.tabCompartments.length*40/2,
                        pBoat.getFrontPosition().getPosY()+20);
            }
        }
    }

    public Point getUpperLeftCoordinatesHexagon(Boat pBoat) {
        if (pBoat.getOrientation()==4) {
            /*
             getFrontPosition en cas de bateau en diagonal vers la gauche
             retourne les coordonées de la boxmap en bas à gaueche?
             return new Point(??,??);
             */
            return null;
        } else {
            return new Point(pBoat.getFrontPosition().getPosX()*40,
                    pBoat.getFrontPosition().getPosY()*40);
        }
    }
    
    public Point getFrontPositionImgHexagon(Boat pBoat){
        return null;
    }
}
