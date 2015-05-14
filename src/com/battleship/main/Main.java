/* 
 * Creation : 17 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.main;

import com.battleship.exceptions.ExecError;
import com.battleship.views.app.ApplicationFrame;
import com.battleship.asset.Config;
import com.battleship.views.tools.UiDialog;
import java.awt.EventQueue;



/**
 * 
 * @since   Feb 17, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class Main {
    
    /**
     * Launch the program
     * @param args program parameters 
     */
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Config.createConfig();
                    ApplicationFrame ap = new ApplicationFrame();
                    ap.pack();
                    ap.setLocationRelativeTo(null);
                    ap.setVisible(true);
                } catch(ExecError ex) {
                    DebugTrack.showErrMsg("Unable to start program");
                    UiDialog.showError("Error", "Unable to start the program! \n"
                                                + "Error message : "+ex.getMessage()
                                                +" Error");
                }
            }
        }); //And Runnable creation
    }
}
