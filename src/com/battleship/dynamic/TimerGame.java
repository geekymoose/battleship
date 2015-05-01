/* 
 * Creation : 30 April 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.dynamic;

import com.battleship.constants.GameConstants;
import com.battleship.models.game.GameModel;
import java.awt.event.ActionEvent;





/**
 * <h1>TimerGame</h1>
 * <p>
 * public class TimerGame<br/>
 * extends TimerManager<br/>
 * implements GameConstants
 * </p>
 * <p>General timer for game panel</p>
 * 
 *
 * @date    May 1, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class TimerGame extends TimerManager implements GameConstants{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private     GameModel   model;
    
    
    
    
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    public TimerGame(GameModel pModel){
        super(DELAY_GAME);
        this.model   = pModel;
    }
    
    
    
    
    

    //**************************************************************************
    // Functions
    //**************************************************************************
    @Override
    public void actionPerformed(ActionEvent e){
        this.model.notifyObservers(null);
    }
}
