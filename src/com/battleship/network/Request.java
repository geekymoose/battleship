
package com.battleship.network;

import java.io.Serializable;

/**
 *
 * @author Anthony
 */
public enum Request implements Serializable {
    NICKNAME,
    CREATE_GAME,
    JOIN_GAME,
    LEAVE_TMP_GAME,
    SURRENDER,
    JOIN_AS_SPECTATOR,
    SHOOT,
    MSG_CHAT,
    READY_FOR_PLAYING,
    ERROR,
    LIST_OF_GAMES,
    MSG_SERVER,
    DISCONNECT,
    JOIN_SUCCED,
    LEAVE_TMP_SUCCED,
    START,
    WIN,
    LOSE,
}
