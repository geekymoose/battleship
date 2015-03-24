/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;


import com.battleship.views.tools.*;
import com.battleship.constants.GraphicalConstants;
import com.battleship.constants.Roots;
import com.battleship.controllers.SwingFactory;
import com.battleship.exceptions.ExecError;
import com.battleship.models.game.Session;
import javax.swing.JFrame;
import javax.swing.JPanel;






/**
 * <h1></h1>
 * 
 * 
 * @date    Feb 11. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class ApplicationFrame extends JFrame implements GraphicalConstants, 
                                                        WindowFrame {
    //**************************************************************************
    // Variables and Constants
    //**************************************************************************
    /**
     * This is the content displayed at the moment by the application. Application 
     * is only a Frame container for a page to display. mainContent is this page.
     * @var mainContent
     */
    private     PagePanel       p_mainContent;
    
    
    
    
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    /**
     * Create a new Application Frame
     * @throws ExecError
     */
    public ApplicationFrame() throws ExecError {
        this.setTitle(FRAME_TITLE);
        this.setSize(FRAME_SIZE_L, FRAME_SIZE_H);
        this.setAlwaysOnTop(false);
        //this.setResizable(false);
        this.setResizable(true); //DEBUG
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.initComponents();
    }
    
    /*
     * Initialize all components
     * Create the new Session, it is linked with user currently using this app 
     * When the app start, user is only
     */
    private void initComponents() throws ExecError{
        Session         .createSession();
        ThemeManager    .createThemeManager();
        this.rooting(Roots.CHOOSE_GAME, null);
        this.getContentPane().add((JPanel)p_mainContent);
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    @Override
    public void rooting(int path, Object param){
        this.getContentPane().removeAll();
        
        //Get the requier page
        try{
            switch(path){
                case Roots.CHOOSE_GAME:
                    this.p_mainContent = new ChooseGamePanel(this);
                    break;
                    
                case Roots.CONFIG:
                    this.p_mainContent = SwingFactory.loadConfigGame(this);
                    break;
                    
                case Roots.PLACE_BOATS:
                    this.p_mainContent = SwingFactory.loadPlaceBoats(this);
                    break;
                    
                case Roots.GAME:
                    this.p_mainContent  = SwingFactory.loadGame(this);
                    break;
                    
                default:
                    throw new ExecError(404); //Page not found
            }
        } catch(ExecError ex){
            try {
                this.p_mainContent = new Error404View(this, ex.getMessage());
            } catch(ExecError ex1) {
                //Should never happen
            }
        }
        
        //Actualize components
        this.getContentPane().add((JPanel)this.p_mainContent);
        this.getContentPane().revalidate();
        this.pack();
        this.setLocationRelativeTo(null);
    }
}


