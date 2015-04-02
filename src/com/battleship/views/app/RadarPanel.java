/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.constants.GameConstants;
import com.battleship.exceptions.ExecError;
import com.battleship.views.tools.Config;
import com.battleship.views.tools.ContentPanel;
import java.awt.Dimension;
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
    private GridPanel radarGrid;
    
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public RadarPanel(JPanel pParentPage, int pGridType) throws ExecError{
        super(pParentPage);
        Dimension BoxMapDim = Config.getDimValues_dim("dim-radar-boxmap");
        switch(pGridType){
            case GRID_TYPE_SQUARE:
                this.radarGrid = new GridSquareView(this, 10, 10, BoxMapDim, pGridType);
                break;
            case GRID_TYPE_HEXAGON:
                this.radarGrid = new GridSquareView(this, 10, 10, BoxMapDim, pGridType);
                break;
        }
        this.add(this.radarGrid);
        this.radarGrid.hideAllBoxMap();
    }

}
