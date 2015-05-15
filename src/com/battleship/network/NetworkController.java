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
public class NetworkController {
    private Socket              clientSocket;
    private ObjectOutputStream  output;
    private ObjectInputStream   input;
    
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public NetworkController() {

    }
    
    
    //**************************************************************************
    // METHODS
    //**************************************************************************
    /**
     * Try to connect to server
     * @param ipServer
     * @return 
     */
    public boolean tryConnect(String ipServer) {
        try {
            this.clientSocket   = new Socket(ipServer, 5000);
            Connection connect  = new Connection(clientSocket, this);
            return true;
        } catch (Exception ex) {
            DebugTrack.showErrMsg("Unable to connect, error msg : "+ex.getMessage());
        }
        return false;
    }
    
    
    public void execRequest(Capsule pCapsule){
        Request rqt = pCapsule.getRequest();
    }
    

    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************
    public ObjectInputStream getInput(){
        return this.input;
    }
    
    public ObjectOutputStream getOutput(){
        return this.output;
    }
    
    public void setInput(ObjectInputStream obj){
        this.input = obj;
    }
    
    public void setOutput(ObjectOutputStream obj){
        this.output = obj;
    }
}
