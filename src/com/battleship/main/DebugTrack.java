/* 
 * Creation : 17 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.main;





/**
 * <h1>DebugTrack</h1>
 * <p>public abstract class DebugTrack</p>
 * <p>Create a debug trace for data</p>
 *
 * @date    Mar 12, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public abstract class DebugTrack {
    
    //**************************************************************************
    // Data Information Message
    //**************************************************************************
    /**
     * Display message about initialization as a class creation.
     * @param str message to display
     */
    public static void showInitMsg(String str){
        System.out.println(" * "+str);
    }
    
    /**
     * Display message about program execution as button processed etc
     * @param str message to display
     */
    public static void showExecMsg(String str){
        System.out.println(" -> "+str);
    }
    
    /**
     * Display data about a constant created (From XML file or other)
     * @param name      constant name in program execution
     * @param value     constant actual value
     */
    public static void showInitConstant(String name, String value){
        while(name.length()<15){ name += " "; }
        System.out.println(" * Constants -> name : "+name+"\t-\tvalue : "+value);
    }
    
    /**
     * Display a message about loaded element as image for a theme. 
     * will show a formated message with type of data loaded and then, value
     * @param type  data type
     * @param value value created
     */
    public static void showInitData(String type, String value){
        while(type.length()<15){ type += " "; }
        System.out.println(" *** Loaded -> type : "+type+"\t-\tvalue : "+value);
    }
    
    
    
    
    
    //**************************************************************************
    // Error message
    //**************************************************************************
    /**
     * Display error message, then return line
     * @param str message to display
     */
    public static void showErrMsg(String str){
        System.err.println(str);
    }
}
