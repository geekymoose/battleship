/* 
 * Creation : 17 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.main;

import com.battleship.exceptions.ExecError;
import com.battleship.views.app.ApplicationFrame;
import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



/**
 * 
 * @date    Feb 17, 2015
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
                ApplicationFrame game;
                try {
                    game = new ApplicationFrame();
                    game.setVisible(true);
                } catch(ExecError ex) {
                    DebugTrack.showErrMsg("Unable to start program");
                    JOptionPane opt = new JOptionPane();
                    opt.showMessageDialog(null, "Unable to start the program. "
                                                +"\nError message : "+ex.getMessage(), 
                                                "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
