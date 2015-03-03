/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views;

import com.battleship.constants.GraphicalConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Panel qui contient les armes / et infos du joueur
 *
 * @date 11 févr. 2015
 * @author Constantin MASSON
 * @author Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class InformationPanel extends JPanel implements GraphicalConstants, Observer {

    private JTextArea ta_info;
    private JScrollPane sp_scroll;

    private JLabel l_title;
    private JLabel l_scoreTitle;
    private JLabel l_score;
    private JLabel l_weapons;

    private JPanel p_west;
    private JPanel p_center;
    private JPanel p_east;
    
    private JPanel p_container;
    
    private JButton b_w1;
    
    private GridBagConstraints gc = new GridBagConstraints();

    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public InformationPanel() {
        initComponents();
        addEachComponents();
        //setSizes();
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    private void initComponents() {
        this.setLayout(new BorderLayout());
        l_title = new JLabel(INFORMATION_TITLE);
        l_scoreTitle = new JLabel("Score : ");
        l_score = new JLabel("0");
        l_weapons = new JLabel("Weapons");
        ta_info = new JTextArea();
        b_w1 = new JButton("W1");
        p_west = new JPanel();
        p_east = new JPanel();
        p_center = new JPanel();
        
        p_container = new JPanel();
        setupTextArea();
    }
    
    private void setSizes(){
        b_w1.setPreferredSize(new Dimension(50,50));
    }

    private void addEachComponents() {
        p_west.add(ta_info);
        p_center.add(l_scoreTitle);
        p_center.add(l_score);
        p_east.add(l_weapons);
        p_east.add(b_w1);
        
        this.add(p_west, BorderLayout.WEST);
        this.add(p_center, BorderLayout.CENTER);
        this.add(p_east, BorderLayout.EAST);
        
    }

    private void setupTextArea() {
        ta_info = new JTextArea();
        ta_info.setLineWrap(true);
        ta_info.setEditable(false);
        sp_scroll = new JScrollPane(ta_info);
        sp_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public void printInfoMessage(String string) {
        ta_info.append(string);
        ta_info.setCaretPosition(ta_info.getText().length());
    }

    //**************************************************************************
    // PATTERN OBSERVER
    //**************************************************************************
    @Override
    public void update(Observable o, Object arg){
        // Met à jour le score, pour les infos on court-cricuite sans passer par l'oberver
        // via la methode printInfoMessage qui ajoute un message directement
    }
}
