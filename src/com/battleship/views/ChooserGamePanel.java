/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views;

import javax.swing.JButton;
import javax.swing.JPanel;



/**
 * Choix au début du style de partie 1v1 ou en ligne etc ...
 * @date    11 févr. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class ChooserGamePanel extends JPanel{
    JButton b_ia;
    JButton b_2players;
    JButton b_lan;
    JButton b_internet;
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public ChooserGamePanel(){
        this.setLayout(null);
        
        b_ia            = new JButton("Jouer contre l'IA");
        b_2players      = new JButton("Jouer à deux joueurs");
        b_lan           = new JButton("Jouer en réseau local");
        b_internet      = new JButton("Jouer sur internet");
        
        this.add(b_ia);
        this.add(b_2players);
        this.add(b_lan);
        this.add(b_internet);
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    
    public void initButtons(){
        
    }
    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************

}
