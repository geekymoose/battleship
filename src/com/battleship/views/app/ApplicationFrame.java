/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;


import com.battleship.asset.Config;
import com.battleship.views.tools.Error404View;
import com.battleship.views.tools.*;
import com.battleship.asset.SwingFactory;
import com.battleship.exceptions.ExecError;
import javax.swing.JFrame;
import javax.swing.JPanel;






/**
 * <h1>ApplicationFrame</h1>
 * <p>
 * public class ApplicationFrame<br/>
 * extends JFrame<br/>
 * implements WindowFrame
 * </p>
 * 
 * 
 * @since   Feb 11. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class ApplicationFrame extends JFrame implements WindowFrame {
    //**************************************************************************
    // Variables and Constants
    //**************************************************************************
    public  static final int    CHOOSE_GAME     = 101;
    public  static final int    CONFIG          = 102;
    public  static final int    PLACE_BOATS     = 103;
    public  static final int    GAME            = 104;
    public  static final int    BAZAAR          = 105;
    public  static final int    NETWORK         = 106;
    public  static final int    LIST_GAMES      = 107; 
    public  static final int    WAITING_ROOM    = 108; 
    
    private final int           FRAME_SIZE_W    = Config.getDimValues_int("frame-size-width");
    private final int           FRAME_SIZE_H    = Config.getDimValues_int("frame-size-height");
    
    
    //Variables 
    /**
     * This is the content displayed at the moment by the application. Application 
     * is only a Frame container for a page to display. mainContent is this page.
     * @var mainContent
     */
    private     PagePanel   p_mainContent;
    
    
    
    
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    /**
     * Create a new Application Frame
     * @throws ExecError thrown if unable to create ApplicationFrame
     */
    public ApplicationFrame() throws ExecError {
        this.setTitle(Config.getGameValues_str("frame-title"));
        this.setSize(FRAME_SIZE_W, FRAME_SIZE_H);
        this.setAlwaysOnTop(false);
        //this.setResizable(false);
        this.setResizable(true); //DEBUG
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.initComponents();
    }
    
    /*
     * Initialize all components
     * Create the new Session, it is linked with user currently using this app 
     * When the app start, user is only
     */
    private void initComponents() throws ExecError{
        this.rooting(this.CHOOSE_GAME, null);
        this.getContentPane().add((JPanel)p_mainContent);
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    @Override
    public void rooting(int path, Object param){
        //Note this rooting mode, with XML file, was experimental
        this.getContentPane().removeAll();
        boolean reset = false;
        if (param instanceof Boolean && (Boolean)param == true){
            reset = true;
        }
        //Get the requier page
        try{
            switch(path){
                case CHOOSE_GAME:
                    this.p_mainContent = new ChooseGamePanel(this);
                    break;
                case CONFIG:
                    this.p_mainContent = SwingFactory.loadConfigGame(this, reset);
                    break;
                case PLACE_BOATS:
                    this.p_mainContent = SwingFactory.loadPlaceBoats(this, reset);
                    break;
                case GAME:
                    this.p_mainContent = SwingFactory.loadGame(this, reset);
                    break;
                case BAZAAR:
                    this.p_mainContent = new BazaarPanel(this);
                    break;
                case NETWORK:
                    this.p_mainContent = new ConnectPanel(this);
                    break;
                case LIST_GAMES:
                    this.p_mainContent = new ListGamesPanel(this);
                    break;
                case WAITING_ROOM:
                    this.p_mainContent = new WaitingRoom(this);
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
        this.p_mainContent.repaint();
        this.p_mainContent.reloadUI();
        this.pack();
        this.setLocationRelativeTo(null);
    }
}


