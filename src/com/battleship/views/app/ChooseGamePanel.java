/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.asset.ThemeManager;
import com.battleship.asset.Config;
import com.battleship.constants.GameConstants;
import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import com.battleship.asset.Session;
import com.battleship.uibutton.*;
import com.battleship.views.tools.*;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;





/**
 * <h1>ChooseGamePanel</h1>
 * <p>
 * public class ChooseGamePanel<br/>
 * extends PagePanel<br/>
 * implements GameConstants
 * </p>
 *
 * @date    Feb 11. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class ChooseGamePanel extends PagePanel implements GameConstants, UiElement{
    private     JPanel                  p_container;
    private     GridBagConstraints      gc;
    private     HeadBar                 p_hb;
    
    //Buttons
    private     UiButton                b_ia;
    private     UiButton                b_2players;
    private     UiButton                b_lan;
    private     UiButton                b_internet;
    
    //Images
    private     Image                   background;
        
    
    
    
    
    //**************************************************************************
    // CONSTRUCTOR - INITIALIZATION
    //**************************************************************************
    /**
     * Create a choose Game Panel
     * @param pFrame Frame containing this panel
     * @throws ExecError error if unable to create this panel
     */
    public ChooseGamePanel(WindowFrame pFrame) throws ExecError {
        super(pFrame);
        this.setPreferredSize(Config.getDimValues_dim("default-dim-appframe"));
        this.initComponents();
        this.loadUI();
    }
    
    private void initComponents() throws ExecError {
        this.p_container= new JPanel();
        this.gc         = new GridBagConstraints();
        this            .setLayout(new BorderLayout());
        this.p_container.setLayout(new GridBagLayout());
        this.p_container.setOpaque(false); //For background
        
        p_hb = new HeadBar(this);
        //Create buttons 
        b_ia            = new ZozoDecorator(new ImgButton(407100, 407200, 407300)).getUiButton();
        b_2players      = new ZozoDecorator(new ImgButton(408100, 408200, 408300)).getUiButton();
        b_lan           = new ZozoDecorator(new ImgButton(409100, 409200, 409300)).getUiButton();
        b_internet      = new ZozoDecorator(new ImgButton(410100, 410200, 410300)).getUiButton();

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 10, 0, 10);
        gc.gridx = 0;
        gc.gridy = 0;
        p_container.add(b_ia, gc); 

        gc.gridx = 0;
        gc.gridy = 1;
        p_container.add(b_2players, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        p_container.add(b_lan, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        p_container.add(b_internet, gc);
        
        this.setBtnActions();
        this.add(p_hb, BorderLayout.NORTH);
        this.add(p_container, BorderLayout.CENTER);
    }
    
    @Override
    public void initPage(){
        //Not used
    }
    
    /*
     * Create actionListener for the buttons
     * Each button call the new JPanel for this application (Parent)
     */
    private void setBtnActions() {
        b_ia.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DebugTrack.showExecMsg("Game mode : AI");
                    Session.setGameMode(MODE_AI);
                    frame.rooting(Config.getRootsValues("config"), true);
                }
            }
        );
        b_2players.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DebugTrack.showExecMsg("Game mode : V2");
                    Session.setGameMode(MODE_V2);
                    frame.rooting(Config.getRootsValues("config"), true);
                }
            }
        );
        b_lan.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DebugTrack.showExecMsg("Game mode : LAN");
                    Session.setGameMode(MODE_LAN);
                    //To do later
                }
            }
        );
        b_internet.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DebugTrack.showExecMsg("Game mode : Internet");
                    Session.setGameMode(MODE_INTERNET);
                    frame.rooting(Config.getRootsValues("bazaar"), true);
                }
            }
        );
    }//End setBtnActions
        
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.background, 0, 0, this.getWidth(), this.getHeight(), this);
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
        this            .reloadUI();
        this.b_ia       .reloadUI();
        this.b_2players .reloadUI();
        this.b_lan      .reloadUI();
        this.b_internet .reloadUI();
    }
    
    @Override
    public void reloadUI(){
        this.background = ThemeManager.getTheme().getImg(415000);
        this.p_hb.reloadUI();
        this.repaint();
    }
}
