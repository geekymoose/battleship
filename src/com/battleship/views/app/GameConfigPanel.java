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
import com.battleship.models.game.Session;
import com.battleship.observers.ObservableModel;
import com.battleship.observers.ObserverModel;
import com.battleship.uibutton.ImgButton;
import com.battleship.uibutton.ZozoDecorator;
import com.battleship.views.tools.Config;
import com.battleship.views.tools.PagePanel;
import com.battleship.views.tools.WindowFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JButton;
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
public class GameConfigPanel extends PagePanel implements ObserverModel, GameConstants{
    private     GameConfigController    controller;
    private     JPanel                  p_buttons;
    private     JPanel                  p_validate;
    private     JPanel                  p_center;
    
    private     JLabel                  l_indication;
    private     JLabel                  l_imgSquare;
    private     JLabel                  l_imgHexa;
    
    private     JButton                 b_square;
    private     JButton                 b_hexa;
    private     AbstractButton          b_validate;
    private     AbstractButton          b_reset;
    private     AbstractButton          b_back;
    
    //Data
    private     int                     gridWidth;
    private     int                     gridHeight;
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
        if(pController==null){
            throw new ExecError();
        }
        this.controller         = pController;
        this.initComponents();
    }
    
    /*
     * Init components
     */
    private void initComponents(){
        this.p_buttons  = new JPanel();
        this.p_validate = new JPanel();
        this.p_center   = new JPanel();
        
        this            .setLayout(new BorderLayout());
        p_buttons       .setLayout(new BorderLayout());
        p_validate      .setLayout(new FlowLayout());
        p_center        .setLayout(new GridLayout(1,2));
        
        l_indication    = new JLabel("Choose your kind of grid");
        b_validate      = new ZozoDecorator(new ImgButton(406100, 406200, 406300));
        b_reset         = new ZozoDecorator(new ImgButton(405100, 405200, 405300));
        b_back          = new ZozoDecorator(new ImgButton(404100, 404200, 404300));
        b_square        = new JButton("Square");
        b_hexa          = new JButton("Hexagonal");
        
        
        p_center        .add(b_square);
        p_center        .add(b_hexa);
        p_buttons       .add(l_indication, BorderLayout.NORTH);
        p_buttons       .add(p_center, BorderLayout.CENTER);
        
        p_validate      .add(b_back);
        p_validate      .add(b_reset);
        p_validate      .add(b_validate);
        
        this.add(p_buttons, BorderLayout.NORTH);
        this.add(p_validate, BorderLayout.SOUTH);
        
        this.setBtnActions();
    }
    
    @Override
    public void initPage(){
        this.controller.resetDefaultConfig();
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
        b_square.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DebugTrack.showExecMsg("Square grid selected");
                    controller.changeGridType(GRID_TYPE_SQUARE);
                }
            }
        );
        b_hexa.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DebugTrack.showExecMsg("Hexa grid selected");
                    controller.changeGridType(GRID_TYPE_HEXAGON);
                }
            }
        );
    }//End setBtnActions
    
    
    
    
    
    //**************************************************************************
    // METHODS
    //**************************************************************************
    @Override
    public void update(ObservableModel o, Object arg){
        this.gridWidth  = ((GameConfigModel)o).getGridWidth();
        this.gridHeight = ((GameConfigModel)o).getGridHeight();
        this.gridType   = ((GameConfigModel)o).getGridType();
        
        //Set buttons state for grid type and reset button
        if(this.gridType == this.GRID_TYPE_SQUARE){
            this.b_square   .setEnabled(false);
            this.b_hexa     .setEnabled(true);
        } else if(this.gridType == this.GRID_TYPE_HEXAGON){
            this.b_square       .setEnabled(true);
            this.b_hexa     .setEnabled(false);
        }
        
        //Button reset state
        if(((GameConfigModel)o).isDefaultConfig()){
            this.b_reset.setEnabled(false);
        } else {
            this.b_reset.setEnabled(true);
        }
    }
    
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
        JOptionPane opt = new JOptionPane();
        int choice = opt.showConfirmDialog(null, 
                              "Are you sure you want to go back? "
                               +"Current configuration could be lost",
                              "Warning",
                              JOptionPane.YES_NO_CANCEL_OPTION, 
                              JOptionPane.QUESTION_MESSAGE);
        
        
        if(choice==JOptionPane.OK_OPTION){
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
    
}