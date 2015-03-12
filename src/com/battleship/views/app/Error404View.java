/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.views.tools.ViewPage;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;





/**
 * <h1>Error404View</h1>
 * <p>public class Error404View</p>
 * <p>Page displayed if error</p>
 *
 * @date    Mar 13, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class Error404View extends JPanel implements ViewPage{
    //**************************************************************************
    // Variables - Constants
    //**************************************************************************
    private     final int   idError;
    private     String      msg;
    private     JLabel      l_message;
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create error 404 page with specific error message from id constants
     * @param pIdError id for error message
     */
    public Error404View(int pIdError){
        this.idError = pIdError;
        this.initComponents();
    }
    
    /*
     * Init comp
     */
    private void initComponents(){
        this.setLayout(new BorderLayout());
        this.l_message = new JLabel();
        
        //Create error message
        switch (idError){
            case 404:
                this.msg = "Page not found :/";
                break;
            default:
                this.msg = "Unkown error";
                break;
        }
        
        this.l_message.setText(msg);
        this.add(this.l_message, BorderLayout.CENTER);
    }
}
