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
import com.battleship.asset.ThemeManager;
import com.battleship.models.game.PlaceBoatsModel;
import com.battleship.models.game.Player;
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
    private     GridPanel                       p_grid;
    private     JPanel                          p_bigContainer;
    
    private     JPanel                          p_buttonPanel;
    private     AbstractButton                  b_valide;
    private     AbstractButton                  b_reset;
    private     AbstractButton                  b_back;
    
    private     Player                          currentPlayer;
    private     Dimension                       dimBox;
    
    private     GridBagConstraints              gbc;
    
    
    
    
    
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
        this.currentPlayer  = this.controller.getCurrentPlayer();
        this.p_grid         = null;
        this.dimBox         =  Config.getDimValues_dim("dim-placeboats-boxmap");
        this.setPreferredSize(Config.getDimValues_dim("default-dim-appframe"));
        this.createGrid();
        this.initComponents();
        
    }
    
    /*
     * Create all components 
     * @throws ExecError 
     */
    private void initComponents() throws ExecError{
        this.setLayout(new BorderLayout());
        
        this.p_buttonPanel  = new JPanel();
        this.b_valide       = new ZozoDecorator(new ImgButton(406100, 406200, 406300));
        this.b_reset        = new ZozoDecorator(new ImgButton(405100, 405200, 405300));
        this.b_back         = new ZozoDecorator(new ImgButton(404100, 404200, 404300));
        this.p_dock         = new DockPanel(this);
        p_container         = new ContainerPanel();
        p_bigContainer      = new JPanel();
        
        gbc                 = new GridBagConstraints();
        p_bigContainer      .setLayout(new GridBagLayout());
        this.p_buttonPanel  .setLayout(new FlowLayout());
        this.p_buttonPanel  .add(b_back);
        this.p_buttonPanel  .add(b_reset);
        this.p_buttonPanel  .add(b_valide);
        
        p_container         .setLayout(new BorderLayout());
        
        p_buttonPanel   .setOpaque(false);
        p_container     .setOpaque(false);
        p_bigContainer  .setOpaque(false);
        p_grid          .setOpaque(false);
        p_dock          .setOpaque(false);
        
        p_container.add(p_grid, BorderLayout.CENTER);
        p_container.add(p_dock, BorderLayout.EAST);
        p_container.add(p_buttonPanel, BorderLayout.SOUTH);
        p_bigContainer.add(p_container, gbc);
        
        this.add(p_bigContainer, BorderLayout.CENTER);
        this.setBtnActions();
    }
    
    /*
     * Create the p_grid where to place boats
     * @throws ExecError 
     */
    private void createGrid() throws ExecError{
        int         width   = this.controller.getWidth();
        int         height  = this.controller.getHeight();
        int         type    = this.controller.getGridType();
        switch(this.controller.getGridType()){
            case GRID_TYPE_SQUARE:
                this.p_grid = new GridSquareView(this, controller, width, height, type, dimBox);
                break;
            case GRID_TYPE_HEXAGON:
                this.p_grid = new GridHexaView(this, controller, width, height, type, dimBox);
                break;
        }

        this.revalidate();
        this.repaint();
    }
    
    /*
     * Reset the p_grid
     */
    private void resetGrid(){
        this.controller.resetFleetGrid();
    }
    
    /*
     * Display a break panel between to player placement
     */
    private void displayBreakPanel(){
        if(this.p_grid != null){
            this.remove(this.p_grid); //Remove old p_grid
        }
        JPanel breakPanel = new JPanel();
        breakPanel.setBackground(Color.BLACK);
        breakPanel.setPreferredSize(this.p_grid.getPreferredSize());
        this.add(breakPanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
        UiDialog.showWarning("Next Player", "Beware! Next player has to place his boats!!");
        this.remove(breakPanel);
        this.resetGrid();
        //this.add(this.p_grid, BorderLayout.CENTER);
        //this.resetGrid();
        p_container.add(this.p_grid, BorderLayout.CENTER);
        p_container.revalidate();
        this.revalidate();
        p_container.repaint();
        this.repaint();
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
                    resetGrid();
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
    // Override from PagePanel
    //**************************************************************************
    @Override
    public void initPage(){
        this.controller.initGrid();
    }

    @Override
    public void update(ObservableModel o, Object arg){
        PlaceBoatsModel m   = (PlaceBoatsModel)o;
        this.currentPlayer  = m.getCurrentPlayer();
    }
    
    @Override
    protected void goNextPage(){
        int mode = Session.getGameMode();
        switch(mode){
            case MODE_AI:
                break;
            case MODE_V2:
                boolean gameReady       = controller.areAllPlayerPlaced();
                boolean isAccpetedGrid  = controller.acceptGrid();
                if(isAccpetedGrid == true && gameReady == true){
                    this.frame.rooting(Config.getRootsValues("game"), true);
                } 
                else if (isAccpetedGrid == true){
                    this.displayBreakPanel();
                }
                else {
                    UiDialog.showWarning("beware!", "Some boats are missing on the grid!!\n"
                            + "Do you want to figth with less boats than your enemy? "
                            + "Woow! We've got a warrior here!");
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
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = ThemeManager.getTheme().getImg(415000);
        g.drawImage(img,0,0, this.getWidth(), this.getHeight(), this);
    }
}
