/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.views.interfaces.ApplicationView;
import com.battleship.views.interfaces.CurrentView;
import javax.swing.JPanel;





// affiche une grille drag & drop dans laquelle on place les bateau du dock => EN
/**
 *
 * 
 * @date    11 févr. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class PlaceFleetPanel extends JPanel implements CurrentView{
    private     ApplicationView     parent;
    
    
    
    
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    /**
     * 
     * @param pParent 
     */
    public PlaceFleetPanel(ApplicationView pParent){
        this.parent = pParent;
    }
}
