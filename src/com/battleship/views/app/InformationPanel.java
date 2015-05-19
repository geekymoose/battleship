/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.asset.Config;
import com.battleship.asset.Session;
import com.battleship.asset.ThemeManager;
import com.battleship.constants.GameConstants;
import com.battleship.controllers.GameController;
import com.battleship.exceptions.ExecError;
import com.battleship.models.game.Player;
import com.battleship.models.weapons.Weapon;
import com.battleship.views.tools.ContentPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;



/**
 *
 *
 * @since   11 Feb. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class InformationPanel extends ContentPanel implements Observer {
    //**************************************************************************
    // Variables
    //**************************************************************************
    private GameController      controller;
    private Player              player;
    private JTextArea           ta_info;
    private JScrollPane         sp_scroll;
    private JPanel              wrapper_general;
    
    //Display weapons
    private JPanel              p_weaponContener;
    
    //Wrapper
    private JPanel              p_west;
    private JPanel              p_center;
    private JPanel              p_east;
    private ContainerPanel      wrapper_data;
    
    //Data
    private JPanel              p_playerName;
    private JPanel              p_score;
    private JLabel              l_playerLabel;
    private JLabel              l_playerName;
    private JLabel              l_scoreTitle;
    private JLabel              l_scoreValue;
    private JLabel              l_turnLabel;
    private JLabel              l_turnValue;

    
    
    
    
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    /**
     * Create a new information panel
     * @param pParentPage parent page
     * @param pController controller for this panel
     * @throws ExecError thrown if unable to create Information panel
     */
    public InformationPanel(JPanel pParentPage, GameController pController) throws ExecError{
        super(pParentPage);
        this.controller     = pController;
        this.initComponents();
    }
    
    /*
     * Initialize all contener panel etc. 
     * Place every panel in he information panel
     */
    private void initComponents() {
        //General contener
        this.p_west             = new JPanel();
        this.p_east             = new JPanel();
        this.p_center           = new JPanel();
        this.wrapper_data       = new ContainerPanel();
        this.wrapper_general    = new JPanel();
        
        this.p_weaponContener   = new JPanel();
        this.p_score            = new JPanel();
        this.p_playerName       = new JPanel();
        
        this.l_scoreTitle       = new JLabel("Score : ");
        this.l_scoreValue       = new JLabel();
        this.l_playerLabel      = new JLabel("Player : ");
        this.l_playerName       = new JLabel();
        this.l_turnLabel        = new JLabel("Turn : ");
        this.l_turnValue        = new JLabel();
        
        this                    .setLayout(new BorderLayout());
        this.p_score            .setLayout(new FlowLayout());
        this.p_playerName       .setLayout(new FlowLayout());
        this.p_weaponContener   .setLayout(new FlowLayout());
        
        
        this.p_playerName       .add(this.l_playerName);
        this.p_score            .add(this.l_scoreTitle);
        this.p_score            .add(this.l_scoreValue);
        
        this.p_weaponContener   .add(new NukeIcon());
        this.p_weaponContener   .add(new TorpedoIcon(){});
        this.p_weaponContener   .add(new BombIcon());
        this.p_weaponContener   .add(new MissileIcon());
        
        //Add data panel in general panel
        this.p_west             .add(this.p_playerName);
        this.p_center           .add(this.p_weaponContener);
        this.p_east             .add(this.p_score);
        
        //Add general contener in information panel
        this.add(p_west,    BorderLayout.WEST);
        this.add(p_center,  BorderLayout.CENTER);
        this.add(p_east,    BorderLayout.EAST);
        
        
        //Set opaque
        this.p_score            .setOpaque(false);
        this.p_weaponContener   .setOpaque(false);
        this.p_playerName       .setOpaque(false);
        this.p_west             .setOpaque(false);
        this.p_east             .setOpaque(false);
        this.p_center           .setOpaque(false);
        this                    .setOpaque(false);
        this.wrapper_data       .setOpaque(false);
        this.wrapper_general    .setOpaque(false);
        
        this.updateData();
    }
    
    
    /**
     * Update the information panel data
     */
    public void updateData(){
        int mode = Session.getGameMode();
        switch(mode){
            case GameConstants.MODE_V2:
                this.player = this.controller.getGameModel().getPlayerTurn();
                break;
            default:
                this.player = Session.getPlayer();
                break;
        }
        this.l_scoreValue.setText(String.valueOf(this.player.getScore()));
        this.l_playerName.setText(this.player.getName());
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    @Override
    public void update(Observable o, Object arg){
    }

    @Override
    public void loadUI(){
        this.reloadUI();
    }
    
    @Override
    public void reloadUI(){
    }
    
    
    
    
    
    //**************************************************************************
    // Inner class
    //**************************************************************************
    /**
     * <h1>WeaponBtn</h1>
     * <p>
     * private abstract class WeaponBtn<br/>
     * extends JPanel<br/>
     * implements MouseListener
     * </p>
     */
    private abstract class WeaponBtn extends JPanel implements MouseListener{
        protected   int     currentImg;
        protected   int     defaultImg;
        protected   int     selectedImg;
        protected   int     hoverImg;
        protected   int     idWeapon;
        
        
        /**
         * Create a new DockBoats
         */
        protected WeaponBtn(int pWeaponId){
            this.idWeapon = pWeaponId;
            this.setPreferredSize(Config.getDimValues_dim("dim-weapon-icon"));
            this.addMouseListener((MouseListener) this);
            this.setOpaque(false);
        }
        
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            
            Image   img1            = ThemeManager.getTheme().getImg(this.selectedImg);
            Image   img2            = ThemeManager.getTheme().getImg(this.currentImg);
            g.drawImage(img2, 0, 0, img2.getWidth(this), img2.getHeight(this), this);
            
            if(player.getCurrentWeapon().getWeaponId() == this.idWeapon){
                g.drawImage(img1, 0, 0, img2.getWidth(this), img2.getHeight(this), this);
            } else{
                g.drawImage(img2, 0, 0, img2.getWidth(this), img2.getHeight(this), this);
            }
        }
        
        
        //**********************************************************************
        // Funtion for mouse Listener
        //**********************************************************************
        @Override
        public void mouseClicked(MouseEvent e){
            WeaponBtn.this.repaint();
            player.switchWeaponWith(this.idWeapon);
        }
        
        @Override
        public void mousePressed(MouseEvent e){
        }

        @Override
        public void mouseReleased(MouseEvent e){
            //controller.selectBoat(idWeapon);
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
            WeaponBtn.this.repaint();
        }
    } //------------------------END DockBoats INNER CLASS-----------------------
    
    
    
    //**************************************************************************
    private class NukeIcon extends WeaponBtn {
        public NukeIcon(){
            super(Weapon.NUKE);
            this.defaultImg     = 502200;
            this.hoverImg       = 502100;
            this.selectedImg    = 502000;
            this.currentImg     = this.defaultImg;
        }
    } 
    
    //**************************************************************************
    private class MissileIcon extends WeaponBtn {
        public MissileIcon(){
            super(Weapon.MISSILE);
            this.defaultImg     = 501200;
            this.hoverImg       = 501100;
            this.selectedImg    = 501000;
            this.currentImg     = this.defaultImg;
        }
    } 
    
    //**************************************************************************
    private class TorpedoIcon extends WeaponBtn {
        public TorpedoIcon(){
            super(Weapon.TORPEDO);
            this.defaultImg     = 503200;
            this.hoverImg       = 503100;
            this.selectedImg    = 503000;
            this.currentImg     = this.defaultImg;
        }
    } 
    
    //**************************************************************************
    private class BombIcon extends WeaponBtn {
        public BombIcon(){
            super(Weapon.BOMB);
            this.defaultImg     = 500200;
            this.hoverImg       = 500100;
            this.selectedImg    = 500000;
            this.currentImg     = this.defaultImg;
        }
    } 
    //------------------------END BOATS INNER CLASS-----------------------
}
