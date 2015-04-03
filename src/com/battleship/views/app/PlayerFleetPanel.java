/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.constants.GameConstants;
import com.battleship.exceptions.ExecError;
import com.battleship.views.tools.ContentPanel;
import javax.swing.JPanel;




/**
 * <h1>PlayerFleetPanel</h1>
 * <p>
 * public class PlayerFleetPanel<br/>
 * extends ContentPanel
 * </p>
 * <p>Display a player grid</p>
 * 
 * 
 * @date    11 févr. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class PlayerFleetPanel extends ContentPanel implements GameConstants{
    //**************************************************************************
    // VARIABLES 
    //**************************************************************************
    private GridPanel fleetGrid;
    
    
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public PlayerFleetPanel(JPanel pParentPage) throws ExecError{
        super(pParentPage);
    }
    
    public void setFleetGrid(GridPanel pFleet){
        this.fleetGrid = pFleet;
        this.add(this.fleetGrid);
        this.fleetGrid.hideAllBoxMap();
    }
    
}
