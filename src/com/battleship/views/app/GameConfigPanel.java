/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.constants.GameConstants;
import com.battleship.controllers.GameConfigController;
import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import com.battleship.models.game.GameConfigModel;
import com.battleship.asset.Session;
import com.battleship.observers.ObservableModel;
import com.battleship.observers.ObserverModel;
import com.battleship.uibutton.ImgButton;
import com.battleship.uibutton.ZozoDecorator;
import com.battleship.asset.Config;
import com.battleship.views.tools.PagePanel;
import com.battleship.asset.ThemeManager;
import com.battleship.exceptions.ForbiddenAction;
import com.battleship.network.Capsule;
import com.battleship.network.Request;
import com.battleship.network.ServerGame;
import com.battleship.uibutton.UiButton;
import com.battleship.views.tools.UiDialog;
import com.battleship.views.tools.UiElement;
import com.battleship.views.tools.WindowFrame;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



/**
 * <h1>GameConfigPanel</h1>
 * <p>
 * public class GameConfigPanel<br/>
 * extends PagePanel<br/>
 * implements ObserverModel
 * </p>
 * 
 * @since   Feb 11. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class GameConfigPanel extends PagePanel implements ObserverModel, 
                                                          GameConstants,
                                                          UiElement{
    private     GameConfigController    controller;
    private     CardLayout              cl;
    private     GridBagConstraints      gbc;
    
    private     JPanel                  p_buttons;
    private     JPanel                  p_center;
    private     ContainerPanel          p_container;
    private     JPanel                  p_left;
    private     JPanel                  p_right;
    private     JPanel                  p_bigCont;
    private     JPanel                  p_bigbigCont;
    private     HeadBar                 p_hb;
    
    private     JLabel                  l_grid1;
    private     JLabel                  l_grid2;
    
    private     UiButton                b_right;
    private     UiButton                b_left;
    private     UiButton                b_validate;
    private     UiButton                b_reset;
    private     UiButton                b_back;
    
    //Image
    private     Image                   img_background;
    private     ImageIcon               img_gridHexa;
    private     ImageIcon               img_gridSquare;
    
    //Data
    private     int                     gridType;
    
    //Special content
    private     JPanel                  p_gameTitle;
    private     JLabel                  l_gameLabel;
    private     JTextField              tf_gameTitle;
    
    
    
    
    
    //**************************************************************************
    // CONSTRUCTOR - INITIALIZATION
    //**************************************************************************
    /**
     * Create a new ConfigGamePanel
     * @param pFrame        Frame containing this panel
     * @param pController   Controller for this page
     * @throws ExecError error if unable to create this panel
     */
    public GameConfigPanel(WindowFrame pFrame, GameConfigController pController) 
    throws ExecError{
        super(pFrame);
        if(pController == null){
            throw new ExecError();
        }
        this.controller = pController;
        this.setPreferredSize(Config.getDimValues_dim("default-dim-appframe"));
        this.initComponents();
        this.setBtnActions();
        this.setSpecialContent();
    }
    
    /*
     * Init components
     */
    private void initComponents() throws ExecError{
        p_buttons       = new JPanel();
        p_center        = new JPanel();
        p_container     = new ContainerPanel();
        p_left          = new JPanel();
        p_right         = new JPanel();
        p_bigCont       = new JPanel();
        p_bigbigCont    = new JPanel();
        p_hb            = new HeadBar(this);
        p_container.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        cl              = new CardLayout();
        gbc             = new GridBagConstraints();
        p_bigbigCont    .setLayout(new BorderLayout());
        gbc.ipadx       = 50;
        gbc.ipady       = 50;
        
        p_bigbigCont    .setOpaque(false);
        p_bigCont       .setOpaque(false);
        p_container     .setOpaque(false);
        p_center        .setOpaque(false);
        p_left          .setOpaque(false);
        p_right         .setOpaque(false);
        p_buttons       .setOpaque(false);
        
        this            .setLayout(new BorderLayout());
        p_buttons       .setLayout(new FlowLayout());
        p_center        .setLayout(cl);
        p_container     .setLayout(new BorderLayout());
        p_left          .setLayout(new BorderLayout());
        p_right         .setLayout(new BorderLayout());
        p_bigCont       .setLayout(new GridBagLayout());
        
        l_grid1         = new JLabel(this.img_gridHexa);
        l_grid2         = new JLabel(this.img_gridSquare);
        
        
        b_validate      = new ZozoDecorator(new ImgButton(406100, 406200, 406300)).getUiButton();
        b_reset         = new ZozoDecorator(new ImgButton(405100, 405200, 405300)).getUiButton();
        b_back          = new ZozoDecorator(new ImgButton(404100, 404200, 404300)).getUiButton();
        b_right         = new ZozoDecorator(new ImgButton(413100, 413200, 413300)).getUiButton();
        b_left          = new ZozoDecorator(new ImgButton(412100, 412200, 412300)).getUiButton();
        
        p_buttons       .add(b_back);
        p_buttons       .add(b_reset);
        p_buttons       .add(b_validate);
        
        p_left          .add(b_left,    BorderLayout.CENTER);
        p_right         .add(b_right,   BorderLayout.CENTER);
        
        p_container     .add(p_buttons, BorderLayout.SOUTH);
        p_container     .add(p_right,   BorderLayout.EAST);
        p_container     .add(p_left,    BorderLayout.WEST);
        p_container     .add(p_center,  BorderLayout.CENTER);
        
        p_bigCont       .add(p_container, gbc);
        p_bigbigCont    .add(p_bigCont);
        
        this.add(p_bigbigCont,  BorderLayout.CENTER);
        this.add(p_hb,          BorderLayout.NORTH);
    }
    
    /*
     * Add special content. Special content can change in function 
     * of current type game. For example, AI mode need to set AI difficulti 
     */
    private void setSpecialContent(){
        
        //So ugly :p 
        if(Session.isConnected()){
            this.tf_gameTitle   = new JTextField();
            this.l_gameLabel    = new JLabel("Title of the game");
            this.p_gameTitle    = new JPanel();
            
            this.p_gameTitle    .setOpaque(false);
            this.p_gameTitle    .setLayout(new FlowLayout());
            this.p_gameTitle    .setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
            this.p_gameTitle    .add(this.l_gameLabel);
            this.p_gameTitle    .add(this.tf_gameTitle);
            this.p_container    .add(this.p_gameTitle, BorderLayout.NORTH);
        }
    }
    
    @Override
    public void initPage(){
        this.controller.resetDefaultConfig();
        if(this.gridType==GRID_TYPE_SQUARE){
            p_center.add(l_grid2, "GRID_HEXA");
            p_center.add(l_grid1, "GRID_SQUARE");
        }else{
            p_center.add(l_grid1, "GRID_SQUARE");
            p_center.add(l_grid2, "GRID_HEXA");
        }
        this.loadUI();
    }
  
    
    /*
     * Create actionListener for the buttons
     * Each button call the new JPanel for this application (Parent)
     */
    private void setBtnActions() {
        b_validate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DebugTrack.showExecMsg("Valid Config Game");
                    goNextPage();
                }
            }
        );
        b_reset.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DebugTrack.showExecMsg("Reset config");
                    controller.resetDefaultConfig();
                    cl.first(p_center);
                }
            }
        );
        b_back.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DebugTrack.showExecMsg("Return back");
                    goPreviousPage();
                    
                }
            }
        );
        
        //Buttons for grid management
        b_right.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switchGrid();
                }
            }
        );
        b_left.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switchGrid();
                }
            }
        );
        
    }//End setBtnActions
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /*
     * Switch grid type to next value
     */
    private void switchGrid(){
        cl.next(p_center);           
        switch(this.gridType){
            case GRID_TYPE_SQUARE:
                DebugTrack.showExecMsg("Hexa grid selected");
                controller.changeGridType(GRID_TYPE_HEXAGON);
                break;
            case GRID_TYPE_HEXAGON:
                DebugTrack.showExecMsg("Square grid selected");
                controller.changeGridType(GRID_TYPE_SQUARE);
                break;
        }
    }
    
    
    
    
    
    //**************************************************************************
    // Functions for update - Ui reloading
    //**************************************************************************
    @Override
    public void updateModel(ObservableModel o, Object arg){
        this.gridType   = ((GameConfigModel)o).getGridType();
        
        //Button reset state
        if(((GameConfigModel)o).isDefaultConfig()){
            this.b_reset.setEnabled(false);
        } else {
            this.b_reset.setEnabled(true);
        }
    }

    @Override
    public void loadUI(){
        this.reloadUI();
    }

    @Override
    public void reloadUI(){
       this.img_background  = ThemeManager.getTheme().getImg(415000);
       this.img_gridHexa    = ThemeManager.getTheme().getImgIcon(414100);
       this.img_gridSquare  = ThemeManager.getTheme().getImgIcon(414200);
       this.b_right         .reloadUI();
       this.b_left          .reloadUI();
       this.b_validate      .reloadUI();
       this.b_reset         .reloadUI();
       this.b_back          .reloadUI();
       this.l_grid1.setIcon(this.img_gridHexa);
       this.l_grid2.setIcon(this.img_gridSquare);
       this.repaint();
    }

    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.img_background,0,0, this.getWidth(), this.getHeight(), this);
    }
    
    
    
    
    
    //**************************************************************************
    // Rooting functions
    //**************************************************************************
    @Override
    protected void goNextPage(){
        int mode = Session.getGameMode();
        switch(mode){
            case MODE_AI:
                if(this.controller.isValidConfig()){
                    frame.rooting(ApplicationFrame.PLACE_BOATS, true);
                }
                break;
            case MODE_V2:
                if(this.controller.isValidConfig()){
                    frame.rooting(ApplicationFrame.PLACE_BOATS, true);
                }
                break;
            case MODE_LAN:
                try{
                    this.controller.setTitle(this.tf_gameTitle.getText());
                    String title = this.tf_gameTitle.getText();
                    ServerGame g = new ServerGame(title, gridType);
                    Session.getNetwork().sendCapsule(new Capsule(Request.CREATE_GAME, g));
                    UiDialog.showConfirmDialog(title, "New game "+title+" created");
                    frame.rooting(ApplicationFrame.WAITING_ROOM, true);
                } catch(ForbiddenAction ex) {
                    UiDialog.showWarning("Bad title", ex.getMessage());
                }
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
                    frame.rooting(ApplicationFrame.CHOOSE_GAME, null);
                    break;
                case MODE_V2:
                    frame.rooting(ApplicationFrame.CHOOSE_GAME, null);
                    break;
                case MODE_LAN:
                    frame.rooting(ApplicationFrame.LIST_GAMES, true);
                    break;
            }
        }
    } //End previous
}