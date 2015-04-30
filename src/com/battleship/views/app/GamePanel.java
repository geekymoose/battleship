/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.asset.CheatCode;
import com.battleship.asset.Config;
import com.battleship.asset.SwingFactory;
import com.battleship.asset.ThemeManager;
import com.battleship.controllers.GameController;
import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import com.battleship.models.game.FleetGridModel;
import com.battleship.models.game.GameConfigModel;
import com.battleship.observers.ObservableModel;
import com.battleship.observers.ObserverModel;
import com.battleship.views.tools.PagePanel;
import com.battleship.views.tools.WindowFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.JPanel;



/**
 * <h1>GamePanel</h1>
 * <p>
 * public class GamePanel<br/>
 * extends PagePanel
 * </p>
 * 
 * @date    11 févr. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class GamePanel extends PagePanel implements ObserverModel{
    //**************************************************************************
    // Variables - Constants
    //**************************************************************************
    private     final GameController    controller;
    private     JPanel                  p_centerPane;
    private     JPanel                  p_bigCont;
    
    private     Dimension               dimBoxFleet;
    private     Dimension               dimBoxRadar;
    
    private     InformationPanel        p_info;
    private     PlayerFleetPanel        p_fleet;
    private     RadarPanel              p_radar;
    private     ChatPanel               p_chat;
    private     HeadBar                 p_headbar;
    private     GridBagConstraints      gc;
    
    
    
    
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    /**
     * Create a new Game panel
     * @param pFrame        frame containing this panel
     * @param pController   controller for this page
     * @throws ExecError error if unable to create this panel
     */
    public GamePanel(WindowFrame pFrame, GameController pController) throws ExecError{
        super(pFrame);
        if(pController == null){
            throw new ExecError();
        }
        this.controller     = pController;
        this.dimBoxFleet    = Config.getDimValues_dim("dim-playerfleet-boxmap");
        this.dimBoxRadar    = Config.getDimValues_dim("dim-radar-boxmap");
        
        this.initComponents();
        this.setPreferredSize(Config.getDimValues_dim("default-dim-appframe"));
    }
    
    
    /*
     * Initialize all components
     */
    private void initComponents() throws ExecError{
        this.gc         = new GridBagConstraints();
        this.p_headbar  = new HeadBar();
        p_centerPane    = new ContainerPanel();
        p_info          = new InformationPanel();
        p_fleet         = new PlayerFleetPanel(this);
        p_radar         = new RadarPanel(this);
        p_chat          = new ChatPanel(p_radar);
        p_bigCont       = new JPanel();
        
        p_centerPane    .setOpaque(false);
        p_fleet         .setOpaque(false);
        p_radar         .setOpaque(false);
        p_bigCont       .setOpaque(false);
        
        this            .setLayout(new BorderLayout());
        p_centerPane    .setLayout(new GridBagLayout()); 
        p_bigCont       .setLayout(new GridBagLayout());
        
        //Put the 2 panels into the boerderlayout's center
        gc.fill         = GridBagConstraints.HORIZONTAL;
        gc.insets       = new Insets(10, 10, 0, 10);
        
        //Place radar
        gc.gridx        = 0;
        gc.gridy        = 0;
        p_centerPane    .add(p_radar, gc);
        
        //Place player fleet grid
        gc.gridx        = 1;
        gc.gridy        = 0;
        p_centerPane    .add(p_fleet, gc);
        
        
        this.p_bigCont.add(p_centerPane);
        this.add(p_headbar, BorderLayout.NORTH);
        this.add(p_chat,    BorderLayout.EAST);
        this.add(p_info,    BorderLayout.SOUTH);
        this.add(p_bigCont, BorderLayout.CENTER);
        
        CheatCode.setData(p_radar);
    }
    
    
    @Override
    public void initPage() throws ExecError{
        GameConfigModel conf        = this.controller.getGameConfig();
        FleetGridModel  gridPlayer1 = conf.getPlayers()[0].getFleet();
        FleetGridModel  gridPlayer2 = conf.getPlayers()[1].getFleet();
        
        DebugTrack.showObjectToString(gridPlayer1);
        DebugTrack.showObjectToString(gridPlayer2);
        
        GridPanel fleetPlayer1 = SwingFactory.loadGridPanel(this.p_fleet, gridPlayer1, dimBoxFleet);
        GridPanel fleetPlayer2 = SwingFactory.loadGridPanel(this.p_fleet, gridPlayer2, dimBoxFleet);
        
        GridPanel radarPlayer1 = SwingFactory.loadGridPanel(this.p_radar, gridPlayer1, dimBoxRadar);
        GridPanel radarPlayer2 = SwingFactory.loadGridPanel(this.p_radar, gridPlayer2, dimBoxRadar);
        
        this.p_fleet.initGrids(fleetPlayer1, fleetPlayer2, conf.getFirstPlayerTurn());
        this.p_radar.initGrids(radarPlayer1, radarPlayer2, conf.getSecondPlayerTurn(),
                                                           conf.getFirstPlayerTurn());
        
        //TMP DEBUG
        fleetPlayer1.getGridCursor().setClickNoAction();
        fleetPlayer2.getGridCursor().setClickNoAction();
        radarPlayer1.getGridCursor().setClickShoot();
        radarPlayer2.getGridCursor().setClickShoot();
        this.repaint();
    }
    
    
    
    
    
    
    //**************************************************************************
    // METHODS
    //**************************************************************************
    public void switchTurn(){
        
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = ThemeManager.getTheme().getImg(417000);
        g.drawImage(img,0,0, this.getWidth(), this.getHeight(), this);
    }

    
    @Override
    public void update(ObservableModel o, Object arg){
    
    }
    
    
    
    
    
    //**************************************************************************
    // UiElement Functions
    //**************************************************************************
    @Override
    public void loadUI(){
    
    }

    @Override
    public void reloadUI(){
        
    }
    
    
    
    
    
    //**************************************************************************
    // Rooting Functions
    //**************************************************************************
    @Override
    protected void goNextPage(){
    }
    
    @Override
    protected void goPreviousPage(){
    }
    
    
    
    
    
    
    //**************************************************************************
    // getters - Setters 
    //**************************************************************************
    public GameController getController(){
        return this.controller;
    }
}
