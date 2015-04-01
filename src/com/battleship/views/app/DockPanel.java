/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.exceptions.ExecError;
import com.battleship.views.tools.Config;
import com.battleship.views.tools.ContentPanel;
import com.battleship.views.tools.PagePanel;
import com.battleship.views.tools.ThemeManager;
import java.awt.Graphics;
import java.awt.GridLayout;
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
public class DockPanel extends ContentPanel{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    
    
    
    
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    /**
     * Create a new Dock Panel 
     * @param pParentPage its parent
     * @throws ExecError thrown if PageParent doesn't exists or is null
     */
    public DockPanel(PagePanel pParentPage) throws ExecError{
        super(pParentPage);
        this.initComponents();
    }
    
    private void initComponents(){
        this.setLayout(new GridLayout(5,1));
        this.add(new AircraftCarrier());
        this.add(new Battleship());
        this.add(new Submarine());
        this.add(new Cruiser());
        this.add(new Destroyer());
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
    /**
     * Inner class to display boats on the dock
     * Enable user to select the boat he want to place
     */
    private abstract class DockBoats extends JPanel implements MouseListener {
        protected   int     currentImg;
        protected   int     defaultImg;
        protected   int     selectedImg;
        
        
        
        /**
         * Create a new DockBoats
         */
        protected DockBoats(){
            this.setPreferredSize(Config.getDimValues_dim("dim-dockboat"));
            this.addMouseListener(this);
        }
        
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(ThemeManager.getTheme().getImg(this.currentImg), 0, 0, this);
        }
        
        @Override
        public void mouseClicked(MouseEvent e){
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
    }
    
    
    //**************************************************************************
    private class AircraftCarrier extends DockBoats {
        public AircraftCarrier(){
            this.defaultImg     = 5000;
            this.selectedImg    = 5001;
            this.currentImg     = this.defaultImg;
        }
    } 
    
    
    //**************************************************************************
    private class Battleship extends DockBoats {
        public Battleship(){
            this.defaultImg     = 4000;
            this.selectedImg    = 4001;
            this.currentImg     = this.defaultImg;
        }
    } 
    
    
    //**************************************************************************
    private class Submarine extends DockBoats {
        public Submarine(){
            this.defaultImg     = 3002;
            this.selectedImg    = 3003;
            this.currentImg     = this.defaultImg;
        }
    } 
    
    
    //**************************************************************************
    private class Cruiser extends DockBoats {
        public Cruiser(){
            this.defaultImg     = 3000;
            this.selectedImg    = 3001;
            this.currentImg     = this.defaultImg;
        }
    } 
    
    
    //**************************************************************************
    private class Destroyer extends DockBoats {
        public Destroyer(){
            this.defaultImg     = 2000;
            this.selectedImg    = 2001;
            this.currentImg     = this.defaultImg;
        }
    } 
    
    
}
