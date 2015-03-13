/* 
 * Creation:    Feb 25, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.controllers;

import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import com.battleship.models.game.GameConfigModel;
import com.battleship.models.game.GameModel;
import com.battleship.views.app.GameConfigPanel;
import com.battleship.views.app.PlaceBoatsPanel;
import com.battleship.views.tools.PagePanel;
import com.battleship.views.tools.WindowFrame;





/**
 * <h1>SwingFactory</h1>
 * <p>public class SwingFactory</p>
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
public abstract class SwingFactory {
    //**************************************************************************
    // Data
    //**************************************************************************
    private static GameConfigModel  model_gameConfig        = null;
    private static GameModel        model_gameModel         = null;
    
    private static GameConfigPanel  view_gameConfigPanel    = null;
    private static PlaceBoatsPanel  view_placeBoatsPanel    = null;
    
    
    
    
    
    //**************************************************************************
    // Functions
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
     * @param pFrame frame containing ConfigGamePanel
     * @return PagePanel PlaceBoatsPanel created
     * @throws ExecError if unable to create the panel
     */
    public static PagePanel loadPlaceBoats(WindowFrame pFrame) 
    throws ExecError{
        if(SwingFactory.view_placeBoatsPanel!=null){
            DebugTrack.showExecMsg("PlaceBoatsPanel already loaded");
            return SwingFactory.view_placeBoatsPanel;
        }
        GameModel               m = new GameModel(SwingFactory.model_gameConfig);
        PlaceBoatsController    c = new PlaceBoatsController(m);
        PlaceBoatsPanel         v = new PlaceBoatsPanel(pFrame, c);
        m.addObserver(v);
        c.setView(v);
        v.initPage();
        SwingFactory.model_gameModel      = m;
        SwingFactory.view_placeBoatsPanel = v;
        return v;
    }
}
