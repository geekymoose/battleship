/* 
 * Creation : May 4, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.asset.Config;
import com.battleship.asset.Session;
import com.battleship.asset.ThemeManager;
import com.battleship.constants.GameConstants;
import com.battleship.exceptions.ExecError;
import com.battleship.exceptions.ForbiddenAction;
import com.battleship.models.weapons.*;
import com.battleship.uibutton.ImgButton;
import com.battleship.uibutton.UiButton;
import com.battleship.uibutton.ZozoDecorator;
import com.battleship.views.tools.ContentPanel;
import com.battleship.views.tools.PagePanel;
import com.battleship.views.tools.UiDialog;
import com.battleship.views.tools.WindowFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;




/**
 * <h1>BazaarPanel</h1>
 * <p>
 * public class BazaarPanel<br/>
 * extends PagePanel<br/>
 * implements GameConstants
 * </p>
 *
 * @date    May 4, 2015
 * @author  Constantin MASSON
 */
public class BazaarPanel extends PagePanel implements GameConstants{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private     ArrayList<ShopWeaponPanel>  listWeaponSell;
    
    private     JPanel              p_shopWeaponPan;
    private     HeadBar             headBar;
    private     UiButton            buttonBack;
    
    //Images
    private     Image               img_background;
    
    
    
    
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new BazaarPanel
     * @param pFrame frame WindowFrame where Bazaar is placed
     * @throws ExecError thrown if pFrame is null
     */
    public BazaarPanel(WindowFrame pFrame) throws ExecError{
        super(pFrame);
        this.setPreferredSize(Config.getDimValues_dim("default-dim-appframe"));
        this.initComponents();
        this.initButtons();
    }
    
    /*
     * Initialize all components
     */
    private void initComponents() throws ExecError{
        this.headBar            = new HeadBar(this);
        this.buttonBack         = new ZozoDecorator(new ImgButton(404100, 404200, 404300)).getUiButton();
        this.p_shopWeaponPan    = new JPanel();
        this.listWeaponSell     = new ArrayList();
        this.listWeaponSell     .add(new ShopWeaponPanel(new Bomb(null, 1)));
        this.listWeaponSell     .add(new ShopWeaponPanel(new NuclearBomb(null, 1)));
        this.listWeaponSell     .add(new ShopWeaponPanel(new Torpedo(null, 1)));
        
        this.p_shopWeaponPan    .setLayout(new GridLayout(this.listWeaponSell.size(), 1));
        this                    .setLayout(new BorderLayout());
        this                    .setOpaque(false);
        this.p_shopWeaponPan    .setOpaque(false);
        
        //Add elements
        for(ShopWeaponPanel ugh : this.listWeaponSell){
            this.p_shopWeaponPan.add(ugh);
        }
        
        this.add(this.headBar,          BorderLayout.NORTH);
        this.add(this.p_shopWeaponPan,  BorderLayout.CENTER);
        this.add(this.buttonBack,       BorderLayout.SOUTH);
    }
    
    /**
     * Initialize button functions
     */
    private void initButtons(){
        this.buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                goPreviousPage();
            }
        });
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.img_background, 0, 0, this.getWidth(), this.getHeight(), this);
    }
    
    
    
    
    

    //**************************************************************************
    // Rooting Function / UI Function
    //**************************************************************************
    @Override
    public void initPage() throws ExecError{
        this.loadUI();
    }

    @Override
    public void loadUI(){
        this.reloadUI();
    }

    @Override
    public void reloadUI(){
        this.img_background = ThemeManager.getTheme().getImg(417000);
        for(ShopWeaponPanel ugh : this.listWeaponSell){
            ugh.reloadUI();
        }
        this.buttonBack.reloadUI();
        this.repaint();
    }
    
    @Override
    protected void goNextPage(){
    }
    
    @Override
    protected void goPreviousPage(){
        frame.rooting(Config.getRootsValues("choose-game"), null);
    }
    
    
    
    //**************************************************************************
    // Inner class ShopWeaponPanel
    //**************************************************************************
    /**
     * <h1>ShopWeaponPanel</h1>
     * <p>
     * private class ShopWeaponPanel<br/>
     * extends ContentPanel<br/>
     * implements MouseListener
     * </p>
     */
    private class ShopWeaponPanel extends ContentPanel implements MouseListener{
        //**********************************************************************
        // Variables - Constants
        //**********************************************************************
        //Data
        private     int             idWeapon;
        private     String          name;
        private     int             priceWeapon;
        private     int             priceAmmo;
        private     ImageIcon       img_hover;
        private     ImageIcon       img_default;
        
        //Panel and display
        private     JButton         b_buyWeapon;
        private     JLabel          l_weaponName;
        private     JLabel          l_weaponData;
        private     JLabel          l_weaponImg;
        
        
        
        //**********************************************************************
        // Constructor - Initialization
        //**********************************************************************
        /**
         * Create a panel to display sell data about one weapon
         * @param pWeapon weapon to display data
         */
        public ShopWeaponPanel(Weapon pWeapon){
            this.idWeapon       = pWeapon.getWeaponId();
            this.priceWeapon    = pWeapon.getPriceWeapon();
            this.priceAmmo      = pWeapon.getPriceAmmo();
            this.name           = pWeapon.getName();
            this.setOpaque(false);
            this.initComponents();
            this.setBuyButton();
            this.addMouseListener(this);
        }
        
        private void initComponents(){
            this.l_weaponName   = new JLabel(this.name);
            this.l_weaponData   = new JLabel("Price : "+priceWeapon);
            this.l_weaponImg    = new JLabel(this.img_default);
            this.b_buyWeapon    = new JButton("Buy weapon");
            
            this.setLayout(new FlowLayout());
            this.add(this.l_weaponImg);
            this.add(this.l_weaponName);
            this.add(this.l_weaponData);
            this.add(this.b_buyWeapon);
        }
        
        private void setBuyButton(){
            this.b_buyWeapon.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    try{
                        switch(idWeapon){
                            case GameConstants.BOMB:
                                Session.getSession().buyWeapon(new Bomb(null, 1));
                                break;
                            case GameConstants.NUKE:
                                Session.getSession().buyWeapon(new NuclearBomb(null, 1));
                                break;
                            case GameConstants.TORPEDO:
                                Session.getSession().buyWeapon(new Torpedo(null, 1));
                                break;
                        }
                    } catch(ForbiddenAction ex) {
                        UiDialog.showError("Error", ex.getMessage());
                    }
                }
            });
        }
        
        
        
        //**********************************************************************
        // UI Function
        //**********************************************************************
        @Override
        public void loadUI(){
            this.reloadUI();
        }
        
        @Override
        public void reloadUI(){
            switch(this.idWeapon){
                case GameConstants.BOMB:
                    this.img_default    = ThemeManager.getTheme().getImgIcon(500200);
                    this.img_hover      = ThemeManager.getTheme().getImgIcon(500100);
                    break;
                case GameConstants.NUKE:
                    this.img_default    = ThemeManager.getTheme().getImgIcon(502200);
                    this.img_hover      = ThemeManager.getTheme().getImgIcon(502100);
                    break;
                case GameConstants.TORPEDO:
                    this.img_default    = ThemeManager.getTheme().getImgIcon(503200);
                    this.img_hover      = ThemeManager.getTheme().getImgIcon(503100);
                    break;
            }
            this.l_weaponImg.setIcon(this.img_default);
            this.repaint();
        }
        
        
        
        //**********************************************************************
        // MouseListener
        //**********************************************************************
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
            this.l_weaponImg.setIcon(this.img_hover);
        }
        
        @Override
        public void mouseExited(MouseEvent e){
            this.l_weaponImg.setIcon(this.img_default);
        }
    } //End inner class
}
