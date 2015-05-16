/* 
 * Creation : 3 mai 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package serverbattleship;

import com.battleship.network.Capsule;
import com.battleship.network.Request;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @date 3 mai 2015
 * @author Constantin MASSON
 * @author Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class ReceiveMsgs implements Runnable {

    int thread_id;
    Controller c;
    ServerSocket serverSocket;
    Socket socket;
    ReceiveMsgs ob1;
    Thread t;
    Capsule capsuleReceived;
    Player player;
    boolean flag = true;

    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public ReceiveMsgs(Player p, String nameThread, Controller controller, ServerSocket serverSocket, Socket socket) {
        this.thread_id = p.getPlayerId();
        this.c = controller;
        this.serverSocket = serverSocket;
        this.socket = socket;
        this.player = p;
        t = new Thread(this, nameThread);
        t.start();
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    @Override
    public void run() {
        do {
            try {
                capsuleReceived = (Capsule) player.getInput().readObject();
                c.printMsg(player.getPlayerNickname() + " ---[" + capsuleReceived.getRequest().toString() + "] ---> Server\n");
                c.execRequest(capsuleReceived, player);
                if(capsuleReceived.getRequest() == Request.DISCONNECT){
                    flag = false;
                }
            } catch (Exception e) {
            }// server can't receive message from client, set sentence to "AuRevoir", exit from do-while loop and then close thread with client

        } while (flag);
    }
    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************

}
