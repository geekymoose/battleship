/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.exceptions;





/**
 * <h1>ExecError</h1>
 * <p>
 * public class ExecError<br/>
 * extends Exception
 * </p>
 * 
 * <p>Exception thrown if a parameter is wrong</p>
 *
 * @date    Feb 9, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class ExecError extends Exception {
    private     final int   idError;

    /**
     * Create Error with default error number
     */
    public ExecError() {
        idError = 0;
    }
    
    /**
     * Create Error with a specific error number
     * @param ErrorNum 
     */
    public ExecError(int ErrorNum){
        idError = ErrorNum;
    }
    
    /**
     * Get id error
     * @return current id error 
     */
    public int getIdError(){
        return this.idError;
    }
}
