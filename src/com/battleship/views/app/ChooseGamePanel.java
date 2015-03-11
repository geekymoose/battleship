/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.views.interfaces.ApplicationView;
import com.battleship.views.interfaces.CurrentView;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;





/**
 * <h1></h1>
 *
 * @date    11 févr. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class ChooseGamePanel extends JPanel implements CurrentView {
    private     ApplicationView     parent;
    private     JPanel              p_container;
    private     GridBagConstraints  gc;
    
    //Buttons
    private     JButton             b_ia;
    private     JButton             b_2players;
    private     JButton             b_lan;
    private     JButton             b_internet;
    
    
    
    
    
    //**************************************************************************
    // CONSTRUCTOR - INITIALIZATION
    //**************************************************************************
    /**
     * Create a choose Game Panel
     * @param pParent parent Frame
     */
    public ChooseGamePanel(ApplicationView pParent) {
        this.parent     = pParent;
        this.initComponents();
    }
    
    private void initComponents() {
        this.p_container= new JPanel();
        this.gc         = new GridBagConstraints();
        this            .setLayout(new BorderLayout());
        this.p_container.setLayout(new GridBagLayout());
        
        //Create buttons 
        b_ia            = new JButton("Playe against AI");
        b_2players      = new JButton("2 Players mode");
        b_lan           = new JButton("Lan");
        b_internet      = new JButton("Internet");

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 10, 0, 10);
        gc.gridx = 0;
        gc.gridy = 0;
        p_container.add(b_ia, gc); 

        gc.gridx = 0;
        gc.gridy = 1;
        p_container.add(b_2players, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        p_container.add(b_lan, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        p_container.add(b_internet, gc);
        
        this.setBtnActions();
        this.add(p_container, BorderLayout.CENTER);
    }
    
    
    /*
     * Create actionListener for the buttons
     * Each button call the new JPanel for this application (Parent)
     */
    private void setBtnActions() {
        b_ia.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    parent.displayConfigGamePanel();
                }
            }
        );
        b_2players.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                }
            }
        );
        b_lan.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                }
            }
        );
        b_internet.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                }
            }
        );
    }//End setBtnActions
}
