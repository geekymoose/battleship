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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
 * @since   May 4, 2015
 * @author  Constantin MASSON
 */
public class BazaarPanel extends PagePanel implements GameConstants{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private     ArrayList<WeaponPanel>  listShopWeapons;
    private     ArrayList<WeaponPanel>  listOwnedWeapons;
    
    //Wrapper
    private     HeadBar             headBar;
    private     UiButton            buttonBack;
    private     ContainerPanel      wrapper_page;
    private     JPanel              wrapper_general;
    private     JPanel              wrapper_weapons;
    private     JPanel              wrapper_data;
    
    //Panel 
    private     JPanel              p_shopWeaponPan;
    private     JPanel              p_ownedWeaponPan;
    
    //Panel for data
    private     JLabel              l_playerName;
    private     JLabel              l_playerMoney;
    
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
        this.updateData();
    }
    
    /*
     * Initialize all components
     */
    private void initComponents() throws ExecError{
        this.headBar            = new HeadBar(this);
        this.wrapper_page       = new ContainerPanel();
        this.wrapper_general    = new JPanel();
        this.wrapper_data       = new JPanel();
        this.wrapper_weapons    = new JPanel();
        this.p_shopWeaponPan    = new JPanel();
        this.p_ownedWeaponPan   = new JPanel();
        this.buttonBack         = new ZozoDecorator(new ImgButton(404100, 404200, 404300)).getUiButton();
        
        this.l_playerMoney      = new JLabel();
        this.l_playerName       = new JLabel();
        this.l_playerMoney      .setForeground(Color.LIGHT_GRAY);
        this.l_playerName       .setForeground(Color.LIGHT_GRAY);
        
        this                    .setLayout(new BorderLayout());
        this.wrapper_general    .setLayout(new GridBagLayout());
        this.wrapper_data       .setLayout(new BoxLayout(this.wrapper_data, BoxLayout.Y_AXIS));
        this.wrapper_page       .setLayout(new BorderLayout());
        this.wrapper_weapons    .setLayout(new BorderLayout());
        
        this                    .setOpaque(false);
        this.wrapper_data       .setOpaque(false);
        this.wrapper_general    .setOpaque(false);
        this.wrapper_page       .setOpaque(false);
        this.wrapper_weapons    .setOpaque(false);
        this.p_ownedWeaponPan   .setOpaque(false);
        this.p_shopWeaponPan    .setOpaque(false);
        
        this.wrapper_data       .setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        this.wrapper_page       .setBorder(BorderFactory.createEmptyBorder(20,50,20,50));
        this.p_ownedWeaponPan   .setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2), "Owned Weapons"));
        this.p_shopWeaponPan   .setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2), "Shop's Weapons"));
        
        this.wrapper_weapons    .add(this.p_ownedWeaponPan,BorderLayout.NORTH);
        this.wrapper_weapons    .add(this.p_shopWeaponPan, BorderLayout.SOUTH);
        
        this.wrapper_data       .add(this.l_playerName);
        this.wrapper_data       .add(this.l_playerMoney);
        
        this.wrapper_page       .add(this.wrapper_data,     BorderLayout.NORTH);
        this.wrapper_page       .add(this.wrapper_weapons,  BorderLayout.CENTER);
        this.wrapper_page       .add(this.buttonBack,       BorderLayout.SOUTH);
        
        GridBagConstraints gbc  = new GridBagConstraints();
        this.wrapper_general    .add(this.wrapper_page, gbc);
        this.add(this.headBar,          BorderLayout.NORTH);
        this.add(this.wrapper_general,  BorderLayout.CENTER);
    }
    
    private void initButtons(){
        this.buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                goPreviousPage();
            }
        });
    }
    
    private void updateData(){
        //Player data
        this.l_playerName       .setText("Player name : "+Session.getName());
        this.l_playerMoney      .setText("Sold : "+Session.getMoney());
        this.wrapper_data.revalidate();
        
        //Shop weapons 
        this.listShopWeapons    = new ArrayList();
        this.listShopWeapons    .add(new WeaponPanel(new Bomb(null, 1), WeaponPanel.SHOP));
        this.listShopWeapons    .add(new WeaponPanel(new NuclearBomb(null, 1), WeaponPanel.SHOP));
        this.listShopWeapons    .add(new WeaponPanel(new Torpedo(null, 1), WeaponPanel.SHOP));
        this.p_shopWeaponPan    .removeAll();
        this.p_shopWeaponPan    .setLayout(new GridLayout(this.listShopWeapons.size(), 1));
        for(WeaponPanel w : this.listShopWeapons){
            this.p_shopWeaponPan.add(w);
        }
        this.p_shopWeaponPan    .revalidate();
        
        //Owned weapons
        this.listOwnedWeapons   = new ArrayList();
        for(Weapon w : Session.getListWeapons()){
            this.listOwnedWeapons.add(new WeaponPanel(w, WeaponPanel.OWNED));
        }
        this.p_ownedWeaponPan   .removeAll();
        this.p_ownedWeaponPan   .setLayout(new GridLayout(this.listOwnedWeapons.size(), 1));
        for(WeaponPanel w : this.listOwnedWeapons){
            this.p_ownedWeaponPan.add(w);
        }
        this.p_ownedWeaponPan   .revalidate();
        this.reloadUI();
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
        for(WeaponPanel w : this.listOwnedWeapons){
            w.reloadUI();
        }
        for(WeaponPanel w : this.listShopWeapons){
            w.reloadUI();
        }
        this.buttonBack.reloadUI();
        this.repaint();
    }
    
    @Override
    protected void goNextPage(){
    }
    
    @Override
    protected void goPreviousPage(){
        frame.rooting(ApplicationFrame.CHOOSE_GAME, null);
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
    private class WeaponPanel extends ContentPanel implements MouseListener{
        //**********************************************************************
        // Variables - Constants
        //**********************************************************************
        private     static final int    OWNED   = 1;
        private     static final int    SHOP    = 2;
        
        //Data
        private     int             idWeapon;
        private     String          name;
        private     int             priceWeapon;
        private     int             priceAmmo;
        private     ImageIcon       img_hover;
        private     ImageIcon       img_default;
        
        //Panel and display
        private     JLabel          l_weaponData;
        private     JLabel          l_weaponImg;
        private     UiButton        b_tradeButton;
        
        
        
        //**********************************************************************
        // Constructor - Initialization
        //**********************************************************************
        /**
         * Create a panel to display sell data about one weapon
         * @param pWeapon   weapon to display data
         * @param in pType  type weapon to display : owned or sold by trader
         */
        public WeaponPanel(Weapon pWeapon, int pType){
            this.idWeapon       = pWeapon.getWeaponId();
            this.priceWeapon    = pWeapon.getPriceWeapon();
            this.priceAmmo      = pWeapon.getPriceAmmo();
            this.name           = pWeapon.getName();
            this.setOpaque(false);
            this.initComponents(pType);
            this.addMouseListener(this);
        }
        
        private void initComponents(int pType){
            this.l_weaponData   = new JLabel();
            this.l_weaponImg    = new JLabel(this.img_default);
            this.l_weaponImg    .setPreferredSize(new Dimension(100,50));
            this.l_weaponData   .setPreferredSize(new Dimension(400, 50)); 
            this.l_weaponData   .setForeground(Color.LIGHT_GRAY);
            
            switch(pType){
                case WeaponPanel.OWNED:
                    String ammo = "NoData";
                    try {
                        int value = Session.getWeapon(idWeapon).getAmmo();
                        ammo = (value == Weapon.INFINITE_AMO) ? "Infinite" : String.valueOf(value);
                        this.priceAmmo = (this.priceAmmo == Weapon.INFINITE_AMO) ? 0 : this.priceAmmo;
                    } catch(ForbiddenAction ex) {
                    }
                    this.l_weaponData.setText(this.name+" - current ammo : "+ammo+" - Ammo price : "+priceAmmo);
                    this.b_tradeButton = new ZozoDecorator(new ImgButton(410700, 410900, 410800)).getUiButton();
                    this.b_tradeButton.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e){
                                try{
                                    switch(idWeapon){
                                        case Weapon.BOMB:
                                            Session.getSession().buyAmmoWeapon(new Bomb(null, 1), 1);
                                            break;
                                        case Weapon.NUKE:
                                            Session.getSession().buyAmmoWeapon(new NuclearBomb(null, 1), 1);
                                            break;
                                        case Weapon.TORPEDO:
                                            Session.getSession().buyAmmoWeapon(new Torpedo(null, 1), 1);
                                            break;
                                    }
                                } catch(ForbiddenAction ex) {
                                    UiDialog.showError("Error", ex.getMessage());
                                }
                                updatePage();
                            }
                        });
                    break;
                case WeaponPanel.SHOP:
                    this.l_weaponData.setText(this.name+" - Weapon price : "+priceWeapon);
                    this.b_tradeButton = new ZozoDecorator(new ImgButton(411000, 411200, 411100)).getUiButton();
                    this.b_tradeButton.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e){
                                try{
                                    switch(idWeapon){
                                        case Weapon.BOMB:
                                            Session.getSession().buyWeapon(new Bomb(null, 1));
                                            break;
                                        case Weapon.NUKE:
                                            Session.getSession().buyWeapon(new NuclearBomb(null, 1));
                                            break;
                                        case Weapon.TORPEDO:
                                            Session.getSession().buyWeapon(new Torpedo(null, 1));
                                            break;
                                    }
                                } catch(ForbiddenAction ex) {
                                    UiDialog.showError("Error", ex.getMessage());
                                }
                                updatePage();
                            }
                        });
                    break;
            }
            
            this.setLayout(new FlowLayout());
            this.add(this.l_weaponImg);
            this.add(this.l_weaponData);
            this.add(this.b_tradeButton);
        }
        
        public void updatePage(){
            BazaarPanel.this.updateData();
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
                case Weapon.BOMB:
                    this.img_default    = ThemeManager.getTheme().getImgIcon(500200);
                    this.img_hover      = ThemeManager.getTheme().getImgIcon(500100);
                    break;
                case Weapon.NUKE:
                    this.img_default    = ThemeManager.getTheme().getImgIcon(502200);
                    this.img_hover      = ThemeManager.getTheme().getImgIcon(502100);
                    break;
                case Weapon.TORPEDO:
                    this.img_default    = ThemeManager.getTheme().getImgIcon(503200);
                    this.img_hover      = ThemeManager.getTheme().getImgIcon(503100);
                    break;
                case Weapon.MISSILE:
                    this.img_default    = ThemeManager.getTheme().getImgIcon(501200);
                    this.img_hover      = ThemeManager.getTheme().getImgIcon(501100);
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
