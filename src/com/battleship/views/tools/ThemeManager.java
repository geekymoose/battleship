/* 
 * Creation : Mar 11, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.views.tools;

import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;





/**
 * <h1>ThemeManager</h1>
 * <p>public class ThemeManager</p>
 * <p>
 * Manage the theme. Work with class Theme (which save image etc). This class 
 * manage the themes loaded and available. Data for themes (As image etc) must 
 * be placed in a specific folder. Default theme folder is given at 
 * ThemeManager creation. It could be changed later.
 * </p>
 *
 * @date    Mar 14, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * 
 * @see Theme
 */
public class ThemeManager{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private     static ThemeManager     singleton           = null;
    private     HashMap<String,Theme>   listLoadedTheme;
    private     Theme                   currentTheme;
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a theme manager
     * @throws ExecError if unable to create ThemeManager
     */
    private ThemeManager() throws ExecError{
        DebugTrack.showInitMsg("Create Theme manager");
        this.listLoadedTheme    = new HashMap();
    }
    
    /**
     * Create theme manager and load default theme. Must be called before over 
     * function from ThemeManager
     * @throws ExecError if unable to create ThemeManager
     */
    public static void createThemeManager() throws ExecError{
        ThemeManager.singleton = new ThemeManager();
        ThemeManager.singleton.loadTheme(Config.getThemeConst_str("default-theme-name"));
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Load a theme and set this theme as current theme used. If this theme was 
     * already loaded during current session, it won't be loaded again but juste 
     * restored. If unable to load the new theme, current one will not be changed 
     * and an ExecError will be thrown
     * @param pName name's theme to load
     * @throws ExecError exception if unable to load this theme
     */
    public void loadTheme(String pName) throws ExecError{
        Theme t = this.listLoadedTheme.get(pName);
        if(t==null){
            t = new Theme(pName);
            this.listLoadedTheme.put(pName, t);
        }
        this.currentTheme = t;
    }
    
    
    /**
     * Return all theme found in the current theme folder (Where every theme 
     * are placed). Throw ExecError in case of trouble
     * @return ArrayList with all theme name
     * @throws ExecError Exception if unable to display themes (Wrong theme path)
     */
    public ArrayList<String> getAllThemeNames() throws ExecError{
        ArrayList<String> listThemeNames    = new ArrayList();
        File themeFolder                    = new File(Config.getThemeFolderPath());
        if(!themeFolder.isDirectory()){
            throw new ExecError(501);
        }
        return listThemeNames;
    }
    
    
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Get current theme used by application. createThemeManager must have been 
     * called before!!!
     * @return current theme
     */
    public static Theme getTheme(){
        return ThemeManager.singleton.currentTheme;
    }
    
    /**
     * Return ThemeManager,createThemeManager must have been called before!!!
     * @return ThemeManager
     */
    public static ThemeManager getThemeManager(){
        return ThemeManager.singleton;
    }
}