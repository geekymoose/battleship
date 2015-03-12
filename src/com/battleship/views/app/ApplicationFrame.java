/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;


import com.battleship.views.tools.ApplicationView;
import com.battleship.views.tools.ViewPage;
import com.battleship.constants.GraphicalConstants;
import com.battleship.constants.Roots;
import com.battleship.exceptions.ExecError;
import com.battleship.main.Session;
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
                                                        ApplicationView {
    //**************************************************************************
    // Variables and Constants
    //**************************************************************************
    /**
     * This is the content displayed at the moment by the application. Application 
     * is only a Frame container for a page to display. mainContent is this page.
     * @var mainContent
     */
    private     ViewPage        p_mainContent;
    
    /**
     * Save user data. When user start the game, a empty session is created (No data) 
     * Depending of the game mode (AI / 2v2 / LAN / Internet), the session is 
     * set with data (For example, it save current game type and recover 
     * weapons user have got)
     */
    private     Session  session; //User session
    
    
    
    
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    /**
     * Create a new Application Frame
     */
    public ApplicationFrame() {
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
    private void initComponents(){
        this.p_mainContent  = new ChooseGamePanel(this);
        this.session        = new Session();
        this.getContentPane().add((JPanel)p_mainContent);
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Return the session for current Application
     * @return Session used by this application
     */
    public Session getSession(){
        return this.session;
    }
    
    @Override
    public void rooting(int path){
        this.getContentPane().removeAll();
        
        //Get the requier page
        try{
            switch(path){
                case Roots.CONFIG:
                    this.p_mainContent = new GameConfigPanel(this);
                    break;
                case Roots.CHOOSE_GAME:
                    this.p_mainContent = new ChooseGamePanel(this);
                    break;
                case Roots.PLACE_BOATS:
                    this.p_mainContent = new PlaceBoatsPanel(this);
                    break;
                default:
                    throw new ExecError(404); //Page not found
            }
        } catch(ExecError ex){
            this.p_mainContent = new Error404View(ex.getIdError());
        }
        
        //Actualize components
        this.getContentPane().add((JPanel)this.p_mainContent);
        this.getContentPane().revalidate();
        this.pack();
        this.setLocationRelativeTo(null);
    }
}


