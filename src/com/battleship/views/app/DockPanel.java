/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.exceptions.ExecError;
import com.battleship.asset.Config;
import com.battleship.views.tools.ContentPanel;
import com.battleship.asset.ThemeManager;
import com.battleship.constants.GameConstants;
import com.battleship.controllers.PlaceBoatsController;
import com.battleship.models.game.Player;
import com.battleship.models.sprites.Boat;
import com.battleship.observers.ObservableModel;
import com.battleship.observers.ObserverModel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;



/**
 * <h1>DockPanel</h1>
 * <p>
 * public class DockPanel<br/>
 * extends ContentPanel
 * </p>
 * <p>
 * Display the dock with all boats to place on the grid.
 * </p>
 * 
 * @date    Feb 11. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class DockPanel extends ContentPanel implements ObserverModel, GameConstants{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private PlaceBoatsController    controller;
    private GridBagConstraints      gbc;
    
    private DockBoats               aircraftCarrier;
    private DockBoats               battleship;
    private DockBoats               submarine;
    private DockBoats               cruiser;
    private DockBoats               destroyer;
    private OrientationButton       orientationButton;
    
    
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    /**
     * Create a new Dock Panel.
     * @param pParentPage       its parent
     * @param pController       controller for DockPanel
     * @throws ExecError thrown if PageParent doesn't exists or is null
     */
    public DockPanel(JPanel pParentPage, PlaceBoatsController pController) throws ExecError{
        super(pParentPage);
        this.controller         = pController;
        this.gbc                = new GridBagConstraints();
        this.initComponents();
        this.loadUI();
    }
    
    private void initComponents(){
        this.aircraftCarrier    = new AircraftCarrierDock();
        this.battleship         = new BattleshipDock();
        this.submarine          = new SubmarineDock();
        this.cruiser            = new CruiserDock();
        this.destroyer          = new DestroyerDock();
        this.orientationButton  = new OrientationButton();
        
        this.setOpaque(false);
        this.setLayout(new GridBagLayout());
        
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(this.aircraftCarrier , gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(this.battleship, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(this.submarine, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(this.cruiser, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(this.destroyer, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        this.add(this.orientationButton, gbc);
    }
    
    
    
    
    
    //**************************************************************************
    // UI Functions
    //**************************************************************************
    @Override
    public void loadUI(){
        this.reloadUI();
    }
    
    @Override
    public void reloadUI(){
        this.aircraftCarrier    .reloadUI();
        this.battleship         .reloadUI();
        this.submarine          .reloadUI();
        this.cruiser            .reloadUI();
        this.destroyer          .reloadUI();
        this.orientationButton  .reloadUI();
    }

    @Override
    public void update(ObservableModel o, Object arg){
        this.repaint();
    }
    
    
    
    
    
    //**************************************************************************
    // Inner class
    //**************************************************************************
    /**<h1>DockBoats</h1>
     * <p>
     * private abstract class DockBoats<br/>
     * extends JPanel<br/>
     * implements MouseListener, MouseWheelEvent
     * </p>
     * <p>
     * Inner class to display boats on the dock
     * Enable user to select the boat he want to place
     * </p>
     */
    private abstract class DockBoats extends ContentPanel implements MouseListener{
        //**********************************************************************
        // Variables
        //**********************************************************************
        protected   int     defaultImg;
        protected   int     selectedImg;
        protected   int     hoverImg;
        protected   int     idBoat;
        
        protected   Image   img_current;
        protected   Image   img_default;
        protected   Image   img_select;
        protected   Image   img_hover;
        
        
        //**********************************************************************
        // Constructor and initialization
        //**********************************************************************
        /**
         * Create a new DockBoats
         */
        protected DockBoats(int pBoatId){
            this.idBoat = pBoatId;
            this.setPreferredSize(Config.getDimValues_dim("dim-dockboat"));
            this.addMouseListener(this);
            this.setOpaque(false);
        }
        
        @Override
        public void loadUI(){
            this.reloadUI();
        }
        
        @Override
        public void reloadUI(){
            this.img_default    = ThemeManager.getTheme().getImg(this.defaultImg);
            this.img_select     = ThemeManager.getTheme().getImg(this.selectedImg);
            this.img_hover      = ThemeManager.getTheme().getImg(this.hoverImg);
            this.img_current    = this.img_default;
        }
        
        
        
        //**********************************************************************
        // Functions
        //**********************************************************************
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            Boat    selectedBoat    = controller.getCurrentPlayer().getSelectedBoat();
            
            if(selectedBoat != null && selectedBoat.getBoatId() == this.idBoat) {
                g.drawImage(this.img_select, 0, 0, 
                            this.img_select.getWidth(this), 
                            this.img_select.getHeight(this), this);
            } else{ 
                g.drawImage(this.img_current, 0, 0, 
                            this.img_current.getWidth(this), 
                            this.img_current.getHeight(this),this);
            }
        }
        
        
        //**********************************************************************
        // Funtion for mouse Listener
        //**********************************************************************
        @Override
        public void mouseClicked(MouseEvent e){
            DockPanel.this.repaint();
        }

        @Override
        public void mousePressed(MouseEvent e){
        }

        @Override
        public void mouseReleased(MouseEvent e){
            controller.selectBoat(idBoat);
            this.repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e){
            this.img_current = this.img_hover;
            this.repaint();
        }
        
        @Override
        public void mouseExited(MouseEvent e){
            this.img_current = this.img_default;
            this.repaint();
            DockPanel.this.repaint();
        }
    } //------------------------END DockBoats INNER CLASS-----------------------
    
    
    
    //**************************************************************************
    private class AircraftCarrierDock extends DockBoats {
        public AircraftCarrierDock(){
            super(GameConstants.AIRCRAFT_CARRIER);
            this.defaultImg     = 418100;
            this.hoverImg       = 418200;
            this.selectedImg    = 418300;
        }
    } 
    
    
    //**************************************************************************
    private class BattleshipDock extends DockBoats {
        public BattleshipDock(){
            super(GameConstants.BATTLESHIP);
            this.defaultImg     = 419100;
            this.hoverImg       = 419200;
            this.selectedImg    = 419300;
        }
    } 
    
    
    //**************************************************************************
    private class SubmarineDock extends DockBoats {
        public SubmarineDock(){
            super(GameConstants.SUBMARINE);
            this.defaultImg     = 421100;
            this.hoverImg       = 421200;
            this.selectedImg    = 421300;
        }
    } 
    
    
    //**************************************************************************
    private class CruiserDock extends DockBoats {
        public CruiserDock(){
            super(GameConstants.CRUISER);
            this.defaultImg     = 420100;
            this.hoverImg       = 420200;
            this.selectedImg    = 420300;
        }
    } 
    
    
    //**************************************************************************
    private class DestroyerDock extends DockBoats {
        public DestroyerDock(){
            super(GameConstants.DESTROYER);
            this.defaultImg     = 422100;
            this.hoverImg       = 422200;
            this.selectedImg    = 422300;
        }
    } //------------------------END BOATS INNER CLASS-----------------------
    
    
    
    //**************************************************************************
    // Inner class for orientation button
    //**************************************************************************
    private class OrientationButton extends ContentPanel implements MouseListener{
        protected   Image   currentImg;
        protected   Image   img_vertical;
        protected   Image   img_horizontal;
        protected   Image   img_standingUp;
        protected   Image   img_standingDown;
        
        
        
        //**********************************************************************
        // Constructor and initialization
        //**********************************************************************
        /**
         * Create a new DockBoats
         */
        protected OrientationButton(){
            this.setPreferredSize(new Dimension(55,55));
            this.addMouseListener(this);
            this.setOpaque(false);
        }

        @Override
        public void loadUI(){
            this.reloadUI();
        }
        @Override
        public void reloadUI(){
            this.img_vertical       = ThemeManager.getTheme().getImg(413400);
            this.img_horizontal     = ThemeManager.getTheme().getImg(413500);
            this.img_standingUp     = ThemeManager.getTheme().getImg(413600);
            this.img_standingDown   = ThemeManager.getTheme().getImg(413700);
            this.currentImg         = this.img_vertical;
        }
        
        
        //**********************************************************************
        // Functions
        //**********************************************************************
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            int     orientation     = controller.getCurrentPlayer().getFleet().getCurrentOrientation();
            Image   current         = null;
            
            switch(orientation){
                case GameConstants.VERTICAL:
                    current = img_vertical;
                    break;
                case GameConstants.HORIZONTAL:
                    current = img_horizontal;
                    break;
                case GameConstants.STANDING_UP:
                    current = img_standingUp;
                    break;
                case GameConstants.STANDING_DOWN:
                    current = img_standingDown;
                    break;
            }
            g.drawImage(current, 0, 0, current.getWidth(this), current.getHeight(this), this);
        }
        
        
        //**********************************************************************
        // Funtion for mouse Listener
        //**********************************************************************
        @Override
        public void mouseClicked(MouseEvent e){
        }

        @Override
        public void mousePressed(MouseEvent e){
        }

        @Override
        public void mouseReleased(MouseEvent e){
            controller.getCurrentPlayer().getFleet().switchNextOrientation();
        }

        @Override
        public void mouseEntered(MouseEvent e){
        }

        @Override
        public void mouseExited(MouseEvent e){
        }
    }
}
