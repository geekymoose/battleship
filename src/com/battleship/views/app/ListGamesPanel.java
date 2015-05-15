/* 
 * Creation : 5 mai 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.asset.Config;
import com.battleship.asset.Session;
import com.battleship.asset.ThemeManager;
import com.battleship.constants.GameConstants;
import com.battleship.exceptions.ExecError;
import com.battleship.exceptions.LanError;
import com.battleship.main.DebugTrack;
import com.battleship.network.Capsule;
import com.battleship.network.Request;
import com.battleship.network.ServerGame;
import com.battleship.observers.ObservableLan;
import com.battleship.observers.ObserverLan;
import com.battleship.uibutton.ImgButton;
import com.battleship.uibutton.UiButton;
import com.battleship.uibutton.ZozoDecorator;
import com.battleship.views.tools.ContentPanel;
import com.battleship.views.tools.PagePanel;
import com.battleship.views.tools.UiDialog;
import com.battleship.views.tools.UiElement;
import com.battleship.views.tools.WindowFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * @since   5 mai 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class ListGamesPanel extends PagePanel implements GameConstants, 
                                                         UiElement,
                                                         ObserverLan{
    private HeadBar                 p_hb;
    private JPanel                  p_container;
    private JPanel                  p_buttons;
    private TablePanel              p_listPanel;
    private UiButton                b_createGame;
    private UiButton                b_back;
    private ContainerPanel          p_centeredPane;
    private JPanel                  p_bigCont;

    //Images
    private Image background;
    
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public ListGamesPanel(WindowFrame pFrame) throws ExecError {
        super(pFrame);
        this.setPreferredSize(Config.getDimValues_dim("default-dim-appframe"));
        this.initComponents();
        this.setBtnActions();
        Session.getNetwork().addObserver(this);
        this.loadUI();
    }
    
    private void initComponents() throws ExecError {
        p_buttons           = new JPanel();
        p_hb                = new HeadBar(this);
        p_container         = new JPanel();
        p_bigCont           = new JPanel();
        p_listPanel         = new TablePanel(); 
        p_centeredPane      = new ContainerPanel();

        
        this                .setLayout(new BorderLayout());
        p_listPanel         .setLayout(new FlowLayout());
        p_container         .setLayout(new BorderLayout());
        p_bigCont           .setLayout(new GridBagLayout());
        p_centeredPane      .setLayout(new BorderLayout());
        
        p_centeredPane      .setPreferredSize(new Dimension(550,400));

        b_createGame    = new ZozoDecorator(new ImgButton(406100, 406200, 406300)).getUiButton();
        b_back          = new ZozoDecorator(new ImgButton(404100, 404200, 404300)).getUiButton();
        
        p_buttons           .add(b_back);
        p_buttons           .add(b_createGame);
        
        p_bigCont           .setOpaque(false);
        p_container         .setOpaque(false);
        p_centeredPane      .setOpaque(false);
        p_buttons           .setOpaque(false);
        p_listPanel         .setOpaque(false);
    
        
        p_centeredPane      .add(p_listPanel,BorderLayout.CENTER);
        p_centeredPane      .add(p_buttons, BorderLayout.SOUTH);
        p_bigCont           .add(p_centeredPane);
        p_container         .add(p_bigCont,BorderLayout.CENTER);
        
        this.add(p_hb,          BorderLayout.NORTH);
        this.add(p_container,   BorderLayout.CENTER);
    }
    
    private void setBtnActions() {
        b_createGame.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Session.getNetwork().deleteObserver(ListGamesPanel.this);
                    DebugTrack.showExecMsg("Create new game");
                    frame.rooting(ApplicationFrame.CONFIG, true);
                }
            }
        );
        b_back.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        DebugTrack.showExecMsg("Return back");
                        Session.getNetwork().deleteObserver(ListGamesPanel.this);
                        Session.disconnect();
                        goPreviousPage();
                    } catch(LanError ex) {
                        UiDialog.showError("Connection Error", ex.getMessage());
                    }
                    
                }
            }
        );
        
    }//End setBtnActions
    
    
    //**************************************************************************
    // OVERRIDE METHODS
    //**************************************************************************
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.background, 0, 0, this.getWidth(), this.getHeight(), this);
    }
    
    @Override
    public void initPage() throws ExecError {
    }
    
    @Override
    protected void goNextPage() {
    }
    
    @Override
    protected void goPreviousPage(){
        String msg      = "Are you sure you want to go back? You will be disconnected";
        String title    = "Warning";
        int choice = UiDialog.showYesNoWarning(title, msg);
        if(choice == JOptionPane.OK_OPTION){
            frame.rooting(ApplicationFrame.CHOOSE_GAME, null);
        }
    }

    @Override
    public void loadUI() {
        this.reloadUI();
    }
    
    @Override
    public void reloadUI() {
        this.background = ThemeManager.getTheme().getImg(417000);
        this.p_hb.reloadUI();
        this.repaint();
    }

    
    //**************************************************************************
    // Network
    //**************************************************************************
    @Override
    public void update(ObservableLan o, Object arg){
        if(arg instanceof ArrayList){
            this.p_listPanel.updateListGame((ArrayList<ServerGame>)arg);
        }
    }
    
    
    
    
    //**************************************************************************
    // Inner class
    //**************************************************************************
    private class TablePanel extends ContentPanel{
        //**********************************************************************
        // CONSTRUCTOR
        //**********************************************************************
        private TablePanel(){
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.setBackground(Color.DARK_GRAY);
            Session.getNetwork().sendCapsule(new Capsule(Request.LIST_OF_GAMES, null));
        }
        
        private void updateListGame(ArrayList<ServerGame> pList){
            this.removeAll();
            if(pList.isEmpty()){
                DebugTrack.showExecMsg("Not game available");
            } 
            else {
                for(ServerGame g : pList){
                    this.add(new GameAvailable(g));
                }
            }
            
            this.revalidate();
        }

        @Override
        public void loadUI(){
        }
        
        @Override
        public void reloadUI(){
        }
    }
    
    
    private class GameAvailable extends ContentPanel{
        //**************************************************************************
        // Constants - Variables
        //**************************************************************************
        private JPanel      p_title;
        private JPanel      p_nbPlayer;
        private JPanel      p_grid;
        private JPanel      p_id;

        private JLabel      l_id;
        private JLabel      l_title;
        private JLabel      l_nbPlayer;
        private JLabel      l_grid;

        private JButton     b_join;
        //private JButton b_spectator;

        //**********************************************************************
        // CONSTRUCTOR
        //**********************************************************************
        private GameAvailable(ServerGame pGame){
            this.initComponents(pGame.getId(), pGame.getTitle(), pGame.getNbPlayers(), pGame.getType());
        }
        
        private void initComponents(Integer id, String title, Integer nbPlayer, int grid){
            p_id                = new JPanel();
            p_title             = new JPanel();
            p_nbPlayer          = new JPanel();
            p_grid              = new JPanel();
            
            l_title             = new JLabel(title);
            l_nbPlayer          = new JLabel(nbPlayer.toString()+"/2");
            l_id                = new JLabel(id.toString());
            l_grid              = new JLabel();
            
            b_join              = new JButton("Join");

            l_grid = (grid == 1) ? new JLabel("hexa") : new JLabel("square");
            

            this.p_id           .setLayout(new GridBagLayout());
            this.p_title        .setLayout(new GridBagLayout());
            this.p_nbPlayer     .setLayout(new GridBagLayout());


            p_title             .setBackground(Color.WHITE);
            p_id                .setBackground(Color.CYAN);
            l_title             .setForeground(Color.DARK_GRAY);
            p_nbPlayer          .setBackground(Color.WHITE);
            p_nbPlayer          .setForeground(Color.BLACK);
            p_grid              .setBackground(Color.WHITE);

            if(nbPlayer == 2){
                l_nbPlayer.setForeground(Color.red);
                b_join.setEnabled(false);
            }
            else{
                l_nbPlayer.setForeground(new Color(0, 160, 0));
            }


            p_id                .add(l_id);
            p_title             .add(l_title);
            p_grid              .add(l_grid);
            p_nbPlayer          .add(l_nbPlayer);

            p_id                .setPreferredSize(new Dimension(60,32));
            p_title             .setPreferredSize(new Dimension(200,32));
            p_nbPlayer          .setPreferredSize(new Dimension(60,32));
            b_join              .setPreferredSize(new Dimension(60,32));
            p_grid              .setPreferredSize(new Dimension(70, 32));
            //this.setPreferredSize(new Dimension(400, 40));
            
            b_join.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // SEND TO SERVER REQUEST JOIN
                        // ID GAME
                    }
                }
            );


            this.add(p_id);
            this.add(p_title);
            this.add(p_nbPlayer);
            this.add(p_grid);
            this.add(b_join);
            this.setOpaque(false);
        }

        @Override
        public void loadUI(){
        }
        
        @Override
        public void reloadUI(){
        }
    }
}
