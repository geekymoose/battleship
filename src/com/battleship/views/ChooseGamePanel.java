/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views;

import Controller.Controller;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Choix au début du style de partie 1v1 ou en ligne etc ...
 *
 * @date 11 févr. 2015
 * @author Constantin MASSON
 * @author Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class ChooseGamePanel extends JPanel implements CurrentView {

    Controller c;
    JButton b_ia;
    JButton b_2players;
    JButton b_lan;
    JButton b_internet;
    JPanel p_container;
    GridBagConstraints gc = new GridBagConstraints();

    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************

    public ChooseGamePanel(Controller cParam) {
        this.c = cParam;
        this.setLayout(new BorderLayout());
        p_container = new JPanel();
        initComponents();
        setBtnActions();
        this.add(p_container, BorderLayout.CENTER);
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    private void initComponents() {
        b_ia = new JButton("Jouer contre l'IA");
        b_2players = new JButton("Jouer à deux joueurs");
        b_lan = new JButton("Jouer en réseau local");
        b_internet = new JButton("Jouer sur internet");

        p_container.setLayout(new GridBagLayout());
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
    }

    /**
     * 0 : Main Menu ChooseGame 1 : ChooseGrid 2 : PlaceFleet 3 : GamePanel
     */
    public void setBtnActions() {
        b_ia.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    c.m.setState(1);
                }
            }
        );
        b_2players.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    c.m.setState(2);
                }
            }
        );
        b_lan.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    c.m.setState(3);
                }
            }
        );
        b_internet.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    c.m.setState(1);
                }
            }
        );
    }

    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************
}
