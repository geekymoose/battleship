/*
 * Creation:    May 20, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;



/**
 * <p>SwitchPanel is used to display temporary panel in order to create a break. 
 * It is useful to create a break between 2 event for example</p>
 *
 * @since   May 20, 2015
 * @author  Constantin MASSON
 */
public class SwitchPanel extends ContainerPanel implements KeyListener, MouseListener{
    //**********************************************************************
    // Constants - Variables
    //**********************************************************************
    /**
     * In this mode, SwitchPanel wait user to press space to finish switch
     */
    public      static final int    MODE_SPACE_WAIT     = 1;
    
    /**
     * Wait for external end (User can leave this page itself)
     */
    public      static final int    MODE_WAIT           = 2;
    
    //Class variables
    private     JPanel              parent;
    private     JPanel              wrapper_center;
    private     JPanel              content;
    private     ArrayList<JPanel>   listSwitchedPanel;
    private     int                 mode;



    //**********************************************************************
    // Constructors - Initialization
    //**********************************************************************
    /**
     * Create a new SwitchPanel displaying a message
     * @param pParent       JPanel where to display switchPanel
     * @param pSwitchMode   switch mode 
     * @param pToSwitch     list of content to reset after break
     */
    public SwitchPanel(JPanel pParent, int pSwitchMode, JPanel ...pToSwitch){
        this.parent             = pParent;
        this.listSwitchedPanel  = new ArrayList();
        this.mode               = pSwitchMode;
        for(JPanel p : pToSwitch){
            this.listSwitchedPanel.add(p);
        }
        this.setFocusable(true);
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.initComponents();
    }

    private void initComponents(){
        this.wrapper_center     = new JPanel();
        this.content            = new JPanel();
        this                    .setLayout(new GridBagLayout());
        this.wrapper_center     .setLayout(new BoxLayout(this.wrapper_center, BoxLayout.Y_AXIS));
        this.wrapper_center     .setOpaque(false);

        //Add in center wrapper
        this.wrapper_center     .add(this.content);

        GridBagConstraints gbc  = new GridBagConstraints();
        this.add(this.wrapper_center, gbc);
    }


    //**********************************************************************
    // Functions
    //**********************************************************************
    /**
     * Start displaying this Panel. Remove grid (Radar and fleet) from 
     * current game and display page without information about players state
     */
    public void display(){
        for(JPanel p : this.listSwitchedPanel){
            this.parent.remove(p);
        }
        this.parent.revalidate();
        this.parent.repaint();
        this.requestFocusInWindow();
    }

    /**
     * Stop displaying this panel. Restore current game state with player 
     * turn data
     */
    public void stopSwitchPanel(){
        this.parent.remove(this);
        for(JPanel p : this.listSwitchedPanel){
            this.parent.add(p);
        }
        this.parent.revalidate();
        this.parent.repaint();
    }

    @Override
    public void loadUI(){
        this.reloadUI();
    }

    @Override
    public void reloadUI(){
    }


    //**********************************************************************
    // Getters - Setters
    //**********************************************************************
    /**
     * Set content of the current switchPanel. Do nothing if null
     * @param pPanel panel to display in the switch panel 
     */
    public void setContent(JPanel pPanel){
        if(pPanel != null){
            this.content = pPanel;
        }
    }


    //**********************************************************************
    // KeyListener and MouseListener
    //**********************************************************************
    @Override
    public void keyTyped(KeyEvent e){
    }

    @Override
    public void keyPressed(KeyEvent e){
    }

    @Override
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_SPACE) {
            this.stopSwitchPanel();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e){
        this.requestFocusInWindow();
    }

    @Override
    public void mousePressed(MouseEvent e){
        this.requestFocusInWindow();
    }

    @Override
    public void mouseReleased(MouseEvent e){
        this.requestFocusInWindow();
    }

    @Override
    public void mouseEntered(MouseEvent e){
        this.requestFocusInWindow();
    }

    @Override
    public void mouseExited(MouseEvent e){
        this.requestFocusInWindow(false);
    }
}