/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;



/**
 * @date    11 févr. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class ChooseGridPanel extends JPanel implements CurrentView{
    private JPanel p_buttons;
    private JPanel p_validate;
    private JLabel l_indication;
    private JLabel l_imgSquare;
    private JLabel l_imgHexa;
    private JButton b_square;
    private JButton b_hexa;
    private JButton b_validate;
    private JButton b_back;
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public ChooseGridPanel(){
        this.setLayout(new BorderLayout());
        initComponents();
        
        this.add(p_buttons, BorderLayout.NORTH);
        this.add(l_imgSquare, BorderLayout.CENTER);
        this.add(p_validate, BorderLayout.SOUTH);
    }
    

    //**************************************************************************
    // METHODS
    //**************************************************************************
    private void initComponents(){
        p_buttons.setLayout(new BorderLayout());
        p_validate.setLayout(new BorderLayout());
        
        l_indication = new JLabel("Choose your kind of grid please");
        b_validate = new JButton("Validate");
        b_back = new JButton("Step back");
        b_square = new JButton("Square");
        b_hexa = new JButton("Hexagonal");
        b_hexa.setEnabled(false);
        
        p_buttons.add(l_indication, BorderLayout.NORTH);
        p_buttons.add(b_square, BorderLayout.CENTER); // Si ne fonctionne pas rajoute un JPanel qui contient les deux élements
        p_buttons.add(b_hexa, BorderLayout.CENTER);
        
        p_validate.add(b_back, BorderLayout.CENTER);
        p_validate.add(b_validate, BorderLayout.CENTER);
    }
    
    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************

}
