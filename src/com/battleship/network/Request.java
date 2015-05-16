
package com.battleship.network;

import java.io.Serializable;

/**
 *
 * @author Anthony
 */
public enum Request implements Serializable {
    NICKNAME,
    CREATE_GAME,
    IS_FULL,
    JOIN_GAME,
    LEAVE_TMP_GAME,
    SURRENDER,
    SHOOT,
    MSG_CHAT,
    READY_FOR_PLAYING,
    ERROR,
    LIST_OF_GAMES,
    MSG_SERVER,
    DISCONNECT,
    JOIN_SUCCEED,
    LEAVE_TMP_SUCCEED,
    START,
    END_OF_GAME,
    FLEET_ENEMY,
    SELECTED_SQUARE,
    YOUR_TURN,
    SERVER_CLOSURE,
    GET_GAME_SERVER, 
    PLACE_BOAT;
}
