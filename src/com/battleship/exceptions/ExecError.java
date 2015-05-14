/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.exceptions;

import com.battleship.main.DebugTrack;
import java.util.ArrayList;





/**
 * <h1>ExecError</h1>
 * <p>
 * public class ExecError<br/>
 * extends Exception
 * </p>
 * 
 * <p>Exception thrown if a parameter is wrong</p>
 *
 * @since   Feb 9, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class ExecError extends Exception {
    //**************************************************************************
    // Variables - Constants
    //**************************************************************************
    private     final int           idError;
    private     final String        msg;
    private     String              extraData;





    //**************************************************************************
    // Constructors - Initialization
    //**************************************************************************
    /**
     * Create Error with default error number
     */
    public ExecError() {
        this.processParam(null);
        this.idError    = 0;
        this.msg        = loadErrorMessage();
    }
    
    /**
     * Create Error with a specific error number
     * @param ErrorNum  error id
     */
    public ExecError(int ErrorNum){
        this.processParam(null);
        this.idError    = ErrorNum;
        this.msg        = loadErrorMessage();
    }
    
    /**
     * Create an error with a specific error number and having extra data about 
     * this error, saved in pErrorInfo (Could be a string, ArrayList etc etc)
     * @param ErrorNum      error id
     * @param pErrorInfo    extra data about this error
     */
    public ExecError(int ErrorNum, Object pErrorInfo){
        this.processParam(pErrorInfo);
        this.idError    = ErrorNum;
        this.msg        = loadErrorMessage();
    }





    //**************************************************************************
    // Special function
    //**************************************************************************
    /**
     * Process parameters. Some error could have extra information. For instance, 
     * a theme could have trouble because of missing files. This files could be 
     * displayed in error message and passed at ExecError as a ArrayList parameters. 
     * This functions will process parameters given.
     * @param Object parameter given
     */
    private void processParam(Object param){
        this.extraData = "";
        if(param instanceof ArrayList){
            ArrayList list = (ArrayList) param;
            int counter = 0;
            for (Object str : list){
                counter++;
                if(counter%5 == 0){
                    this.extraData += "\n";
                }
                this.extraData += str.toString()+" - ";
            }
        }
        else if( param instanceof String){
            this.extraData = (String)param;
        }
    }





    //**************************************************************************
    // Functions
    //**************************************************************************
    /*
     * Load the error message from id error
     * Error family
     * 400 : error about loading (As page not found during a page loading)
     * @param data is some extra iformation about error
     */
    private String loadErrorMessage(){
        String str;
        switch (this.idError){
            
            //General error
            case 404:
                str = "Page not found :/";
                break;
            case 430:
                str = "Null controller in creation class "+this.extraData;
                break;
            
                
            //Theme error
            case 501:
                str = "Unable to display themes list";
                break;
            case 502:
                str = "Unable to load the theme!\n"
                       +"Theme "+this.extraData
                       +" doesn't exists in theme folder";
                break;
            case 503:
                str = "Unable to load theme, some file are missing!\n\n"
                       +"Files "+this.extraData+" are missing!";
                break;
            
                
            //XML DOM Parser error
            case 604:
                str = "Error with xml files";
                break;
                
                
            //SwingFactory error
            case 700:
                str = "Error in swingFactory";
                break;
                
                
                
            //Default error
            default:
                str = "Unkown error";
                break;
        }
        
        DebugTrack.showErrMsg(str);
        return str;
    }
    
    
    
    
    
    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Get id error
     * @return current id error
     */
    public int getIdError(){
        return this.idError;
    }
    
    @Override
    public String getMessage(){
        return this.msg;
    }
}
