/* 
 * Creation : 28 March 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.asset;

import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import java.awt.Dimension;
import java.util.HashMap;





/**
 * <h1>Config</h1>
 * <p>public class Config</p>
 * <p>
 * Keep all program data as constants used, path etc. Create all important 
 * class program need to run properly.
 * </p><p>
 * All constants data are from xml files. These files are loaded in this 
 * class thanks to a xml parser. DOMParser is used for this work. See 
 * DOMParser class for further information about its running process.
 * </p>
 *
 * @since   Mar 28, 2015
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
    public  static      String      themeConfigPath;
    public  static      String      dimConfigPath;
    public  static      String      gameConfigPath;
    public  static      String      rootsPath;
    
    
    //List with all constants used for application
    private static      HashMap<String, Dimension>  l_dimConfig_dim;
    private static      HashMap<String, Integer>    l_dimConfig_int;
    private static      HashMap<String, Integer>    l_gameConfig_int;
    private static      HashMap<String, Double>     l_gameConfig_double;
    private static      HashMap<String, String>     l_gameConfig_str;
    private static      HashMap<String, String>     l_themeConfig_str;
    private static      HashMap<String, Integer>    l_roots;
    
    
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
        this.dimConfigPath          = constantsPath+"dimConfig.xml";
        this.themeConfigPath        = constantsPath+"themeConfig.xml";
        this.gameConfigPath         = constantsPath+"gameConfig.xml";
    }
    
    
    /**
     * Create configuration, initialize every element used by program
     * @throws ExecError thrown if an element wasn't successfully created
     */
    public static void createConfig() throws ExecError{
        Config.singleton            = new Config();
        DOMParser                   .createDOMParser(); //XML parser
        
        //Root constants 
        Config.l_roots              = DOMParser.getIntegerConstants(rootsPath);
        
        
        //Load app constants
        Config.l_dimConfig_dim      = DOMParser.getDimensionConstants(dimConfigPath);
        Config.l_dimConfig_int      = DOMParser.getIntegerConstants(dimConfigPath);
        Config.l_gameConfig_int     = DOMParser.getIntegerConstants(gameConfigPath);
        Config.l_gameConfig_str     = DOMParser.getStringConstants(gameConfigPath);
        Config.l_gameConfig_double  = DOMParser.getDoubleConstants(gameConfigPath);
        
        //Theme constants
        Config.l_themeConfig_str    = DOMParser.getStringConstants(themeConfigPath);
        Config.themeFolderPath      = Config.getThemeValues_str("default-theme-folder-path");
        
        //Theme image value
        Config.l_staticImgNames     = DOMParser.getThemeImgName(themeConfigPath, "static");
        Config.l_dynamicImageNames  = DOMParser.getThemeImgName(themeConfigPath, "dynamic");
        
        //Create theme manager, must be called at the end!!
        Session.createSession();   //Create user session
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
    public static int getRootsValues(String pName){
        Integer root = Config.l_roots.get(pName);
        DebugTrack.isValidConstantsName(root, pName);
        return root;
    }
    
    /**
     * Return dimension constants with Dimension format
     * @param pName constants name
     * @return Dimension for this constants
     */
    public static Dimension getDimValues_dim(String pName){
        Dimension dim = Config.l_dimConfig_dim.get(pName);
        DebugTrack.isValidConstantsName(dim, pName);
        return dim;
    }
    
    /**
     * Return dimension constants with int format
     * @param pName constants name
     * @return Integer value of a dimension
     */
    public static int getDimValues_int(String pName){
        Integer dim = Config.l_dimConfig_int.get(pName);
        DebugTrack.isValidConstantsName(dim, pName);
        return dim;
    }
    
    /**
     * Return game constants with int value
     * @param pName constants name
     * @return int constants for the game
     */
    public static int getGameValues_int(String pName){
        Integer val = Config.l_gameConfig_int.get(pName);
        DebugTrack.isValidConstantsName(val, pName);
        return val;
    }
    
    /**
     * Return game constants with double value
     * @param pName constants name
     * @return double constants for the game
     */
    public static double getGameValues_double(String pName){
        double val = Config.l_gameConfig_double.get(pName);
        DebugTrack.isValidConstantsName(val, pName);
        return val;
    }
    
    /**
     * Return game constants with string value
     * @param pName constants name
     * @return String value
     */
    public static String getGameValues_str(String pName){
        String val = Config.l_gameConfig_str.get(pName);
        DebugTrack.isValidConstantsName(val, pName);
        return val;
    }
    
    /**
     * Return a string constants used for theme as theme path etc
     * @param pName constants name
     * @return String constants
     */
    public static String getThemeValues_str(String pName){
        String str = Config.l_themeConfig_str.get(pName);
        DebugTrack.isValidConstantsName(str, pName);
        return str;
    }
    
    /**
     * Return all image name to load (Static image only)
     * @return HashMap of String : String
     */
    public static HashMap<String, String> getStaticImgNames(){
        return Config.l_staticImgNames;
    }
    
    /**
     * Return all image name to load (Dynamic image only)
     * @return HashMap of String : String
     */
    public static HashMap<String, String> getDynamicImgNames(){
        return Config.l_dynamicImageNames;
    }
}
