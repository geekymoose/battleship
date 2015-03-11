/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.views.interfaces.ApplicationView;
import com.battleship.views.interfaces.CurrentView;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;



/**
 * <h1></h1>
 * 
 * @date    11 févr. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class ConfigGamePanel extends JPanel implements CurrentView{
    private     ApplicationView     parent;
    private     JPanel              p_buttons;
    private     JPanel              p_validate;
    private     JPanel              p_center;
    
    private     JLabel              l_indication;
    private     JLabel              l_imgSquare;
    private     JLabel              l_imgHexa;
    
    private     JButton             b_square;
    private     JButton             b_hexa;
    private     JButton             b_validate;
    private     JButton             b_back;
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    /**
     * 
     * @param pParent 
     */
    public ConfigGamePanel(ApplicationView pParent){
        this.parent = pParent;
        initComponents();
    }
    
    
    
    
    
    //**************************************************************************
    // METHODS
    //**************************************************************************
    
    private void initComponents(){
        this.p_buttons  = new JPanel();
        this.p_validate = new JPanel();
        this.p_center   = new JPanel();
        
        this            .setLayout(new BorderLayout());
        p_buttons       .setLayout(new BorderLayout());
        p_validate      .setLayout(new BorderLayout());
        p_center        .setLayout(new GridLayout(1,2));
        
        l_indication    = new JLabel("Choose your kind of grid please");
        b_validate      = new JButton("Validate");
        b_back          = new JButton("Step back");
        b_square        = new JButton("Square");
        b_hexa          = new JButton("Hexagonal");
        
        b_hexa.setEnabled(false);
        
        p_buttons       .add(l_indication, BorderLayout.NORTH);
        p_center        .add(b_square);
        p_center        .add(b_hexa);
        p_buttons       .add(p_center, BorderLayout.CENTER);
        
        p_validate      .add(b_back, BorderLayout.CENTER);
        p_validate      .add(b_validate, BorderLayout.CENTER);
        
        this.add(p_buttons, BorderLayout.NORTH);
        this.add(p_validate, BorderLayout.SOUTH);
    }
}
