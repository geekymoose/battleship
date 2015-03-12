/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.controllers.PlaceBoatsController;
import com.battleship.controllers.PooFactory;
import com.battleship.views.tools.ApplicationView;
import com.battleship.views.tools.ViewPage;
import javax.swing.JPanel;





/**
 *
 * 
 * @date    11 févr. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class PlaceBoatsPanel extends JPanel implements ViewPage{
    private     final ApplicationView           app;
    private     final PlaceBoatsController      controller;
    
    
    
    
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    /**
     * 
     * @param pApp      Application containing this panel
     */
    public PlaceBoatsPanel(ApplicationView pApp){
        //this.controller = PooFactory.loadConfigGame(this);
        this.controller = null;
        this.app = pApp;
    }
}
