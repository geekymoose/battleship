/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.asset.*;
import com.battleship.behaviors.Target;
import com.battleship.controllers.GameController;
import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import com.battleship.models.game.FleetGridModel;
import com.battleship.models.game.GameConfigModel;
import com.battleship.models.game.GameModel;
import com.battleship.models.game.Player;
import com.battleship.models.weapons.Weapon;
import com.battleship.observers.*;
import com.battleship.uibutton.*;
import com.battleship.views.tools.PagePanel;
import com.battleship.views.tools.WindowFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;



/**
 * <h1>GamePanel</h1>
 * <p>
 * public class GamePanel<br/>
 * extends PagePanel
 * </p>
 * 
 * @since   11 févr. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class GamePanel extends PagePanel implements ObserverModel, ObserverLan{
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
    
    private     SwitchPanel             p_switchPanel;
    private     EndGamePanel            p_endGamePanel;
    
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
        if(Session.isConnected()){
            Session.getNetwork().addLanObserver(this);
        }
                DebugTrack.showDebugMsg("Game Over");
                this.p_bigCont.removeAll();
                this.p_endGamePanel.setOutcome(GameModel.GAME_OVER);
                this.p_bigCont.add(this.p_endGamePanel);
                this.p_bigCont.revalidate();
    }
    
    private void initComponents() throws ExecError{
        this.p_headbar  = new HeadBar(this);
        p_centerPane    = new ContainerPanel();
        p_info          = new InformationPanel(this, this.controller);
        p_fleet         = new PlayerFleetPanel(this);
        p_radar         = new RadarPanel(this);
        p_chat          = new ChatPanel(p_radar);
        
        p_bigCont       = new JPanel();
        p_switchPanel   = new SwitchPanel();
        p_endGamePanel  = new EndGamePanel(this);
        
        p_centerPane    .setOpaque(false);
        p_fleet         .setOpaque(false);
        p_radar         .setOpaque(false);
        p_bigCont       .setOpaque(false);
        
        this            .setLayout(new BorderLayout());
        p_centerPane    .setLayout(new GridBagLayout()); 
        p_bigCont       .setLayout(new GridBagLayout());
        
        GridBagConstraints  gc = new GridBagConstraints();
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
        
        this.p_fleet.initGrids(fleetPlayer1, fleetPlayer2, 0);
        this.p_radar.initGrids(radarPlayer1, radarPlayer2, 1, 0);
        
        //TMP DEBUG
        fleetPlayer1.getGridCursor().setClickNoArm();
        fleetPlayer2.getGridCursor().setClickNoArm();
        
        conf.getPlayers()[0].addObserverModel(this.p_radar);
        conf.getPlayers()[1].addObserverModel(this.p_radar);
        
        //Change cursor owner, it means players 1 will attack grid 0 and reverso for player 0
        radarPlayer1.getGridCursor().setOwner(conf.getPlayers()[1]);
        radarPlayer2.getGridCursor().setOwner(conf.getPlayers()[0]);
        
        //If connected and is not the first player for this game
        if(Session.isConnected()){
            if(conf.getFirstPlayerTurn() != 0){
                this.p_radar.setAttackedGrid(0);
            }
        }
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
                this.p_endGamePanel.setOutcome(GameModel.GAME_OVER);
                this.p_bigCont.add(this.p_endGamePanel);
                this.p_bigCont.revalidate();
                break;
                
            case GameModel.GAME_VICTORY:
                DebugTrack.showDebugMsg("Victory");
                this.p_bigCont.removeAll();
                this.p_endGamePanel.setOutcome(GameModel.GAME_VICTORY);
                this.p_bigCont.add(this.p_endGamePanel);
                this.p_bigCont.revalidate();
                break;
                
            case GameModel.SWITCH_PAGE:
                //Display the switch panel which create a break to switch player
                int playerTurn  = m.getIdPlayerTurn();
                int foe         = (playerTurn+1)%2;
                this.p_radar.switchGrid(foe);
                this.p_fleet.switchGrid(playerTurn);
                this.p_switchPanel.display();
                break;
                
            //Change behavior for radar grid cursor.
            case GameModel.SWITCH_BEHAVIORS:
                this.p_radar.setAttackedGrid(m.getIdPlayerTurn());
                break;
                
            default:
                break;
        }
        this.p_info.updateData(); //Update data in the information panel
        this.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.img_background,0,0, this.getWidth(), this.getHeight(), this);
    }

    @Override
    public void updateModel(ObservableModel o, Object arg){
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
        this.p_headbar      .reloadUI();
        this.p_fleet        .reloadUI();
        this.p_radar        .reloadUI();
        this.p_endGamePanel .reloadUI();
        this.p_switchPanel  .reloadUI();
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

    @Override
    public void updateLan(ObservableLan o, Object arg){
        if(arg instanceof int[]){
            //Remain : tab 0 = idWeapon / 1 = x / 2 = y
            int tab[] = (int[])arg;
            int idWeapon = tab[0];
            Point p = new Point();
            p.x = tab[1];
            p.y = tab[2];
            Weapon w = PooFactory.createWeaponFromId(idWeapon, 1);
            GridPanel grid = this.p_fleet.getCurrentFleetPanel();
            Target[][] target = grid.controller.getFleetGrid().getTabBoxMap();
            w.fireAt(p.x, p.y, target, grid.controller.getFleetGrid());
            this.controller.swithTurn();
        }
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
    private class SwitchPanel extends ContainerPanel implements KeyListener{
        //**********************************************************************
        // Constants - Variables
        //**********************************************************************
        private     JPanel      wrapper_center;
        private     JLabel      l_switchTitle;
        private     JLabel      l_nextPlayerName;
        private     JLabel      l_nbTurn;
        private     JLabel      l_switchMessage;
        
        
        
        //**********************************************************************
        // Constructors - Initialization
        //**********************************************************************
        public SwitchPanel(){
            this.initComponents();
            this.setFocusable(true);
            this.addKeyListener(this);
        }
        
        private void initComponents(){
            this.wrapper_center     = new JPanel();
            this.l_switchTitle      = new JLabel();
            this.l_nextPlayerName   = new JLabel();
            this.l_nbTurn           = new JLabel();
            this.l_switchMessage    = new JLabel();
            
            this.l_switchTitle      .setForeground(Color.WHITE);
            this.l_nextPlayerName   .setForeground(Color.WHITE);
            this.l_nbTurn           .setForeground(Color.WHITE);
            this.l_switchMessage    .setForeground(Color.WHITE);
            
            GridBagConstraints gbc  = new GridBagConstraints();
            this.setLayout(new GridBagLayout());
            this.wrapper_center     .setLayout(new BoxLayout(this.wrapper_center, BoxLayout.Y_AXIS));
            this.wrapper_center     .setOpaque(false);
            
            //Add in center wrapper
            this.wrapper_center     .add(this.l_switchTitle);
            this.wrapper_center     .add(this.l_nextPlayerName);
            this.wrapper_center     .add(this.l_nbTurn);
            this.wrapper_center     .add(this.l_switchMessage);
            
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
            String  name = GamePanel.this.controller.getGameModel().getPlayerTurn().getName();
            String  turn = String.valueOf(GamePanel.this.controller.getGameModel().getNbTurn());
            Font    font = new Font("Arial", Font.BOLD, 25);
            
            this.l_switchTitle      .setText("Call the next player!!!");
            this.l_nextPlayerName   .setText("Player turn : "+name);
            this.l_nbTurn           .setText("current turn : "+turn);
            this.l_switchMessage    .setText("Tape space to continue...");
            
            this.l_switchTitle      .setFont(font);
            this.l_nextPlayerName   .setFont(font);
            this.l_nbTurn           .setFont(font);
            this.l_switchMessage    .setFont(font);
            
            GamePanel.this.remove(p_bigCont);
            GamePanel.this.remove(p_info);
            GamePanel.this.remove(p_chat);
            GamePanel.this.add(this);
            GamePanel.this.revalidate();
            GamePanel.this.repaint();
            this.requestFocusInWindow();
        }
        
        /**
         * Stop displaying this panel. Restore current game state with player 
         * turn data
         */
        public void stopSwitchPanel(){
            GamePanel.this.remove(this);
            GamePanel.this.add(p_bigCont, BorderLayout.CENTER);
            GamePanel.this.add(p_info, BorderLayout.SOUTH);
            GamePanel.this.add(p_chat, BorderLayout.EAST);
            GamePanel.this.revalidate();
            GamePanel.this.repaint();
        }
        
        @Override
        public void loadUI(){
            this.reloadUI();
        }
        
        @Override
        public void reloadUI(){
        }
        
        
        //**********************************************************************
        // KeyListener
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
    }
    
    
    
    
    
    //**************************************************************************
    // Inner class Victory / Game Over
    //**************************************************************************
    /**
     * <h1>EndGamePanel</h1>
     * <p>
     * private class EndGamePanel<br/>
     * extends JPanel
     * </p>
     * <p>Display the game issue for current player : Game over or victory</p>
     */
    private class EndGamePanel extends ContainerPanel{
        //**********************************************************************
        // Constants - Variables
        //**********************************************************************
        private JPanel      wrapper_general;
        private JPanel      wrapper_center;
        private JPanel      wrapper_bottom;
        
        private JLabel      l_titleReward;
        private JLabel      l_mainImg;
        private UiButton    b_goBazaar;
        private UiButton    b_return;
        private int         gameOutcome;
        
        
        //**********************************************************************
        // Constructors - Initialization
        //**********************************************************************
        public EndGamePanel(PagePanel pParent){
            super(pParent);
            this.initComponents();
            this.initButtons();
        }
        
        private void initComponents(){
            this.b_return       = new ZozoDecorator(new ImgButton(404100, 404200, 404300)).getUiButton();
            this.b_goBazaar     = new ZozoDecorator(new ImgButton(410100, 410200, 410300)).getUiButton();
            this.l_titleReward  = new JLabel();
            this.wrapper_general= new JPanel();
            this.wrapper_bottom = new JPanel();
            this.wrapper_center = new JPanel();
            this.l_mainImg      = new JLabel(new ImageIcon());
            
            GridBagConstraints  gc = new GridBagConstraints();
            this                .setLayout(new GridBagLayout());
            this.wrapper_general.setLayout(new BorderLayout());
            this.wrapper_center .setLayout(new BoxLayout(this.wrapper_center, BoxLayout.Y_AXIS));
            this.wrapper_bottom .setLayout(new FlowLayout());
            
            this.wrapper_general.setOpaque(false);
            this.wrapper_center .setOpaque(false);
            this.wrapper_bottom .setOpaque(false);
            
            this                .setPreferredSize(new Dimension(600,400));
            
            this.l_titleReward  .setForeground(Color.WHITE);
            this.l_titleReward  .setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
            
            
            //Add elements in wrapper
            this.wrapper_center .add(this.l_mainImg);
            this.wrapper_center .add(this.l_titleReward);
            this.wrapper_center .add(this.b_goBazaar);
            this.wrapper_bottom .add(this.b_return);
            
            //Add general wrapper
            this.wrapper_general.add(this.wrapper_center, BorderLayout.CENTER);
            this.wrapper_general.add(this.wrapper_bottom, BorderLayout.SOUTH);
            this.add(this.wrapper_general, gc);
        }
        
        /*
         * Set button function
         */
        private void initButtons(){
            PagePanel page = ((PagePanel)parentPage);
            this.b_goBazaar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    page.getFrame().rooting(ApplicationFrame.BAZAAR, true);
                }
            });
            
            this.b_return.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    page.getFrame().rooting(ApplicationFrame.CHOOSE_GAME, true);
                }
            });
        }

        
        //**********************************************************************
        // Functions
        //**********************************************************************
        @Override
        public void loadUI(){
            this.reloadUI();
        }
        
        @Override
        public void reloadUI(){
            this.b_goBazaar.reloadUI();
            this.b_return.reloadUI();
        }
        
        /**
         * Set outcome of game. Display specific reward (Victory / Game over)
         * @param pOutCome 
         */
        public void setOutcome(int pOutCome){
            this.gameOutcome = pOutCome;
            //Display voctory or game over
            switch(this.gameOutcome){
                case GameModel.GAME_VICTORY:
                    this.l_mainImg = new JLabel(ThemeManager.getTheme().getImgIcon(722030));
                    this.revalidate();
                    break;
                case GameModel.GAME_OVER:
                    this.l_mainImg = new JLabel(ThemeManager.getTheme().getImgIcon(712030));
                    this.revalidate();
                    break;
            }
        }
    } //------------------------------------------------------------------------
}
