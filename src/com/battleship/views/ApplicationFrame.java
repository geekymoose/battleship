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
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * @date 11 févr. 2015
 * @author Constantin MASSON
 * @author Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class ApplicationFrame extends JFrame implements GraphicalConstants, ObserverGame {

    CurrentView currentView;
    Model m = new Model();
    Controller c = new Controller(m);

    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public ApplicationFrame() {
        m.registerObserver((ObserverGame) this);
        this.pack();
        this.setSize(FRAME_SIZE_L, FRAME_SIZE_H);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle(FRAME_TITLE);
        this.setLayout(new BorderLayout());
        currentView = new ChooseGamePanel(c);
        this.getContentPane().add((Component) currentView);
        this.validate();
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    private void reInitView() {
        this.getContentPane().removeAll();
        this.getContentPane().add((Component) currentView);
        this.getContentPane().revalidate();
    }

    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************
    //**************************************************************************
    // PATTERN OBSERVER
    //**************************************************************************

    @Override
    public void update(ObservableGame o, Object arg) {
        switch (m.stateOfView) {
            case 0:
                currentView = new ChooseGamePanel(c);
                reInitView();
            case 1:
                currentView = new ChooseGridPanel(c);
                reInitView();
            case 2:
                currentView = new PlaceFleetPanel(c);
                reInitView();
            case 3:
                currentView = new GamePanel(c);
                reInitView();
        }
        /**
         * 0 : Main Menu ChooseGame 1 : ChooseGrid 2 : PlaceFleet 3 : GamePanel
         * */
    }

}
