/* 
 * Creation : 5 mai 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @since   5 mai 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
class Connection implements Runnable {

    Thread              thread;
    Socket              clientSocket;
    NetworkController   nc;

    Connection(Socket skt, NetworkController nc) {
        thread          = new Thread(this, "RunServer");
        clientSocket    = skt;
        this.nc         = nc;
        thread.start();
    }

    @Override
    public void run() {
        try {
            try {
                nc.setOutput(new ObjectOutputStream(clientSocket.getOutputStream()));
                nc.getOutput().flush();
                nc.setInput(new ObjectInputStream(clientSocket.getInputStream()));
                //CONNECTED
            } catch (Exception e) {
                //ERREUR DE CONNECTION
            }
            ListenRequest listen = new ListenRequest(nc);
        } catch (Exception ex) {
            //IMPOSSIBLE D'AVOIR UN THREAD D'ECOUTE, ENVOYER MESSAGE AU SERVER ET 
            // RECOMMENCER
        }
    }
}
