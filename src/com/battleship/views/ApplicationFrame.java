/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views;

import com.battleship.constants.GraphicalConstants;
import com.battleship.observer.Observable;
import com.battleship.observer.Observer;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * @date 11 févr. 2015
 * @author Constantin MASSON
 * @author Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class ApplicationFrame extends JFrame implements GraphicalConstants, Observer{
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
    public void update(Observable obs) {
        // Variable dans le model qui dit dans quel état doit être la view
    }
     
}
