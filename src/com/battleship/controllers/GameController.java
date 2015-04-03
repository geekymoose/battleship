/*
 * Class :      GameController
 * Creation:    Mar 24, 2015
 * Author :     Constantin MASSON
 * 
 */

package com.battleship.controllers;

import com.battleship.asset.Config;
import com.battleship.asset.SwingFactory;
import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import com.battleship.models.game.FleetGridModel;
import com.battleship.models.game.GameModel;
import com.battleship.views.app.GridPanel;
import java.awt.Dimension;
import javax.swing.JPanel;





/**
 * <h1>GameController</h1>
 * <p>
 * public class GameController<br/>
 * extends Controller
 * </p>
 *
 * @author Constantin MASSON
 * @date Mar 24, 2015
 */
public class GameController extends Controller{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    
    
    
    
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new controller for game model and game panel view
     * @param pModel Model managed by this controller
     * @throws ExecError throws if pView or pMode is null
     */
    public GameController(GameModel pModel) throws ExecError{
        super(pModel);
        DebugTrack.showInitMsg("Create GameController controller");
    }
    
    /**
     * Create and return the GridPanel view linked with player fleet
     * @param pPan
     * @param pNumPlayer
     * @return
     * @throws ExecError 
     */
    public GridPanel initGridPlayer(JPanel pPan, int pNumPlayer) throws ExecError{
        Dimension       dim = Config.getDimValues_dim("dim-playerfleet-boxmap");
        FleetGridModel  m   = ((GameModel)this.model).getConfig().getPlayers()[0].getFleet();
        return SwingFactory.loadGridPanel(pPan, m, dim);
    }
    
    
    
    
    

    //**************************************************************************
    // Functions
    //**************************************************************************
}
