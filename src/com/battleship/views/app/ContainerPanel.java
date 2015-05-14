/* 
 * Creation : 3 avr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.asset.ThemeManager;
import com.battleship.views.tools.ContentPanel;
import com.battleship.views.tools.PagePanel;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.BorderFactory;



/**
 * <h1>ContainerPanel</h1>
 * <p>
 * public class ContainerPanel<br/>
 * extends ContentPanel
 * </p>
 * <p><Display a special background</p>
 * 
 * @since   3 Apr. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class ContainerPanel extends ContentPanel{
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public ContainerPanel(){
        this.setOpaque(false);
        this.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    }
    public ContainerPanel(PagePanel pParent){
        super(pParent);
        this.setOpaque(false);
        this.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    }
    
    //**************************************************************************
    // METHODS
    //**************************************************************************
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = ThemeManager.getTheme().getImg(416000);
        g.drawImage(img,0,0, this.getWidth(), this.getHeight(), this);
    }
    
    @Override
    public void loadUI(){
        this.reloadUI();
    }
    
    @Override
    public void reloadUI(){
    
    }
}
