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
import com.battleship.models.game.GameModel;
import com.battleship.models.game.Player;
import com.battleship.observers.ObservableModel;
import com.battleship.observers.ObserverModel;
import com.battleship.views.tools.PagePanel;
import com.battleship.views.tools.WindowFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
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
    
    private     SwitchPanel             switchPanel;
    
    //Image 
    private     Image                   img_background;
    
    
    
    
    
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
        this.p_headbar  = new HeadBar(this);
        p_centerPane    = new ContainerPanel();
        p_info          = new InformationPanel(this, this.controller);
        p_fleet         = new PlayerFleetPanel(this);
        p_radar         = new RadarPanel(this);
        p_chat          = new ChatPanel(p_radar);
        p_bigCont       = new JPanel();
        switchPanel     = new SwitchPanel();
        
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
        fleetPlayer1.getGridCursor().setClickNoArm();
        fleetPlayer2.getGridCursor().setClickNoArm();
        radarPlayer1.getGridCursor().setClickShoot();
        radarPlayer2.getGridCursor().setClickShoot();
        
        conf.getPlayers()[0].addObserver(this.p_radar);
        conf.getPlayers()[1].addObserver(this.p_radar);
        
        //Change cursor owner, it means players 1 will attack grid 0 and reverso for player 0
        radarPlayer1.getGridCursor().setOwner(conf.getPlayers()[1]);
        radarPlayer2.getGridCursor().setOwner(conf.getPlayers()[0]);
        this.repaint();
    }
    
    
    
    
    
    
    //**************************************************************************
    // METHODS
    //**************************************************************************
    /**
     * Switch the current display. It depend of action sent. 
     * @param m         GameModel recovered from update function
     * @param pAction   Action to do
     */
    public void switchTurnDisplay(GameModel m, int pAction){
        switch(pAction){
            case GameModel.GAME_OVER:
                DebugTrack.showDebugMsg("Game Over");
                this.p_bigCont.removeAll();
                this.p_bigCont.add(new EndGamePanel(GameModel.GAME_OVER));
                this.p_bigCont.revalidate();
                this.repaint();
                break;
            case GameModel.GAME_VICTORY:
                this.p_bigCont.removeAll();
                this.p_bigCont.add(new EndGamePanel(GameModel.GAME_VICTORY));
                this.p_bigCont.revalidate();
                this.repaint();
                DebugTrack.showDebugMsg("Victory");
                break;
            case GameModel.SWITCH_PAGE:
                int playerTurn  = m.getIdPlayerTurn();
                int foe         = (playerTurn+1)%2;
                this.p_radar.switchGrid(foe);
                this.p_fleet.switchGrid(playerTurn);
                this.switchPanel.display();
                break;
            default:
                this.repaint();
                break;
        }
        this.p_info.updateData(); //Update data in the information panel
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.img_background,0,0, this.getWidth(), this.getHeight(), this);
    }

    
    @Override
    public void update(ObservableModel o, Object arg){
        if(o instanceof Player){
            this.repaint();
        } else if (o instanceof GameModel && arg instanceof Integer){
            this.switchTurnDisplay((GameModel)o, (int)arg);
        } else {
            this.repaint();
        }
    }
    
    
    
    
    
    //**************************************************************************
    // UiElement Functions
    //**************************************************************************
    @Override
    public void loadUI(){
        this.reloadUI();
    }

    @Override
    public void reloadUI(){
        this.img_background = ThemeManager.getTheme().getImg(417000);
        this.repaint();
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
    // Inner class for break between 2 shoot (In V2 mode)
    //**************************************************************************
    /**
     * <h1>SwitchPanel</h1>
     * <p>
     * public class SwitchPanel<br/>
     * extends JPanel
     * </p>
     * 
     * <p>Panel display between 2 round in V2 mode. It is used to hide 
     * the players fleet during switching
     * </p>
     */
    private class SwitchPanel extends JPanel{
        private     JButton     b_confirm;
        
        public SwitchPanel(){
            this.initComponents();
        }
        private void initComponents(){
            this.b_confirm = new JButton("Next player");
            this.b_confirm.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    stopSwitchPanel();
                }
            });
            this.setBackground(Color.BLACK);
            this.add(this.b_confirm);
        }
        
        /**
         * Start displaying this Panel. Remove grid (Radar and fleet) from 
         * current game and display page without information about players state
         */
        public void display(){
            GamePanel.this.remove(p_bigCont);
            GamePanel.this.remove(p_info);
            GamePanel.this.add(this);
            GamePanel.this.revalidate();
            GamePanel.this.repaint();
        }
        
        /**
         * Stop displaying this panel. Restore current game state with player 
         * turn data
         */
        public void stopSwitchPanel(){
            GamePanel.this.remove(this);
            GamePanel.this.add(p_bigCont, BorderLayout.CENTER);
            GamePanel.this.add(p_info, BorderLayout.SOUTH);
            GamePanel.this.revalidate();
            GamePanel.this.repaint();
        }
    }
    
    
    
    
    
    //**************************************************************************
    // Inner class Victory / Game Over
    //**************************************************************************
    /**
     * <h1>EndGamePanel</h1>
     * <p>
     * private class EndGamePanel
     * extends JPanel
     * </p>
     * <p>Display the game issue for current player : Game over or victory</p>
     */
    private class EndGamePanel extends JPanel{
        private JLabel  l_titleReward;
        public EndGamePanel(int pValue){
            this.setBackground(Color.BLACK);
            this.l_titleReward = new JLabel();
            this.l_titleReward.setForeground(Color.WHITE);
            this.setPreferredSize(new Dimension(600,400));
            this.add(this.l_titleReward);
            
            //Display voctory or game over
            switch(pValue){
                case GameModel.GAME_VICTORY:
                    this.l_titleReward.setText("Victory");
                    break;
                case GameModel.GAME_OVER:
                    this.l_titleReward.setText("Game over");
                    break;
            }
        }
    }
}
