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
public class NetworkController {

    private Socket clientSocket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************

    public NetworkController() {

    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    public boolean tryConnect(String ipServer) {
        boolean flag = false;
        try {
            clientSocket = new Socket(ipServer, 10000);
            flag = true;
            //CONNEXION REUSSI
        } catch (Exception ex) {
            //ERREUR de CONNEXION DONC INVOCATION MESSAGE ERREUR
            flag = false;
        }

        if (flag == true) {
            Connection connect = new Connection(clientSocket, this); 
        }
        return flag;
    }
    
    public void execRequest(Capsule cpsl){
        Request rqt = cpsl.getRequest();
        
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
