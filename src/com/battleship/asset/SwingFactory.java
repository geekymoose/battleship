/* 
 * Creation:    Feb 25, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.asset;

import com.battleship.constants.GameConstants;
import com.battleship.controllers.GameConfigController;
import com.battleship.controllers.GameController;
import com.battleship.controllers.GridController;
import com.battleship.controllers.PlaceBoatsController;
import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import com.battleship.models.game.FleetGridHexagon;

import com.battleship.models.game.FleetGridModel;
import com.battleship.models.game.FleetGridSquare;
import com.battleship.models.game.GameConfigModel;
import com.battleship.models.game.GameModel;
import com.battleship.models.game.Player;

import com.battleship.views.app.GameConfigPanel;
import com.battleship.views.app.GamePanel;
import com.battleship.views.app.GridHexaView;
import com.battleship.views.app.GridPanel;
import com.battleship.views.app.GridSquareView;
import com.battleship.views.app.PlaceBoatsPanel;
import com.battleship.views.tools.PagePanel;
import com.battleship.views.tools.WindowFrame;

import java.awt.Dimension;
import javax.swing.JPanel;





/**
 * <h1>SwingFactory</h1>
 * <p>
 * public class SwingFactory<br/>
 * implements GameConstants
 * </p>
 * 
 * <p>
 * This class enable us to create view class with the specific controller and 
 * its model. Each time a View page is created, it need its controller and model. 
 * This class enable us to automatically create this items for the view.
 * </p>
 *
 * @date    Feb 25, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public abstract class SwingFactory implements GameConstants{
    //**************************************************************************
    // Data
    //**************************************************************************
    //Models 
    private static GameConfigModel  model_gameConfig            = null;
    private static FleetGridModel   model_fleetGridSessPlayer   = null;
    private static GameModel        model_gameModel             = null;
    
    //Views
    private static GameConfigPanel  view_gameConfigPanel        = null;
    private static PlaceBoatsPanel  view_placeBoatsPanel        = null;
    private static GridPanel        view_fleetGridSessPlayer    = null;
    private static GamePanel        view_game                   = null;
    
    
    
    
    
    //**************************************************************************
    // Loading Functions for program used Models / Views / Controllers
    //**************************************************************************
    /**
     * Create Model / View / Controller for ConfigGamePanel
     * @param pFrame frame containing ConfigGamePanel
     * @return PagePanel ConfigGamePanel created
     * @throws ExecError if unable to create the panel
     */
    public static PagePanel loadConfigGame(WindowFrame pFrame) throws ExecError{
        if(SwingFactory.view_gameConfigPanel != null){
            DebugTrack.showExecMsg("ConfigGamePanel already loaded");
            return SwingFactory.view_gameConfigPanel;
        }
        GameConfigModel         m = new GameConfigModel();
        GameConfigController    c = new GameConfigController(m);
        GameConfigPanel         v = new GameConfigPanel(pFrame, c);
        m.addObserver(v);
        c.setView(v);
        v.initPage();
        SwingFactory.model_gameConfig     = m;
        SwingFactory.view_gameConfigPanel = v;
        return v;
    }
    
    
    
    /**
     * Create Model / View / Controller for PlaceBoatsPanel
     * @param pFrame    frame containing ConfigGamePanel
     * @return PagePanel PlaceBoatsPanel created
     * @throws ExecError if unable to create the panel
     */
    public static PagePanel loadPlaceBoats(WindowFrame pFrame) throws ExecError{
        if(SwingFactory.view_placeBoatsPanel != null){
            DebugTrack.showExecMsg("PlaceBoatsPanel already loaded");
            return SwingFactory.view_placeBoatsPanel;
        }
        if(SwingFactory.model_gameConfig == null){
            DebugTrack.showErrMsg("Error in loadGame - add error msg");
            throw new ExecError();
        }
        
        int gridWidth           = SwingFactory.model_gameConfig.getGridWidth();
        int gridHeight          = SwingFactory.model_gameConfig.getGridHeight();
        int gridType            = SwingFactory.model_gameConfig.getGridType();
        Dimension BoxMapDim     = Config.getDimValues_dim("dim-playerfleet-boxmap");
        
        FleetGridModel      m   = createFleetGridModel(gridWidth, gridHeight, gridType, Session.getPlayer());
        
        PlaceBoatsController    c   = new PlaceBoatsController(m);
        GridController          c2  = new GridController(m);
        PlaceBoatsPanel         v   = new PlaceBoatsPanel(pFrame, c);
        GridPanel               v2  = createGridPanel(v, c2, gridWidth, gridHeight, gridType, BoxMapDim);
        
        v.setGrid(v2);
        m.addObserver(v);
        m.addObserver(v2);
        c.setView(v);
        v.initPage();
        SwingFactory.model_fleetGridSessPlayer      = m;
        SwingFactory.view_fleetGridSessPlayer       = v2;
        SwingFactory.view_placeBoatsPanel           = v;
        return v;
    }
    
    
    /**
     * Create Model / View / Controller for Game 
     * @param pFrame frame containing ConfigGamePanel
     * @return PagePanel PlaceBoatsPanel created
     * @throws ExecError if unable to create the panel
     */
    public static PagePanel loadGame(WindowFrame pFrame) throws ExecError{
        if(SwingFactory.view_game != null){
            DebugTrack.showExecMsg("Game already loaded");
            return SwingFactory.view_game;
        }
        if(SwingFactory.model_gameConfig == null || SwingFactory.model_fleetGridSessPlayer == null){
            DebugTrack.showErrMsg("Error in class SwingFactory : loadGame");
            throw new ExecError();
        }
        
        GameConfigModel     config      = SwingFactory.model_gameConfig;
        GridPanel           playerGrid  = SwingFactory.view_fleetGridSessPlayer;
        GridPanel           enemyGrid   = SwingFactory.view_fleetGridSessPlayer;
        
        GameModel           m           = new GameModel(config);
        GameController      c           = new GameController(m);
        GamePanel           v           = new GamePanel(pFrame, c, playerGrid, enemyGrid);
        m.addObserver(v);
        c.setView(v);
        v.initPage();
        SwingFactory.model_gameModel    = m;
        SwingFactory.view_game          = v;
        return v;
    }
    
    
    
    
    
    //**************************************************************************
    // Extra function for elements loading
    //**************************************************************************
    /**
     * Create a FleetGrid with specific configuration and a owner
     * @param pW    grid width
     * @param pH    grid height
     * @param pType grid type
     * @param owner grid owner
     */
    private static FleetGridModel createFleetGridModel(int pW, int pH, int pType, Player owner){
        FleetGridModel grid = null;
        switch(pType){
            case GRID_TYPE_SQUARE:
                grid = new FleetGridSquare(pW, pH, owner);
                break;
            case GRID_TYPE_HEXAGON:
                grid = new FleetGridHexagon(pW, pH, owner);
                break;
        }
        return grid;
    }
    
    
    /**
     * Create a GridPanel with specific configuration
     * @param parent        Parent
     * @param pController   Grid controller
     * @param pW            grid width
     * @param pH            grid height
     * @param pType         grid type
     * @param pDim          dimension for one BoxMap
     * @return              GridPanel
     * @throws ExecError thrown if unable to create
     */
    private static GridPanel createGridPanel(JPanel parent, GridController pController, 
                                                    int pW, int pH, int pType, Dimension pDim) 
    throws ExecError{
        
        GridPanel grid = null;
        switch(pType){
            case GRID_TYPE_SQUARE:
                grid = new GridSquareView(parent, pController, pW, pH, pType, pDim);
                break;
            case GRID_TYPE_HEXAGON:
                grid = new GridHexaView(parent, pController, pW, pH, pType, pDim);
                break;
        }
        return grid;
    }
    
    
}
