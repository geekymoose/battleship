/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.constants.GameConstants;
import com.battleship.constants.Roots;
import com.battleship.controllers.GameConfigController;
import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import com.battleship.models.game.GameConfigModel;
import com.battleship.observers.ObservableModel;
import com.battleship.observers.ObserverModel;
import com.battleship.views.tools.PagePanel;
import com.battleship.views.tools.WindowFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;



/**
 * <h1></h1>
 * 
 * @date    Feb 11. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class GameConfigPanel extends PagePanel implements ObserverModel,
                                                            GameConstants,
                                                            Roots{
    private     GameConfigController    controller;
    private     JPanel                  p_buttons;
    private     JPanel                  p_validate;
    private     JPanel                  p_center;
    
    private     JLabel                  l_indication;
    private     JLabel                  l_imgSquare;
    private     JLabel                  l_imgHexa;
    
    private     JButton                 b_square;
    private     JButton                 b_hexa;
    private     JButton                 b_validate;
    private     JButton                 b_reset;
    private     JButton                 b_back;
    
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
        this.controller = pController;
        this.initComponents();
    }
    
    private void initComponents(){
        this.p_buttons  = new JPanel();
        this.p_validate = new JPanel();
        this.p_center   = new JPanel();
        
        this            .setLayout(new BorderLayout());
        p_buttons       .setLayout(new BorderLayout());
        p_validate      .setLayout(new FlowLayout());
        p_center        .setLayout(new GridLayout(1,2));
        
        l_indication    = new JLabel("Choose your kind of grid");
        b_validate      = new JButton("Validate");
        b_reset         = new JButton("Reset");
        b_back          = new JButton("Step back");
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
        switch (this.gridType){
            case GRID_TYPE_SQUARE:
                this.b_square   .setEnabled(false);
                this.b_hexa     .setEnabled(true);
                break;
            case GRID_TYPE_HEXAGON:
                this.b_square       .setEnabled(true);
                this.b_hexa     .setEnabled(false);
                break;
        }
        
        if(((GameConfigModel)o).isDefaultConfig()){
            this.b_reset.setEnabled(false);
        } else {
            this.b_reset.setEnabled(true);
        }
    }
    
    @Override
    protected void goNextPage(){
        //Add management for different kind of game
        if(this.controller.isValidConfig()){
            frame.rooting(PLACE_BOATS, null);
        }
        else{
            DebugTrack.showErrMsg("Config not valid yet");
            //Display message 'not valid'
        }
    }
    
    @Override
    protected void goPreviousPage(){
        DebugTrack.showErrMsg("Previous page to do");
        //To do later
    }
}