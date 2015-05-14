/*
 * Creation:    May 14, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.exceptions;



/**
 * <h1>LanError</h1>
 * <p>
 * public class LanError<br/>
 * extends Exception
 * </p>
 *
 * @since   May 14, 2015
 * @author  Constantin MASSON
 */
public class LanError extends Exception {

    /**
     * Creates a new instance of <code>LanError</code> without detail message.
     */
    public LanError() {
    }


    /**
     * Constructs an instance of <code>LanError</code> with the specified detail message.
     * @param msg the detail message.
     */
    public LanError(String msg) {
        super(msg);
    }
}
