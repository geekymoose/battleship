/* 
 * Creation : 3 mars 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.asset.ThemeManager;
import com.battleship.exceptions.ExecError;
import com.battleship.uibutton.ImgButton;
import com.battleship.uibutton.ZozoDecorator;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;





/**
 * 
 * @date    3 mars 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class HeadBar extends JPanel{
    JLabel l_title;
    JLabel l_music      = new JLabel("MUSIC");
    private ImageIcon title =  ThemeManager.getTheme().getImgIcon(426100);
    // A Améliorer
    AbstractButton b_music = new ZozoDecorator(new ImgButton(425100, 425100, 425200));
    JPanel p_logo       = new JPanel();
    JPanel p_theme      = new JPanel();
    JComboBox themes    = new JComboBox();
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    /**
     * Constructor of the HeadBar
     * @throws ExecError 
     */
    public HeadBar() throws ExecError{
        l_title = new JLabel(title);
        this    .setLayout(new BorderLayout());
        b_music.setPreferredSize(new Dimension(30,30));
        this    .add(l_title, BorderLayout.WEST);
        p_theme .add(themes);
        p_theme .add(b_music);
        this    .add(p_theme, BorderLayout.EAST);
        
        
        for(String str : ThemeManager.getThemeManager().getAllThemeNames()){
            this.themes.addItem(str);
        }
        
        //ThemeManager.getThemeManager().loadTheme("Selected theme yeah man");
        
        
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************

    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************

}
