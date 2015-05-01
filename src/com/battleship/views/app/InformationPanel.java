/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.asset.Config;
import com.battleship.asset.ThemeManager;
import com.battleship.constants.GameConstants;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;





// Panel qui contient les armes / et infos du joueur => EN + Javadoc
/**
 *
 *
 * @date    11 févr. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class InformationPanel extends JPanel implements Observer {

    private JTextArea ta_info;
    private JScrollPane sp_scroll;

    private JLabel l_title;
    private JLabel l_scoreTitle;
    private JLabel l_score;

    private JPanel p_west;
    private JPanel p_center;
    private JPanel p_east;
    
    private JPanel p_weapon;
        
    private JPanel p_container;
    
    private GridBagConstraints gc = new GridBagConstraints();

    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public InformationPanel() {
        initComponents();
        addEachComponents();
        //setSizes();
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    private void initComponents() {
        this.setLayout(new BorderLayout());
        //l_title = new JLabel(Config.getDisplayConst_str("information-title"));
        l_scoreTitle = new JLabel("Score : ");
        l_score = new JLabel("0");
        ta_info = new JTextArea();
        p_west = new JPanel();
        p_east = new JPanel();
        p_center = new JPanel();
        p_weapon = new JPanel();
        
        p_container = new JPanel();
        setupTextArea();
    }
    
    private void setSizes(){
    }

    private void addEachComponents() {
        p_weapon.add(new NukeIcon());
        p_weapon.add(new TorpedoIcon(){});
        p_weapon.add(new BombIcon());
        p_weapon.add(new MissileIcon());
        
        p_west.add(ta_info);
        p_center.add(l_scoreTitle);
        p_center.add(l_score);
        p_east.add(p_weapon);
        
        this.add(p_west, BorderLayout.WEST);
        this.add(p_center, BorderLayout.CENTER);
        this.add(p_east, BorderLayout.EAST);
        
    }

    private void setupTextArea() {
        ta_info = new JTextArea();
        ta_info.setLineWrap(true);
        ta_info.setEditable(false);
        sp_scroll = new JScrollPane(ta_info);
        sp_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public void printInfoMessage(String string) {
        ta_info.append(string);
        ta_info.setCaretPosition(ta_info.getText().length());
    }

    //**************************************************************************
    // PATTERN OBSERVER
    //**************************************************************************
    @Override
    public void update(Observable o, Object arg){
        // Met à jour le score, pour les infos on court-cricuite sans passer par l'oberver
        // via la methode printInfoMessage qui ajoute un message directement
    }
    
    
    
    
    
    
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
            this.setPreferredSize(Config.getDimValues_dim("dim-dockboat"));
            this.addMouseListener((MouseListener) this);
            this.setOpaque(false);
        }
        
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            //Boat    selectedBoat    = controller.getCurrentPlayer().getSelectedBoat();
            Image   img1            = ThemeManager.getTheme().getImg(this.selectedImg);
            Image   img2            = ThemeManager.getTheme().getImg(this.currentImg);
           
            /*
            if(selectedBoat != null && selectedBoat.getBoatId() == this.idWeapon) {
                g.drawImage(img1, 0, 0, img1.getWidth(this), img1.getHeight(this),this);
            } else{ 
                g.drawImage(img2, 0, 0, img2.getWidth(this), img2.getHeight(this), this);
            }
            */
        }
        
        
        //**********************************************************************
        // Funtion for mouse Listener
        //**********************************************************************
        @Override
        public void mouseClicked(MouseEvent e){
            WeaponBtn.this.repaint();
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
            super(GameConstants.NUKE);
            this.defaultImg     = 502200;
            this.hoverImg       = 502100;
            this.selectedImg    = 502000;
            this.currentImg     = this.defaultImg;
        }
    } 
    
    //**************************************************************************
    private class MissileIcon extends WeaponBtn {
        public MissileIcon(){
            super(GameConstants.MISSILE);
            this.defaultImg     = 501200;
            this.hoverImg       = 501100;
            this.selectedImg    = 501000;
            this.currentImg     = this.defaultImg;
        }
    } 
    
    //**************************************************************************
    private class TorpedoIcon extends WeaponBtn {
        public TorpedoIcon(){
            super(GameConstants.TORPEDO);
            this.defaultImg     = 503200;
            this.hoverImg       = 503100;
            this.selectedImg    = 503000;
            this.currentImg     = this.defaultImg;
        }
    } 
    
    //**************************************************************************
    private class BombIcon extends WeaponBtn {
        public BombIcon(){
            super(GameConstants.BOMB);
            this.defaultImg     = 500200;
            this.hoverImg       = 500100;
            this.selectedImg    = 500000;
            this.currentImg     = this.defaultImg;
        }
    } 

    //------------------------END BOATS INNER CLASS-----------------------
}
