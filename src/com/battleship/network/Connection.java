/* 
 * Creation : 5 mai 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.network;

import com.battleship.main.DebugTrack;
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
    private Thread              thread;
    private Socket              clientSocket;
    private NetworkController   networkController;

    public Connection(Socket skt, NetworkController nc) {
        this.thread             = new Thread(this, "RunServer");
        this.clientSocket       = skt;
        this.networkController  = nc;
        this.thread.start();
    }

    @Override
    public void run() {
        try {
            try {
                networkController.setOutput(new ObjectOutputStream(clientSocket.getOutputStream()));
                networkController.getOutput().flush();
                networkController.setInput(new ObjectInputStream(clientSocket.getInputStream()));
                DebugTrack.showExecMsg("Connected");
            } 
            catch (Exception e) {
                DebugTrack.showErrMsg("Connection ERROR : Unable to connect");
            }
            ListenRequest listen = new ListenRequest(networkController);
        }
        catch (Exception ex) {
            DebugTrack.showErrMsg("Listen server ERROR");
            //IMPOSSIBLE D'AVOIR UN THREAD D'ECOUTE, ENVOYER MESSAGE AU SERVER ET 
            //RECOMMENCER
        }
    }
}
