/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.exceptions;





/**
 * <h1>ForbiddenAction</h1>
 * <p>
 * public class ForbiddenAction<br/>
 * extends Exception
 * </p>
 *
 * @since   Feb 9, 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class ForbiddenAction extends Exception {

    /**
     * Creates a new instance of <code>ForbiddenAction</code> without detail message.
     */
    public ForbiddenAction() {
    }


    /**
     * Constructs an instance of <code>ForbiddenAction</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ForbiddenAction(String msg) {
        super(msg);
    }
}
