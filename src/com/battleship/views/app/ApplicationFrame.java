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
 * @date    Feb 11. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class ApplicationFrame extends JFrame implements WindowFrame {
    //**************************************************************************
    // Variables and Constants
    //**************************************************************************
    //Constants -> load roots
    private     final int   CHOOSE_GAME     = Config.getRootsValues("choose-game");
    private     final int   CONFIG          = Config.getRootsValues("config");
    private     final int   PLACE_BOATS     = Config.getRootsValues("place-boats");
    private     final int   GAME            = Config.getRootsValues("game");
    private     final int   FRAME_SIZE_W    = Config.getDimValues_int("frame-size-width");
    private     final int   FRAME_SIZE_H    = Config.getDimValues_int("frame-size-height");
    
    
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
     * @throws ExecError
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
        this.getContentPane().removeAll();
        
        //Get the requier page
        try{
            if(path == this.CHOOSE_GAME){
                this.p_mainContent = new ChooseGamePanel(this);
                
            } else if(path == this.CONFIG){
                this.p_mainContent = SwingFactory.loadConfigGame(this);
                
            } else if(path == this.PLACE_BOATS){
                this.p_mainContent = SwingFactory.loadPlaceBoats(this);
                
            } else if(path == this.GAME){
                this.p_mainContent = SwingFactory.loadGame(this);
                
            } else {
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


