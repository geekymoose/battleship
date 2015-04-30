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
 * implements GameConstants
 * </p>
 * 
 * <p>
 * Display the players grids. This class also manage the cursor behavior if 
 * above this grid.
 * The Grid from all player are placed in PlayerFleetPanel. But only one grid is 
 * displayed at the same time. switch functions are used to swith the grid 
 * displayed.
 * </p>
 * 
 * 
 * @date    11 Feb. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class PlayerFleetPanel extends ContentPanel implements GameConstants {
    //**************************************************************************
    // VARIABLES 
    //**************************************************************************
    private         GridPanel       currentGrid;
    private         GridPanel[]     fleetGridPlayers;
    
    
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    /**
     * Create a new PlayerFleetPanel which display the player fleet on the game board
     * @param pParentPage parent
     * @throws ExecError thrown if parent is null
     */
    public PlayerFleetPanel(JPanel pParentPage) throws ExecError{
        super(pParentPage);
        this.currentGrid        = null;
        this.fleetGridPlayers   = new GridPanel[2];
    }
    
    /**
     * Initialize the 2 grid on the PlayerGridPanel.
     * The first grid is the owned by player 1
     * @param pFleet1 fleet owned by player 1
     * @param pFleet2 fleet owned by player 2
     */
    public void initGrids(GridPanel pFleet1, GridPanel pFleet2){
        this.fleetGridPlayers[0]    = pFleet1;
        this.fleetGridPlayers[1]    = pFleet2;
        
        this.fleetGridPlayers[0].setOpaque(false);
        this.fleetGridPlayers[1].setOpaque(false);
    }
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Switch turn behaviors
     * @param playerTurn player turn (First player is 0, second is 1 ...)
     */
    public void switchTurne(int playerTurn){
        this.switchGrid(playerTurn);
        this.switchCursorBehavior(playerTurn);
    }
    
    /**
     * Switch grid displayed. display the fleet for player whom number 
     * is given in parameter (First player is number 0)
     * @param playerTurn player number id (start at 0)
     */
    private void switchGrid(int playerTurn){
        this.removeAll();
        this.currentGrid = this.fleetGridPlayers[playerTurn];
        this.add(this.currentGrid);
        this.revalidate();
        this.repaint();
    }
    
    /**
     * Switch cursor behavior for all player when cursor is above fleet grid. 
     * @param playerTurn current player turn (Start at 0)
     */
    private void switchCursorBehavior(int playerTurn){
        /*
         * Note, in fact, there are no actions for this fleet panel, 
         * player can only see his boats but not click
         */
        switch(playerTurn){
            case 0:
                this.fleetGridPlayers[0].getGridCursor().setClickNoAction();;
                this.fleetGridPlayers[1].getGridCursor().setClickNoAction();;
                break;
            case 1:
                this.fleetGridPlayers[0].getGridCursor().setClickNoAction();;
                this.fleetGridPlayers[1].getGridCursor().setClickNoAction();;
                break;
        }
    }
}
