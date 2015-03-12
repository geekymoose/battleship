/* 
 * Creation:    Feb 25, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.controllers;

import com.battleship.exceptions.ExecError;
import com.battleship.models.game.GameConfigModel;
import com.battleship.models.game.GameModel;
import com.battleship.views.app.GameConfigPanel;
import com.battleship.views.app.PlaceBoatsPanel;





/**
 * <h1>PooFactory</h1>
 * <p>public class PooFactory</p>
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
public abstract class PooFactory {
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    private PooFactory(){
        //Abstract    
    }
    
    
    
    
    

    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Create Model and Controller for ConfigGamePanel
     * @param pView View to bound with controller created
     * @return controller created
     * @throws ExecError Exception if pView or model is null
     */
    public static GameConfigController loadConfigGame(GameConfigPanel pView) throws ExecError{
        GameConfigModel         m = new GameConfigModel();
        GameConfigController    c = new GameConfigController(pView, m);
        m.addObserver(pView);
        return c;
    }
}
