/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views;

import com.battleship.constants.GraphicalConstants;
import com.battleship.observers.ObservableGame;
import com.battleship.observers.ObserverGame;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JFrame;

/**
 * @date    11 févr. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class ApplicationFrame extends JFrame implements GraphicalConstants, ObserverGame{
    CurrentView currentView;
    
    
    
    
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public ApplicationFrame() {
        this.pack();
        this.setSize(FRAME_SIZE_L, FRAME_SIZE_H);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle(FRAME_TITLE);
        this.setLayout(new BorderLayout());
        currentView = new ChooseGamePanel();
        
        this.getContentPane().add((Component) currentView);
    }
    
    
    
    
    
    //**************************************************************************
    // METHODS
    //**************************************************************************
    
    
    
    
    
    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************
    
    
    
    
    
    //**************************************************************************
    // PATTERN OBSERVER
    //**************************************************************************
    @Override
    public void update(ObservableGame o, Object arg){
        // Variable dans le model qui dit dans quel état doit être la view
        int varModel = 10;
        
        switch(varModel){
            case 0: currentView = new ChooseGamePanel();
            case 1: currentView = new ChooseGridPanel();
            case 2: currentView = new PlaceFleetPanel();
            case 3: currentView = new GamePanel();
        }
    }
}
