/* 
 * Creation : 17 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.main;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JOptionPane;





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
    public static final boolean  debug_mode = true;
    //**************************************************************************
    // Data process
    //**************************************************************************
    public static void isValidConstantsName(Object o, String name){
        if(debug_mode == false){return;}
        if(o==null){
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            String  fctName     = stackTraceElements[3].getClassName();
            String  fileName    = stackTraceElements[3].getFileName();
            String  methodName  = stackTraceElements[3].getMethodName();
            int     nbLine      = stackTraceElements[3].getLineNumber();
            
            String msg = "Constants \""+name+"\" doesn't exists!\n"
                         +"\tFile : "+fileName+"\n"
                         +"\tMethod : "+methodName+"\n"
                         +"\tAt line : "+nbLine+"\n";
            DebugTrack.showErrMsg(msg);
            DebugTrack.showError("Error", msg);
            System.exit(0);
        }
    }
    
    private static String getStackTraceChild(int nb){
        if(debug_mode == false){return "";}
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String  fctName     = stackTraceElements[nb].getClassName();
        String  fileName    = stackTraceElements[nb].getFileName();
        String  methodName  = stackTraceElements[nb].getMethodName();
        int     nbLine      = stackTraceElements[nb].getLineNumber();

        String msg = "Error data : \n"
                     +"\tFile : "+fileName+"\n"
                     +"\tMethod : "+methodName+"\n"
                     +"\tAt line : "+nbLine+"\n";
        DebugTrack.showErrMsg(msg);
        //DebugTrack.showError("Error", msg);
        return msg;
    }
    
    
    
    //**************************************************************************
    // Data Information Message
    //**************************************************************************
    /**
     * Display message about initialization as a class creation.
     * @param str message to display
     */
    public static void showInitMsg(String str){
        if(debug_mode == false){return;}
        System.out.println(" * "+str);
    }
    
    /**
     * Display message about program execution as button processed etc
     * @param str message to display
     */
    public static void showExecMsg(String str){
        if(debug_mode == false){return;}
        System.out.println(" -> "+str);
    }
    
    /**
     * Display data about a constant created (From XML file or other)
     * @param name      constant name in program execution
     * @param value     constant actual value
     */
    public static void showInitConstant(String name, String value){
        if(debug_mode == false){return;}
        while(name.length()<15){ name += " "; }
        System.out.println(" *** Constants -> name : "+name+"\t-\tvalue : "+value);
    }
    
    /**
     * Display data about a constant created (From XML file or other)
     * @param type  data type
     * @param value value created
     */
    public static void showInitConstant(String type, int value){
        if(debug_mode == false){return;}
        while(type.length()<15){ type += " "; }
        System.out.println(" *** Constants -> name : "+type+"\t-\tvalue : "+value);
    }
    
    /**
     * Display data about a constant created (From XML file or other)
     * @param type  data type
     * @param value value created
     */
    public static void showInitConstant(String type, Dimension value){
        if(debug_mode == false){return;}
        while(type.length()<15){ type += " "; }
        System.out.println(" *** Constants -> name : "+type+"\t-\tvalue : "+value.toString());
    }
    
    /**
     * Display a message about loaded element as image for a theme. 
     * will show a formated message with type of data loaded and then, value
     * @param type  data type
     * @param value value created
     */
    public static void showInitData(String type, String value){
        if(debug_mode == false){return;}
        while(type.length()<15){ type += " "; }
        System.out.println(" *** Loaded -> type : "+type+"\t-\tvalue : "+value);
    }
    
    /**
     * Display a message about loaded element as image for a theme. 
     * will show a formated message with type of data loaded and then, value
     * @param type  data type
     * @param data  ArrayList of data
     */
    public static void showInitData(String type, ArrayList<String> data){
        if(debug_mode == false){return;}
        for(String str:data){
            DebugTrack.showInitData("Theme path", str);
        }
    }
    
    
    
    
    
    //**************************************************************************
    // Error message
    //**************************************************************************
    /**
     * Display error message, then return line
     * @param str message to display
     */
    public static void showErrMsg(String str){
        if(debug_mode == false){return;}
        System.err.println(str);
    }
    
    
    
    
    
    //**************************************************************************
    // Debug Dialog Message
    //**************************************************************************
    /**
     * Display a JDialog with error message
     * @param pTitle    dialog title (Displayed at the window top)
     * @param pMsg      Message to display
     */
    public static void showError(String pTitle, String pMsg){
        if(debug_mode == false){return;}
        JOptionPane opt = new JOptionPane();
        opt.showMessageDialog(null, pMsg, pTitle, JOptionPane.ERROR_MESSAGE);
    }
    
    
    
    
    
    //**************************************************************************
    // Application debug for data
    //**************************************************************************
    /**
     * Display data from an object : display toString() value
     * @param o object to display
     */
    public static void showObjectToString(Object o){
        if(debug_mode == false){return;}
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String  fctName     = stackTraceElements[2].getClassName();
        System.out.print("Debug in "+fctName+" - msg : ");
        if(o == null){
            String str = "Object given is null\n\n  *** Stack state ***\n\n";
            str += DebugTrack.getStackTraceChild(0);
            str += DebugTrack.getStackTraceChild(1);
            str += DebugTrack.getStackTraceChild(2);
            DebugTrack.showErrMsg(str);
            System.exit(0);
        }else{
            System.out.println(o.toString());
        }
    }
}
