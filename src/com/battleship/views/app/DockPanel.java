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
import com.battleship.models.sprites.Boat;
import com.battleship.uibutton.ImgButton;
import com.battleship.uibutton.ZozoDecorator;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.AbstractButton;
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
    private GridBagConstraints      gbc;
    
    
    
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
    }
    
    private void initComponents(){
        this.setOpaque(false);
        this.setLayout(new GridBagLayout());
        
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(new AircraftCarrier(), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(new Battleship(), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.add(new Submarine(), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(new Cruiser(), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(new Destroyer(), gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        AbstractButton but = new ZozoDecorator(new ImgButton(407100, 407200, 407300));
        but.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("DEBUG IN DOCKPANEL: change p");
                    }
        });
        this.add(but, gbc);
    }
    
    
    
    
    
    //**************************************************************************
    // UI Functions
    //**************************************************************************
    @Override
    public void loadUI(){
    
    }
    
    @Override
    public void reloadUI(){
    
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
    private abstract class DockBoats extends JPanel implements MouseListener{
        protected   int     currentImg;
        protected   int     defaultImg;
        protected   int     selectedImg;
        protected   int     hoverImg;
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
            Boat    selectedBoat    = controller.getCurrentPlayer().getSelectedBoat();
            Image   img1            = ThemeManager.getTheme().getImg(this.selectedImg);
            Image   img2            = ThemeManager.getTheme().getImg(this.currentImg);
            
            if(selectedBoat != null && selectedBoat.getBoatId() == this.idBoat) {
                g.drawImage(img1, 0, 0, img1.getWidth(this), img1.getHeight(this),this);
            } else{ 
                g.drawImage(img2, 0, 0, img2.getWidth(this), img2.getHeight(this), this);
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
            this.currentImg = this.hoverImg;
            this.repaint();
        }

        @Override
        public void mouseExited(MouseEvent e){
            this.currentImg = this.defaultImg;
            this.repaint();
            DockPanel.this.repaint();
        }
    } //------------------------END DockBoats INNER CLASS-----------------------
    
    
    
    //**************************************************************************
    private class AircraftCarrier extends DockBoats {
        public AircraftCarrier(){
            super(GameConstants.AIRCRAFT_CARRIER);
            this.defaultImg     = 418100;
            this.hoverImg       = 418200;
            this.selectedImg    = 418300;
            this.currentImg     = this.defaultImg;
        }
    } 
    
    
    //**************************************************************************
    private class Battleship extends DockBoats {
        public Battleship(){
            super(GameConstants.BATTLESHIP);
            this.defaultImg     = 419100;
            this.hoverImg       = 419200;
            this.selectedImg    = 419300;
            this.currentImg     = this.defaultImg;
        }
    } 
    
    
    //**************************************************************************
    private class Submarine extends DockBoats {
        public Submarine(){
            super(GameConstants.SUBMARINE);
            this.defaultImg     = 421100;
            this.hoverImg       = 421200;
            this.selectedImg    = 421300;
            this.currentImg     = this.defaultImg;
        }
    } 
    
    
    //**************************************************************************
    private class Cruiser extends DockBoats {
        public Cruiser(){
            super(GameConstants.CRUISER);
            this.defaultImg     = 420100;
            this.hoverImg       = 420200;
            this.selectedImg    = 420300;
            this.currentImg     = this.defaultImg;
        }
    } 
    
    
    //**************************************************************************
    private class Destroyer extends DockBoats {
        public Destroyer(){
            super(GameConstants.DESTROYER);
            this.defaultImg     = 422100;
            this.hoverImg       = 422200;
            this.selectedImg    = 422300;
            this.currentImg     = this.defaultImg;
        }
    } //------------------------END BOATS INNER CLASS-----------------------
    
}
