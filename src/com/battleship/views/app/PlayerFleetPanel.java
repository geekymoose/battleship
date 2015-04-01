/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.constants.GameConstants;
import com.battleship.exceptions.ExecError;
import com.battleship.views.tools.Config;
import com.battleship.views.tools.ContentPanel;
import com.battleship.views.tools.PagePanel;
import java.awt.Dimension;




/**
 * <h1>PlayerFleetPanel</h1>
 * <p>
 * public class PlayerFleetPanel<br/>
 * extends ContentPanel
 * </p>
 * <p>Display a player grid</p>
 * 
 * 
 * @date    11 févr. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class PlayerFleetPanel extends ContentPanel implements GameConstants{
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public PlayerFleetPanel(PagePanel pParentPage) throws ExecError{
        super(pParentPage);
        Dimension BoxMapDim = Config.getDimValues_dim("dim-playerfleet-boxmap");
        this.add(new GridHexaView(this, 10, 10, BoxMapDim, GRID_TYPE_HEXAGON));
    }
}
