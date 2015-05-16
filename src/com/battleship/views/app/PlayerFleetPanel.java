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
 * @since   11 Feb. 2015
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
    // Contructors - Initialization
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
     * Initialize the 2 grid on the PlayerGridPanel and display the grid owned 
     * by player playing first. If played id given is not a actual played, display 
     * nothing
     * @param pFleet1       fleet owned by player 1
     * @param pFleet2       fleet owned by player 2
     * @param pToDisplay    first grid to display (Player id)
     */
    public void initGrids(GridPanel pFleet1, GridPanel pFleet2, int pToDisplay){
        this.fleetGridPlayers[0]    = pFleet1;
        this.fleetGridPlayers[1]    = pFleet2;
        
        this.fleetGridPlayers[0].setOpaque(false);
        this.fleetGridPlayers[1].setOpaque(false);
        
        this.displayGridPlayer(pToDisplay);
    }
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Display grid owned by player given in parameter
     * @param pIdPlayer id player owning the grid to display
     */
    public void displayGridPlayer(int pIdPlayer){
        if(pIdPlayer>=0 && pIdPlayer <= 2){
            this.switchGrid(pIdPlayer);
        }
    }
    
    /**
     * Switch grid displayed. display the fleet for player whom number 
     * is given in parameter (First player is number 0)
     * @param playerTurn player number id (start at 0)
     */
    public void switchGrid(int playerTurn){
        this.removeAll();
        this.currentGrid = this.fleetGridPlayers[playerTurn];
        this.add(this.currentGrid);
        this.revalidate();
        this.repaint();
    }
    
    
    //**************************************************************************
    // UI Functions
    //**************************************************************************
    @Override
    public void loadUI(){
        this.reloadUI();
    }
    
    @Override
    public void reloadUI(){
        for(GridPanel grid : this.fleetGridPlayers){
            grid.reloadUI();
        }
        this.repaint();
    }
    
    //**************************************************************************
    // Getters - Setters 
    //**************************************************************************
    public GridPanel getCurrentFleetPanel(){
        return this.currentGrid;
    }
}
