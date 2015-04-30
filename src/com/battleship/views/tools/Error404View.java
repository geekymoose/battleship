/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.tools;

import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import java.awt.BorderLayout;
import javax.swing.JLabel;





/**
 * <h1>Error404View</h1>
 * <p>
 * public class Error404View<br/>
 * extends PagePanel
 * </p>
 * <p>Page displayed if error</p>
 *
 * @date    Mar 13, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class Error404View extends PagePanel{
    //**************************************************************************
    // Variables - Constants
    //**************************************************************************
    private     final String        msg;
    private     final JLabel        l_message;
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create error 404 page with specific error message from id constants
     * @param pFrame    Frame containing this panel
     * @param pMsg      message to display
     * @throws ExecError
     */
    public Error404View(WindowFrame pFrame, String pMsg) throws ExecError{
        super(pFrame);
        this.msg = pMsg;
        this.setLayout(new BorderLayout());
        this.l_message = new JLabel(pMsg);
        this.add(this.l_message, BorderLayout.CENTER);
        DebugTrack.showErrMsg("Page Error404 with message: "+this.msg);
    }
    
    @Override
    public void initPage(){
    }
    
    @Override
    protected void goNextPage(){
        //Not used
    }
    
    @Override
    protected void goPreviousPage(){
        //Not used
    }
    
    @Override
    public void loadUI(){
        //Not used
    }

    @Override
    public void reloadUI(){
        //Not used
    }
}
