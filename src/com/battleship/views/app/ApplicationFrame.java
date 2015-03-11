/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;


import com.battleship.views.interfaces.ApplicationView;
import com.battleship.views.interfaces.CurrentView;
import com.battleship.constants.GraphicalConstants;
import javax.swing.JFrame;
import javax.swing.JPanel;






/** !!!!!!!!!!!!!!!!!!!! Please write down JavaDoc here !!!!!!!!!!!!!!!!!!!!!!!!
 * <h1></h1>
 * 
 * 
 * @date    11 févr. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class ApplicationFrame extends JFrame implements GraphicalConstants, ApplicationView {
    private     CurrentView     mainContent;
    
    
    
    
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    /**
     * 
     */
    public ApplicationFrame() {
        this.setTitle(FRAME_TITLE);
        this.setSize(FRAME_SIZE_L, FRAME_SIZE_H);
        this.setAlwaysOnTop(false);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.initComponents();
    }
    
    /*
     * Initialize all components
     */
    private void initComponents(){
        this.mainContent = new ChooseGamePanel(this);
        this.getContentPane().add((JPanel)mainContent);
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    
    @Override
    public void displayChooseGamePanel(){
        this.getContentPane().removeAll();
        this.mainContent = new ChooseGamePanel(this);
        this.getContentPane().add((JPanel)this.mainContent);
        this.getContentPane().revalidate();
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    @Override
    public void displayConfigGamePanel(){
        this.getContentPane().removeAll();
        this.mainContent = new ConfigGamePanel(this);
        this.getContentPane().add((JPanel)this.mainContent);
        this.getContentPane().revalidate();
        this.pack();
        this.setLocationRelativeTo(null);
    }

}
