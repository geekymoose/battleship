/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views;

import com.battleship.constants.GraphicalConstants;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
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

    private JPanel p_weapons;

    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public InformationPanel() {
        this.setLayout(new GridLayout(8, 1));
        initComponents();
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    private void initComponents() {
        l_title = new JLabel(INFORMATION_TITLE);
        l_scoreTitle = new JLabel("Score");
        l_score = new JLabel("0");
        l_weapons = new JLabel("Weapons");
        ta_info = new JTextArea();
        p_weapons = new JPanel();
        setupTextArea();
    }

    private void addEachComponents() {
        this.add(l_title);
        this.add(ta_info);
        this.add(l_scoreTitle);
        this.add(l_score);
        this.add(l_weapons);
        this.add(p_weapons);
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

    private void addEachWeapons() {
        //p_weapons.add(this);
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
