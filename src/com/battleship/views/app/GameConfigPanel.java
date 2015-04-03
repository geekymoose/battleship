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
import javax.swing.AbstractButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



/**
 * <h1>GameConfigPanel</h1>
 * <p>
 * public class GameConfigPanel<br/>
 * extends PagePanel<br/>
 * implements ObserverModel
 * </p>
 * 
 * @date    Feb 11. 2015
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
    private     JPanel                  p_container;
    private     JPanel                  p_left;
    private     JPanel                  p_right;
    private     JPanel                  p_bigCont;
    private     JPanel                  p_card1;
    private     JPanel                  p_card2;
    
    private     JLabel                  l_grid1;
    private     JLabel                  l_grid2;
    
    private     AbstractButton          b_right;
    private     AbstractButton          b_left;
    private     AbstractButton          b_validate;
    private     AbstractButton          b_reset;
    private     AbstractButton          b_back;
    
    //Data
    private     int                     gridWidth; //Not used atm
    private     int                     gridHeight;//Not used atm
    private     int                     gridType;
    
    
    
    
    
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
    }
    
    /*
     * Init components
     */
    private void initComponents(){
        p_buttons   = new JPanel();
        p_center    = new JPanel();
        p_container = new ContainerPanel();
        p_left      = new JPanel();
        p_right     = new JPanel();
        p_bigCont   = new JPanel();
        p_card1     = new JPanel();
        p_card2     = new JPanel();
        
        cl          = new CardLayout();
        gbc         = new GridBagConstraints();
        gbc.ipadx = 50;
        gbc.ipady = 50;
        
        p_bigCont   .setOpaque(false);
        p_container .setOpaque(false);
        p_center    .setOpaque(false);
        p_left      .setOpaque(false);
        p_right     .setOpaque(false);
        p_buttons   .setOpaque(false);
        
        l_grid1     = new JLabel(ThemeManager.getTheme().getImgIcon(414100));
        l_grid2     = new JLabel(ThemeManager.getTheme().getImgIcon(414200));
        
        this            .setLayout(new BorderLayout());
        p_buttons       .setLayout(new FlowLayout());
        p_center        .setLayout(cl);
        p_container     .setLayout(new BorderLayout());
        p_left          .setLayout(new BorderLayout());
        p_right         .setLayout(new BorderLayout());
        p_bigCont       .setLayout(new GridBagLayout());
        
        
        b_validate      = new ZozoDecorator(new ImgButton(406100, 406200, 406300));
        b_reset         = new ZozoDecorator(new ImgButton(405100, 405200, 405300));
        b_back          = new ZozoDecorator(new ImgButton(404100, 404200, 404300));
        b_right         = new ZozoDecorator(new ImgButton(413100, 413200, 413300));
        b_left          = new ZozoDecorator(new ImgButton(412100, 412200, 412300));
        
        p_buttons       .add(b_back);
        p_buttons       .add(b_reset);
        p_buttons       .add(b_validate);
        
        p_left          .add(b_left, BorderLayout.CENTER);
        p_right         .add(b_right, BorderLayout.CENTER);
        
        //p_center        .add(l_grid1, "GRID1");
        //p_center        .add(l_grid2, "GRID2");
        
        p_container     .add(p_buttons, BorderLayout.SOUTH);
        p_container     .add(p_right, BorderLayout.EAST);
        p_container     .add(p_left, BorderLayout.WEST);
        p_container     .add(p_center, BorderLayout.CENTER);
        
        //Magouille pour sizer le borderlayout
        p_bigCont.add(p_container, gbc);
        //p_bigCont.setPreferredSize(new Dimension(490,370));
        this.add(p_bigCont);
        
        this.setBtnActions();
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
    public void update(ObservableModel o, Object arg){
        this.gridWidth  = ((GameConfigModel)o).getGridWidth();
        this.gridHeight = ((GameConfigModel)o).getGridHeight();
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
        //Not used
    }

    @Override
    public void reloadUI(){
        //Not used
    }
    
    
    
    
    
    //**************************************************************************
    // Rooting functions
    //**************************************************************************
    @Override
    protected void goNextPage(){
        int mode = Session.getGameMode();
        if(this.controller.isValidConfig()){
            switch(mode){
                case MODE_AI:
                    frame.rooting(Config.getRootsValues("place-boats"), null);
                    break;
                case MODE_V2:
                    break;
                case MODE_LAN:
                    break;
                case MODE_INTERNET:
                    break;
            }
        }
    }
    
    @Override
    protected void goPreviousPage(){
        String msg      = "Are you sure you want to go back? Current configuration could be lost";
        String title    = "Warning";
        int choice = UiDialog.showYesNoWarning(title, msg);
        if(choice == JOptionPane.OK_OPTION){
            int mode = Session.getGameMode();
            if(this.controller.isValidConfig()){
                switch(mode){
                    case MODE_AI:
                        frame.rooting(Config.getRootsValues("choose-game"), null);
                        break;
                    case MODE_V2:
                        break;
                    case MODE_LAN:
                        break;
                    case MODE_INTERNET:
                        break;
                }
            }
        }
    } //End previous

    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = ThemeManager.getTheme().getImg(415000);
        g.drawImage(img,0,0, this.getWidth(), this.getHeight(), this);
    }
}