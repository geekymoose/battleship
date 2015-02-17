/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views;

import java.awt.BorderLayout;
import javax.swing.JPanel;



/**
 * Contient Radar, fleet, score, chat (Panel englobant)
 * @date    11 févr. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class GamePanel extends JPanel implements CurrentView{
    JPanel centerPane;
    InformationPanel p_info;
    FleetPanel p_fleet;
    RadarPanel p_radar;
    ChatPanel p_chat;
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public GamePanel(){
        this.setLayout(new BorderLayout());
        
        initComponents();
        this.add(p_info, BorderLayout.WEST);
        this.add(centerPane, BorderLayout.CENTER);
        this.add(p_chat, BorderLayout.EAST);
        
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    private void initComponents(){
        centerPane = new JPanel();
        centerPane.setLayout(new BorderLayout());
        
        p_info = new InformationPanel();
        p_fleet = new FleetPanel();
        p_radar = new RadarPanel();
        p_chat = new ChatPanel();
        
        centerPane.add(p_radar, BorderLayout.NORTH);
        centerPane.add(p_fleet, BorderLayout.SOUTH);
    }
    
    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************

}
