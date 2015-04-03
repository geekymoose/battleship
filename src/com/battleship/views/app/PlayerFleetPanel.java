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
    private         GridPanel       currentGrid;
    private         GridPanel[]     fleetGridPlayers;
    
    
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public PlayerFleetPanel(JPanel pParentPage) throws ExecError{
        super(pParentPage);
        this.currentGrid        = null;
        this.fleetGridPlayers   = null;
    }
    
    /**
     * Switch radar displayed. display the radar for player whom number 
     * is given in parameter (First player is number 0)
     * @param playerTurn player number id (start at 0)
     */
    public void switchGrid(int playerTurn){
        this.currentGrid = this.fleetGridPlayers[playerTurn];
        this.removeAll();
        this.add(this.currentGrid);
        this.revalidate();
        this.currentGrid.setOpaque(false);
        this.repaint();
    }
    
    
    /**
     * Set fleet grid to display.
     * @param pFleet1
     * @param pFleet2 
     */
    public void setFleetGrids(GridPanel pFleet1, GridPanel pFleet2){
        this.fleetGridPlayers   = new GridPanel[2];
        this.fleetGridPlayers[0] = pFleet1;
        this.fleetGridPlayers[1] = pFleet2;
    }
}
