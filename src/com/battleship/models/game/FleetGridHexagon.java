/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.models.game;

import com.battleship.observers.ObservableModel;





/**
 * <h1>FleetGridHexagon</h1>
 * <p>
 * public class FleetGridHexagon<br/>
 * extends FleetGridModel
 * </p>
 * 
 *
 * @date    Feb 9, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public class FleetGridHexagon extends FleetGridModel{
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new FleetGrid with hexagon coordinates
     * @param pWidth    Grid width
     * @param pHeight   Grid Height
     * @param pOwner    FleetGrid owner
     */
    public FleetGridHexagon(int pWidth, int pHeight, Player pOwner){
        super(pWidth, pHeight, pOwner);
        this.tabBoxMap      = new BoxMapHexagon[pHeight][pWidth];
    }
    
    
    
    
    

    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * @deprecated 
     */
    @Override
    public BoxMap getNextBoxMap(BoxMap pPos, int pDirection){
        return null;
    }


    @Override
    public void update(ObservableModel o, Object arg){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
