/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.models.game;

import com.battleship.observers.ObservableModel;





/**
 * <h1>FleetGridSquare</h1>
 * <p>
 * public class FleetGridSquare<br/>
 * extends FleetGridModel
 * </p>
 * 
 *
 * @date    Feb 9, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public class FleetGridSquare extends FleetGridModel{
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new FleetGrid with square coordinates
     * @param pWidth    Grid width
     * @param pHeight   Grid Height
     * @param pOwner    FleetGrid owner
     */
    public FleetGridSquare(int pWidth, int pHeight, Player pOwner){
        super(pWidth, pHeight, pOwner);
        this.tabBoxMap      = new BoxMapSquare[pHeight][pWidth];
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
    }
}
