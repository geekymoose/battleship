/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.constants.GameConstants;
import com.battleship.controllers.PlaceBoatsController;
import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import com.battleship.asset.Session;
import com.battleship.observers.ObservableModel;
import com.battleship.observers.ObserverModel;
import com.battleship.uibutton.ImgButton;
import com.battleship.uibutton.ZozoDecorator;
import com.battleship.asset.Config;
import com.battleship.asset.SwingFactory;
import com.battleship.asset.ThemeManager;
import com.battleship.models.game.FleetGridModel;
import com.battleship.models.game.GameConfigModel;
import com.battleship.models.game.PlaceBoatsModel;
import com.battleship.views.tools.PagePanel;
import com.battleship.views.tools.UiDialog;
import com.battleship.views.tools.WindowFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



/**
 *
 * 
 * @date    Feb 11. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class PlaceBoatsPanel extends PagePanel implements ObserverModel, GameConstants{
    private     final PlaceBoatsController      controller;
    
    private     ContainerPanel                  p_container;
    private     DockPanel                       p_dock;
    private     JPanel                          p_bigContainer;
    
    private     JPanel                          p_buttonPanel;
    private     AbstractButton                  b_valide;
    private     AbstractButton                  b_reset;
    private     AbstractButton                  b_back;
    
    private     Dimension                       dimBox;
    private     GridBagConstraints              gbc;
    
    private     PlayerFleetPanel                gridPanel;
    
    
    
    
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    /**
     * 
     * @param pFrame        Frame containing this panel
     * @param pController   Controller for this page
     * @throws ExecError error if unable to create this panel
     */
    public PlaceBoatsPanel(WindowFrame pFrame, PlaceBoatsController pController) throws ExecError{
        super(pFrame);
        if(pController==null){
            throw new ExecError();
        }
        this.controller     = pController;
        this.dimBox         =  Config.getDimValues_dim("dim-placeboats-boxmap");
        this.setPreferredSize(Config.getDimValues_dim("default-dim-appframe"));
        this.initComponents();
    }
    
    /*
     * Create all components 
     * @throws ExecError 
     */
    private void initComponents() throws ExecError{
        this                .setLayout(new BorderLayout());
        this.p_buttonPanel  = new JPanel();
        this.b_valide       = new ZozoDecorator(new ImgButton(406100, 406200, 406300));
        this.b_reset        = new ZozoDecorator(new ImgButton(405100, 405200, 405300));
        this.b_back         = new ZozoDecorator(new ImgButton(404100, 404200, 404300));
        
        p_container         = new ContainerPanel();
        p_bigContainer      = new JPanel();
        
        this.gridPanel      = new PlayerFleetPanel(this); 
        this.p_dock         = new DockPanel(this);
        
        gbc                 = new GridBagConstraints();
        p_bigContainer      .setLayout(new GridBagLayout());
        this.p_buttonPanel  .setLayout(new FlowLayout());
        this.p_buttonPanel  .add(b_back);
        this.p_buttonPanel  .add(b_reset);
        this.p_buttonPanel  .add(b_valide);
        
        p_container         .setLayout(new BorderLayout());
        
        p_buttonPanel       .setOpaque(false);
        p_container         .setOpaque(false);
        p_bigContainer      .setOpaque(false);
        p_dock              .setOpaque(false);
        gridPanel           .setOpaque(false);
        
        p_container         .add(gridPanel, BorderLayout.CENTER);
        p_container         .add(p_dock, BorderLayout.EAST);
        p_container         .add(p_buttonPanel, BorderLayout.SOUTH);
        p_bigContainer      .add(p_container, gbc);
        
        this                .add(p_bigContainer, BorderLayout.CENTER);
        this                .setBtnActions();
    }
    
    /*
     * Create actionListener for the buttons
     * Each button call the new JPanel for this application (Parent)
     */
    private void setBtnActions() {
        b_valide.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DebugTrack.showExecMsg("Valid Place Boats");
                    goNextPage();
                }
            }
        );
        
        b_reset.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DebugTrack.showExecMsg("Reset Place Boats");
                    controller.resetFleetGrid();
                }
            }
        );
        
        b_back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DebugTrack.showExecMsg("Return before Place Boats");
                    goPreviousPage();
                }
            }
        );
    }//End setBtnActions
    
    
    
    
    
    //**************************************************************************
    // Functions Override
    //**************************************************************************
    @Override
    public void initPage() throws ExecError{
        this.controller.initPage();
        GameConfigModel conf = this.controller.getGameConfig();
        FleetGridModel gridPlayer1 = conf.getPlayers()[0].getFleet();
        FleetGridModel gridPlayer2 = conf.getPlayers()[1].getFleet();
        
        DebugTrack.showObjectToString(gridPlayer1);
        DebugTrack.showObjectToString(gridPlayer2);
        
        GridPanel fleetPlayer1 = SwingFactory.loadGridPanel(this.gridPanel, gridPlayer1, dimBox, Color.WHITE);
        GridPanel fleetPlayer2 = SwingFactory.loadGridPanel(this.gridPanel, gridPlayer2, dimBox, Color.WHITE);
        
        fleetPlayer1.getGridCursor().setClickPlaceBoat();
        fleetPlayer2.getGridCursor().setClickPlaceBoat();
        
        this.gridPanel.setFleetGrids(fleetPlayer1, fleetPlayer2);
        this.gridPanel.switchGrid(0);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = ThemeManager.getTheme().getImg(415000);
        g.drawImage(img,0,0, this.getWidth(), this.getHeight(), this);
    }
    
    
    @Override
    public void update(ObservableModel o, Object arg){
        PlaceBoatsModel m   = (PlaceBoatsModel)o;
    }
    
    
    
    
    
    //**************************************************************************
    // Functions Rooting
    //**************************************************************************
    @Override
    protected void goNextPage(){
        int mode = Session.getGameMode();
        switch(mode){
            case MODE_AI:
                break;
            case MODE_V2:
                //-1 invalid grid, 0 last player reached, 1 next player
                switch(this.controller.switchPlayer()){
                    case -1:
                        UiDialog.showWarning("beware!", "Some boats are missing on the grid!!\n"
                                + "Do you want to figth with less boats than your enemy? "
                                + "Woow! We've got a warrior here!");
                        break;
                    case 0:
                        this.frame.rooting(Config.getRootsValues("game"), true);
                        break;
                    case 1:
                        this.gridPanel.switchGrid(1);
                        UiDialog.showWarning("Next Player", 
                                             "Beware! Next player has to place his boats!!");
                        break;
                }
                break;
            case MODE_LAN:
                break;
            case MODE_INTERNET:
                break;
        }
    }
    
    @Override
    protected void goPreviousPage(){
        String msg      = "Are you sure you want to go back? Current configuration could be lost";
        String title    = "Warning";
        int choice = UiDialog.showYesNoWarning(title, msg);
        if(choice == JOptionPane.OK_OPTION){
            int mode = Session.getGameMode();
            switch(mode){
                case MODE_AI:
                    frame.rooting(Config.getRootsValues("config"), null);
                    break;
                case MODE_V2:
                    frame.rooting(Config.getRootsValues("config"), null);
                    break;
                case MODE_LAN:
                    break;
                case MODE_INTERNET:
                    break;
            }
        }
    }
}
