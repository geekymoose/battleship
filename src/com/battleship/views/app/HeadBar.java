/* 
 * Creation : 3 mars 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.asset.ThemeManager;
import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import com.battleship.uibutton.ImgButton;
import com.battleship.uibutton.UiButton;
import com.battleship.uibutton.ZozoDecorator;
import com.battleship.views.tools.ContentPanel;
import com.battleship.views.tools.PagePanel;
import com.battleship.views.tools.UiDialog;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;





/**
 * 
 * @since   3 mars 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class HeadBar extends ContentPanel{
    //**************************************************************************
    // Variables - Constants
    //**************************************************************************
    private JLabel              l_title;
    private JLabel              l_music;
    private ImageIcon           title;
    private JPanel              p_logo;
    private JPanel              p_theme;
    private JComboBox           themes;
    
    
    
    
    
    //**************************************************************************
    // Initialization - Constructors
    //**************************************************************************
    /**
     * Constructor of the HeadBar
     * @param pParent parent application
     * @throws ExecError thrown if error (parent is null)
     */
    public HeadBar(JPanel pParent) throws ExecError{
        super(pParent);
        this.initComponents();
        this.initThemeComboBox();
        
        this.loadUI();
    }
    
    private void initComponents(){
        this.p_theme    = new JPanel();
        this.p_logo     = new JPanel();
        this.l_title    = new JLabel();
        this.l_music    = new JLabel("Music");
        this.themes     = new JComboBox();
        this            .setLayout(new BorderLayout());
        
        this            .add(l_title, BorderLayout.WEST);
        this.p_theme    .add(themes);
        this            .add(p_theme, BorderLayout.EAST);
    }
    
    private void initThemeComboBox() throws ExecError{
        for(String str : ThemeManager.getThemeManager().getAllThemeNames()){
            this.themes.addItem(str);
        }
        this.themes.setSelectedItem(ThemeManager.getTheme().getThemeName());
        this.themes.addActionListener(new ThemeListener());
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
        this.title = ThemeManager.getTheme().getImgIcon(426100);
        this.l_title.setIcon(title);
        this.repaint();
    }
    
    
    
    //**************************************************************************
    // ActionListener
    //**************************************************************************
    private class ThemeListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String themeName = (String)themes.getSelectedItem();
            try {
                ThemeManager.getThemeManager().loadTheme(themeName);
            } catch(ExecError ex) {
                DebugTrack.showErrMsg("Unable to load theme "+themeName);
                UiDialog.showWarning("Theme not valide", ex.getMessage());
            }
            themes.setSelectedItem(ThemeManager.getTheme().getThemeName());
            ((PagePanel)parentPage).loadUI();
        }
    }
}
