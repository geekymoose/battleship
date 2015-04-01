/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.constants.GameConstants;
import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import com.battleship.models.game.Session;
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
import javax.swing.AbstractButton;
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
public class ChooseGamePanel extends PagePanel implements GameConstants{
    private     JPanel                  p_container;
    private     GridBagConstraints      gc;
    
    //Buttons
    private     AbstractButton          b_ia;
    private     AbstractButton          b_2players;
    private     AbstractButton          b_lan;
    private     AbstractButton          b_internet;
        
    
    
    
    
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
    }
    
    private void initComponents() {
        this.p_container= new JPanel();
        this.gc         = new GridBagConstraints();
        this            .setLayout(new BorderLayout());
        this.p_container.setLayout(new GridBagLayout());
        this.p_container.setOpaque(false);
        
        //Create buttons 
        b_ia            = new ZozoDecorator(new ImgButton(407100, 407200, 407300));
        b_2players      = new ZozoDecorator(new ImgButton(408100, 408200, 408300));
        b_lan           = new ZozoDecorator(new ImgButton(409100, 409200, 409300));
        b_internet      = new ZozoDecorator(new ImgButton(410100, 410200, 410300));

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
                    frame.rooting(Config.getRootsValues("config"), null);
                }
            }
        );
        b_2players.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DebugTrack.showExecMsg("Game mode : V2");
                    //To do later
                }
            }
        );
        b_lan.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DebugTrack.showExecMsg("Game mode : LAN");
                    //To do later
                }
            }
        );
        b_internet.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DebugTrack.showExecMsg("Game mode : Internet");
                    //To do later
                }
            }
        );
    }//End setBtnActions
    
    @Override
    protected void goNextPage(){
        //Not used
    }

    @Override
    protected void goPreviousPage(){
        //Not used
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = ThemeManager.getTheme().getImg(411100);
        g.drawImage(img,0,0, this.getWidth(), this.getHeight(), this);
    }
}
