/* 
 * Creation : 3 mars 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;





/**
 * 
 * @date    3 mars 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class HeadBar extends JPanel{
    JLabel l_title = new JLabel("BATTLESHIP");
    JLabel l_music = new JLabel("MUSIC");
    JLabel l_theme = new JLabel("THEME");
    JPanel p_logo = new JPanel();
    JPanel p_theme = new JPanel();
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public HeadBar(){
        this.setLayout(new BorderLayout());
        this.add(l_title, BorderLayout.WEST);
        p_theme.add(l_theme);
        p_theme.add(l_music);
        this.add(p_theme, BorderLayout.EAST);
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************

    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************

}
