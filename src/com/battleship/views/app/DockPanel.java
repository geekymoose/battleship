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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
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
public class DockPanel extends ContentPanel implements GameConstants{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private PlaceBoatsController    controller;
    
    private GridBagConstraints      gbc             = new GridBagConstraints();
    private JLabel                  l_cruiser       = new JLabel("Cruiser");
    private JLabel                  l_submarine     = new JLabel("Submarine");
    private JLabel                  l_aircraft      = new JLabel("Aircraft carrier");
    private JLabel                  l_destroyer     = new JLabel("Destroyer");
    private JLabel                  l_battleship    = new JLabel("Battleship");
    
    
    
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
        this.initComponents();
    }
    
    private void initComponents(){
        this.setOpaque(false);
        this.setLayout(new GridBagLayout());
        //gbc.insets = new Insets(0,0,0,0);
        l_cruiser   .setForeground(Color.WHITE);
        l_submarine .setForeground(Color.WHITE);
        l_destroyer .setForeground(Color.WHITE);
        l_aircraft  .setForeground(Color.WHITE);
        l_battleship.setForeground(Color.WHITE);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(new AircraftCarrier(), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(l_aircraft, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(new Battleship(), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(l_battleship, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(new Submarine(), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        this.add(l_submarine, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        this.add(new Cruiser(), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 7;
        this.add(l_cruiser, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 8;
        this.add(new Destroyer(), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 9;
        this.add(l_destroyer, gbc);
    }
    
    
    
    
    
    //**************************************************************************
    // METHODS
    //**************************************************************************
    
    
    
    
    
    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************
    
    
    
    
    
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
    private abstract class DockBoats extends JPanel implements MouseListener{
        protected   int     currentImg;
        protected   int     defaultImg;
        protected   int     selectedImg;
        protected   int     idBoat;
        
        
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
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(ThemeManager.getTheme().getImg(this.currentImg), 0, 0, this);
        }
        
        
        @Override
        public void mouseClicked(MouseEvent e){
            controller.selectBoat(idBoat);
            this.repaint();
        }

        @Override
        public void mousePressed(MouseEvent e){
        }

        @Override
        public void mouseReleased(MouseEvent e){
        }

        @Override
        public void mouseEntered(MouseEvent e){
            this.currentImg = this.selectedImg;
            this.repaint();
        }

        @Override
        public void mouseExited(MouseEvent e){
            this.currentImg = this.defaultImg;
            this.repaint();
        }
    } //------------------------END DockBoats INNER CLASS-----------------------
    
    
    //**************************************************************************
    private class AircraftCarrier extends DockBoats {
        public AircraftCarrier(){
            super(GameConstants.AIRCRAFT_CARRIER);
            this.defaultImg     = 5000;
            this.selectedImg    = 5001;
            this.currentImg     = this.defaultImg;
        }
    } 
    
    
    //**************************************************************************
    private class Battleship extends DockBoats {
        public Battleship(){
            super(GameConstants.BATTLESHIP);
            this.defaultImg     = 4000;
            this.selectedImg    = 4001;
            this.currentImg     = this.defaultImg;
        }
    } 
    
    
    //**************************************************************************
    private class Submarine extends DockBoats {
        public Submarine(){
            super(GameConstants.SUBMARINE);
            this.defaultImg     = 3002;
            this.selectedImg    = 3003;
            this.currentImg     = this.defaultImg;
        }
    } 
    
    
    //**************************************************************************
    private class Cruiser extends DockBoats {
        public Cruiser(){
            super(GameConstants.CRUISER);
            this.defaultImg     = 3000;
            this.selectedImg    = 3001;
            this.currentImg     = this.defaultImg;
        }
    } 
    
    
    //**************************************************************************
    private class Destroyer extends DockBoats {
        public Destroyer(){
            super(GameConstants.DESTROYER);
            this.defaultImg     = 2000;
            this.selectedImg    = 2001;
            this.currentImg     = this.defaultImg;
        }
    } //------------------------END BOATS INNER CLASS-----------------------
    
    
}
