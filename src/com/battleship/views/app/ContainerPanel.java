/* 
 * Creation : 3 avr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.views.tools.ThemeManager;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;



/**
 * @date    3 avr. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class ContainerPanel extends JPanel{
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public ContainerPanel(){

    }

    //**************************************************************************
    // METHODS
    //**************************************************************************

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = ThemeManager.getTheme().getImg(416000);
        g.drawImage(img,0,0, this.getWidth(), this.getHeight(), this);
    }
    
    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************

}
