/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;



/**
 * Contient Radar, fleet, score, chat (Panel englobant)
 * @date    11 févr. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class GamePanel extends JPanel implements CurrentView{
    Controller c;
    JPanel p_centerPane;
    
    InformationPanel p_info;
    FleetPanel p_fleet;
    RadarPanel p_radar;
    ChatPanel p_chat;
    HeadBar p_headbar = new HeadBar();
    GridBagConstraints gc = new GridBagConstraints();
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public GamePanel(Controller cParam){
        this.c = cParam;
        this.setLayout(new BorderLayout());
        initComponents();
        
        this.add(p_headbar, BorderLayout.NORTH);
        this.add(p_centerPane, BorderLayout.CENTER);
        this.add(p_chat, BorderLayout.EAST);
        this.add(p_info, BorderLayout.SOUTH);
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    private void initComponents(){
        p_centerPane = new JPanel();
        p_centerPane.setLayout(new GridBagLayout()); 
        p_centerPane.setBackground(Color.red);
        
        p_info = new InformationPanel();
        p_info.setBackground(Color.ORANGE);
        
        p_fleet = new FleetPanel();
        p_fleet.setBackground(Color.CYAN);
        
        p_radar = new RadarPanel();
        p_radar.setBackground(Color.DARK_GRAY);
        
        p_chat = new ChatPanel();
        p_chat.setBackground(Color.PINK);
        
        //Mettre les deux panels dans des center de borderlayout
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 10, 0, 10);
        gc.gridx = 0;
        gc.gridy = 0;
        p_centerPane.add(p_radar, gc);
        gc.gridx = 1;
        gc.gridy = 0;
        p_centerPane.add(p_fleet, gc);
    }
    
    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************

}
