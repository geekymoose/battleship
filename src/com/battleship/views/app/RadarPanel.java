/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.constants.GameConstants;
import com.battleship.exceptions.ExecError;
import com.battleship.observers.ObservableModel;
import com.battleship.observers.ObserverModel;
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
 * @since   11 févr. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class RadarPanel extends ContentPanel implements ObserverModel, GameConstants{
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
     * Initialize the 2 grid on the RadarPanel. and display the grid owned 
     * by player playing first. If played id given is not a actual played, display 
     * nothing.
     * @param pFleet1       fleet owned by player 1
     * @param pFleet2       fleet owned by player 2
     * @param pOwner        id player owning this radar
     * @param pShooter      owner biggest foe!
     */
    public void initGrids(GridPanel pFleet1, GridPanel pFleet2, int pOwner, int pShooter){
        this.fleetGridPlayers[0]    = pFleet1;
        this.fleetGridPlayers[1]    = pFleet2;
        
        this.fleetGridPlayers[0].setOpaque(false);
        this.fleetGridPlayers[1].setOpaque(false);
        
        this.fleetGridPlayers[0].hideAllBoxMap();
        this.fleetGridPlayers[1].hideAllBoxMap();
        
        this.displayRadarPlayer(pOwner, pShooter);
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Display radar owned by player given in parameter. The first player id is 
     * the grid owner, the grid displayed is his grid. The second parameter is 
     * the played that will attack the grid
     * @param pOwner    id player owning this radar
     * @param pShooter  owner biggest foe!
     */
    public void displayRadarPlayer(int pOwner, int pShooter){
        if(pOwner>=0 && pOwner <= 2){
            this.switchGrid(pOwner);
            this.setAttackedGrid(pOwner);
        }
    }
    
    
    /**
     * Switch radar displayed. display the radar for player whom number 
     * is given in parameter (First player is number 0)
     * @param pOwner player number id (start at 0)
     */
    public void switchGrid(int pOwner){
        this.removeAll();
        this.currentGrid = this.fleetGridPlayers[pOwner];
        this.add(this.currentGrid);
        this.revalidate();
        this.repaint();
    }
    
    /**
     * Set new attacked grid, other player has their cursor set to noAction.
     * @param pOwner player number id (start at 0)
     */
    public void setAttackedGrid(int pOwner){
        for(GridPanel grid:this.fleetGridPlayers){
            grid.getGridCursor().setClickNoAction();
        }
        this.fleetGridPlayers[pOwner].getGridCursor().setClickShoot();
    }
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
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

    @Override
    public void update(ObservableModel o, Object arg){
    }
}
