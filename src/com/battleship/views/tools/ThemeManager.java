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
 * <p>Manage the theme. Work with class Theme (which save image etc). This class 
 * manage the themes loaded and available. Data for themes (As image etc) must 
 * be placed in a specific folder. Default theme folder is given at 
 * ThemeManager creation. It could be changed later. </p>
 *
 * @date    Mar 14, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * 
 * @see ThemeConstants
 * @see Theme
 */
public class ThemeManager{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private     HashMap<String,Theme>   listLoadedTheme;
    private     Theme                   currentTheme;
    private     String                  themePath;
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a theme manager
     * @param pDefaultPath  Default theme folder path. For example, if the folder 
     *                      with theme is in /hone/user1/apply/theme/
     *                      pDefaultPath must be this path (Do not forget the 
     *                      last '/'
     */
    public ThemeManager(String pDefaultPath){
        DebugTrack.showInitMsg("Create Theme manager");
        this.listLoadedTheme    = new HashMap();
        this.themePath          = pDefaultPath;
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
            t = new Theme(this.themePath, pName);
            this.listLoadedTheme.put(pName, t);
        }
        this.currentTheme = t;
    }
    
    
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return all theme found in the current theme folder (Where every theme 
     * are placed). Throw ExecError in case of trouble
     * @return ArrayList with all theme name
     * @throws ExecError Exception if unable to display themes (Wrong theme path)
     */
    public ArrayList<String> getThemeNames() throws ExecError{
        ArrayList<String> listThemeNames    = new ArrayList();
        File themeFolder                    = new File(this.themePath);
        
        if(!themeFolder.isDirectory()){
            throw new ExecError(501);
        }
        return listThemeNames;
    }
    
    /**
     * Get current theme used by application
     * @return current theme
     */
    public Theme getCurrentTheme(){
        return this.currentTheme;
    }
}