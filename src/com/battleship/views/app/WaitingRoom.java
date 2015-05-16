/* 
 * Creation : 5 mai 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.asset.Config;
import com.battleship.asset.Session;
import com.battleship.asset.SwingFactory;
import com.battleship.asset.ThemeManager;
import com.battleship.constants.GameConstants;
import com.battleship.exceptions.ExecError;
import com.battleship.exceptions.ForbiddenAction;
import com.battleship.main.DebugTrack;
import com.battleship.models.game.GameConfigModel;
import com.battleship.network.Capsule;
import com.battleship.network.Request;
import com.battleship.network.ServerGame;
import com.battleship.observers.ObservableLan;
import com.battleship.observers.ObserverLan;
import com.battleship.uibutton.ImgButton;
import com.battleship.uibutton.UiButton;
import com.battleship.uibutton.ZozoDecorator;
import com.battleship.views.tools.PagePanel;
import com.battleship.views.tools.UiDialog;
import com.battleship.views.tools.UiElement;
import com.battleship.views.tools.WindowFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * Display current configuration for a lan game. Current player on the game are 
 * shown and current state. When each player is ready, game can start
 * 
 * @since   5 mai 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class WaitingRoom extends PagePanel implements GameConstants, 
                                                         UiElement,
                                                         ObserverLan{
    private HeadBar                 p_hb;
    private JPanel                  p_container;
    private JPanel                  p_buttons;
    private ContainerPanel          p_centeredPane;
    private JPanel                  p_bigCont;
    
    private UiButton                b_back;
    private UiButton                b_start;

    //Images
    private Image background;
    
    private ServerGame  game;
    private boolean     isBeginner;
    
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public WaitingRoom(WindowFrame pFrame) throws ExecError {
        super(pFrame);
        DebugTrack.showExecMsg("In Waiting Room");
        this.setPreferredSize(Config.getDimValues_dim("default-dim-appframe"));
        this.initComponents();
        this.setBtnActions();
        Session.getNetwork().addLanObserver(this);
        Session.getNetwork().sendCapsule(new Capsule(Request.GET_GAME_SERVER, null));
        this.loadUI();
    }
    
    private void initComponents() throws ExecError {
        p_buttons           = new JPanel();
        p_hb                = new HeadBar(this);
        p_container         = new JPanel();
        p_bigCont           = new JPanel();
        p_centeredPane      = new ContainerPanel();

        
        this                .setLayout(new BorderLayout());
        p_container         .setLayout(new BorderLayout());
        p_bigCont           .setLayout(new GridBagLayout());
        p_centeredPane      .setLayout(new BorderLayout());
        
        p_centeredPane      .setPreferredSize(new Dimension(550,400));

        b_start         = new ZozoDecorator(new ImgButton(406100, 406200, 406300)).getUiButton();
        b_back          = new ZozoDecorator(new ImgButton(404100, 404200, 404300)).getUiButton();
        
        p_buttons           .add(b_back);
        p_buttons           .add(b_start);
        
        p_bigCont           .setOpaque(false);
        p_container         .setOpaque(false);
        p_centeredPane      .setOpaque(false);
        p_buttons           .setOpaque(false);
        
        
        p_centeredPane      .add(p_buttons, BorderLayout.SOUTH);
        p_bigCont           .add(p_centeredPane);
        p_container         .add(p_bigCont,BorderLayout.CENTER);
        
        this.add(p_hb,          BorderLayout.NORTH);
        this.add(p_container,   BorderLayout.CENTER);
    }
    
    private void setBtnActions() {
        b_start.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    DebugTrack.showExecMsg("Your are ready");
                    Session.getNetwork().sendCapsule(new Capsule(Request.READY_FOR_PLAYING, null));
                    UiDialog.showWarning("Waiting for players", "You are ready. Game is waiting "
                            + "for the other players");
                }
            }
        );
        b_back.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    goPreviousPage();
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
            Session.getNetwork().sendCapsule(new Capsule(Request.LEAVE_TMP_GAME, null));
            frame.rooting(ApplicationFrame.LIST_GAMES, false);
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
    public void updateLan(ObservableLan o, Object arg){
        if(arg instanceof Boolean){
            this.isBeginner = (boolean)arg;
            System.out.println("DEBUG : "+arg);
            System.out.println("DEBUG : "+isBeginner);
        }
        
        else if(arg instanceof ServerGame){
            this.game = (ServerGame)arg;
        }
        
        else if(arg instanceof Request){
            try {
                //Game is ready to start!
                Session.getNetwork().deleteLanObserver(this);
                GameConfigModel m = new GameConfigModel();
                m.setTitle(this.game.getTitle());
                m.setGridType(this.game.getType());
                
                int posBeginner = (this.isBeginner) ? 0 : 1;
                m.setFirstPlayerTurn(posBeginner);
                
                SwingFactory.setGameConfig(m);
                frame.rooting(ApplicationFrame.PLACE_BOATS, true);
            } catch(ForbiddenAction ex) {
                //Should never happend
            }
        }
        
    }
}
