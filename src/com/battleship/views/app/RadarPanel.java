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
 * <h1>RadarPanel</h1>
 * <p>
 * public class RadarPanel<br/>
 * extends ContentPanel
 * </p>
 * <p>Display user radar grid</p>
 * 
 * @date    11 févr. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class RadarPanel extends ContentPanel implements GameConstants{
    //**************************************************************************
    // VARIABLES 
    //**************************************************************************
    private         GridPanel       currentGrid;
    private         GridPanel[]     fleetGridPlayers;
    
    
    
    //**************************************************************************
    // Constructors - Initialization
    //**************************************************************************
    /**
     * Create a new RadarPanel which display radar for game
     * @param pParentPage parent of this radar
     * @throws ExecError thrown if ExecError is null
     */
    public RadarPanel(JPanel pParentPage) throws ExecError{
        super(pParentPage);
        this.currentGrid        = null;
        this.fleetGridPlayers   = new GridPanel[2];
    }
    
    
    /**
     * Initialize the 2 grid on the RadarPanel. 
     * The first grid is the owned by player 1
     * @param pFleet1 fleet owned by player 1
     * @param pFleet2 fleet owned by player 2
     */
    public void initGrids(GridPanel pFleet1, GridPanel pFleet2){
        this.fleetGridPlayers[0]    = pFleet1;
        this.fleetGridPlayers[1]    = pFleet2;
        
        this.fleetGridPlayers[0].setOpaque(false);
        this.fleetGridPlayers[1].setOpaque(false);
        
        this.fleetGridPlayers[0].hideAllBoxMap();
        this.fleetGridPlayers[1].hideAllBoxMap();
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
     * Switch radar displayed. display the radar for player whom number 
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
                this.fleetGridPlayers[0].getGridCursor().setClickShoot();;
                this.fleetGridPlayers[1].getGridCursor().setClickNoAction();;
                break;
            case 1:
                this.fleetGridPlayers[0].getGridCursor().setClickNoAction();;
                this.fleetGridPlayers[1].getGridCursor().setClickShoot();;
                break;
        }
    }
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return array of GridPanel
     * @return GridPanel[]
     */
    public GridPanel[] getGrids(){
        return this.fleetGridPlayers;
    }
    
    /**
     * Display current grid
     */
    public void displayCurrentGrid(){
        this.currentGrid.visibleAllBoxMap();
    }
    
    /**
     * Hide current grid
     */
    public void hideCurrentGrid(){
        this.currentGrid.visibleAllBoxMap();
    }
}
