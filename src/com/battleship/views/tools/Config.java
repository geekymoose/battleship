/* 
 * Creation : 28 March 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.tools;

import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import com.battleship.models.game.Session;
import java.awt.Dimension;
import java.util.HashMap;





/**
 * <h1>Config</h1>
 * <p>public class Config</p>
 * <p>
 * Keep all program data as constants used, path etc. Create all important 
 * class program need to run properly. 
 * <br/>
 * All constants data are from xml files. These files are loaded in this 
 * class thanks to a xml parser. DOMParser is used for this work. See 
 * DOMParser class for further information about its running process.
 * </p>
 *
 * @date    Mar 28, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 * 
 * @see DOMParser
 */
public class Config {
    //**************************************************************************
    // Constants
    //**************************************************************************
    private static      Config      singleton = null;
    
    //Constants for Parsing (Public mode)
    public  static      String      themeConstantsPath;
    public  static      String      dimConstantsPath;
    public  static      String      displayConstantsPath;
    public  static      String      gameConstantsPath;
    public  static      String      timerConstantsPath;
    public  static      String      rootsPath;
    
    
    //List with all constants used for application
    private static      HashMap<String, Dimension>  l_dimConst_dim;
    private static      HashMap<String, Integer>    l_dimConst_int;
    private static      HashMap<String, String>     l_displayConst_str;
    private static      HashMap<String, Integer>    l_gameConst_int;
    private static      HashMap<String, String>     l_themeConst_str;
    private static      HashMap<String, Integer>    l_timerConst;
    private static      HashMap<String, Integer>    l_rootsConst;
    
    
    //List with loaded image name / ext
    private static      HashMap<String, String>      l_staticImgNames;
    private static      HashMap<String, String>      l_dynamicImageNames;
    
    
    
    //**************************************************************************
    // Variables
    //**************************************************************************
    private static String   themeFolderPath;
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /*
     * Create a new Config instance. All constants are loaded from xml files, but this 
     * files are located somewhere Parser need to know. Config will create 
     * these path.
     */
    private Config(){
        String constantsPath        = "src/com/battleship/constants/";
        this.rootsPath              = constantsPath+"roots.xml";
        this.themeConstantsPath     = constantsPath+"themeConstants.xml";
        this.dimConstantsPath       = constantsPath+"dimConstants.xml";
        this.displayConstantsPath   = constantsPath+"displayConstants.xml";
        this.gameConstantsPath      = constantsPath+"gameConstants.xml";
        this.timerConstantsPath     = constantsPath+"timerConstants.xml";
    }
    
    
    /**
     * Create configuration, initialize every element used by program
     * @throws ExecError thrown if an element wasn't successfully created
     */
    public static void createConfig() throws ExecError{
        Config.singleton            = new Config();
        DOMParser                   .createDOMParser(); //XML parser
        Session                     .createSession();   //Create user session
        
        //Root constants 
        Config.l_rootsConst         = DOMParser.getIntegerConstants(rootsPath);
        
        
        //Load app constants
        Config.l_dimConst_dim       = DOMParser.getDimensionConstants(dimConstantsPath);
        Config.l_dimConst_int       = DOMParser.getIntegerConstants(dimConstantsPath);
        Config.l_displayConst_str   = DOMParser.getStringConstants(displayConstantsPath);
        Config.l_gameConst_int      = DOMParser.getIntegerConstants(gameConstantsPath);
        
        //Theme constants
        Config.l_themeConst_str     = DOMParser.getStringConstants(themeConstantsPath);
        Config.themeFolderPath      = Config.getThemeConst_str("default-theme-folder-path");
        
        //Theme image value
        Config.l_staticImgNames     = DOMParser.getThemeImgName(themeConstantsPath, "static");
        Config.l_dynamicImageNames  = DOMParser.getThemeImgName(themeConstantsPath, "dynamic");
        
        //Timer constants
        Config.l_timerConst         = DOMParser.getIntegerConstants(timerConstantsPath);
        
        //Create theme manager, must be called at the end!!
        ThemeManager.createThemeManager();
    }
    
    
    
    
    
    //**************************************************************************
    // Getters - Setters for variables
    //**************************************************************************
    /**
     * Return current theme path value
     * @return theme path
     */
    public static String getThemeFolderPath(){
        return Config.themeFolderPath;
    }
    
    
    
    
    
    //**************************************************************************
    // Getters - Setters for Constants
    //**************************************************************************
    public static int getRootsConst(String pName){
        Integer root = Config.l_rootsConst.get(pName);
        DebugTrack.isValidConstantsName(root, pName);
        return root;
    }
    
    /**
     * Return dimension constants with Dimension format
     * @param pName
     * @return 
     */
    public static Dimension getDimConst_dim(String pName){
        Dimension dim = Config.l_dimConst_dim.get(pName);
        DebugTrack.isValidConstantsName(dim, pName);
        return dim;
    }
    
    /**
     * Return dimension constants with int format
     * @param pName
     * @return 
     */
    public static int getDimConst_int(String pName){
        Integer dim = Config.l_dimConst_int.get(pName);
        DebugTrack.isValidConstantsName(dim, pName);
        return dim;
    }
    
    /**
     * Return display constants with string format
     * @param pName
     * @return 
     */
    public static String getDisplayConst_str(String pName){
        String str = Config.l_displayConst_str.get(pName);
        DebugTrack.isValidConstantsName(str, pName);
        return str;
    }
    
    /**
     * Return game constants with int value
     * @param pName
     * @return 
     */
    public static int getGameConst_int(String pName){
        Integer val = Config.l_gameConst_int.get(pName);
        DebugTrack.isValidConstantsName(val, pName);
        return val;
    }
    
    /**
     * Return a timer constants
     * @param pName timer constants name
     * @return 
     */
    public static int getTimerConst(String pName){
        Integer val = Config.l_timerConst.get(pName);
        DebugTrack.isValidConstantsName(val, pName);
        return val;
    }
    
    /**
     * return theme constants
     * @param pName
     * @return 
     */
    public static String getThemeConst_str(String pName){
        String str = Config.l_themeConst_str.get(pName);
        DebugTrack.isValidConstantsName(str, pName);
        return str;
    }
    
    /**
     * Return all image name to load (Static image only)
     * @return 
     */
    public static HashMap<String, String> getStaticImgNames(){
        return Config.l_staticImgNames;
    }
    
    /**
     * Return all image name to load (Dynamic image only)
     * @return 
     */
    public static HashMap<String, String> getDynamicImgNames(){
        return Config.l_dynamicImageNames;
    }
}
