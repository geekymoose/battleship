/* 
 * Creation : 17 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.main;

import com.battleship.views.ApplicationFrame;
import java.awt.EventQueue;



/**
 * 
 * @date    17 févr. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class Main {
    /**
     * Launch the program
     * @param args 
     */
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ApplicationFrame game = new ApplicationFrame();
                game.pack();
                game.setLocationRelativeTo(null);
                game.setVisible(true);
            }
        });
    }
}
