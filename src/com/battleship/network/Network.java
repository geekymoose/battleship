/* 
 * Creation : 5 mai 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.network;

import com.battleship.asset.Session;
import com.battleship.exceptions.LanError;
import com.battleship.main.DebugTrack;
import com.battleship.models.game.Model;
import com.battleship.observers.ObservableLan;
import com.battleship.observers.ObserverLan;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @since   5 mai 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class Network extends Model implements ObservableLan{
    //**************************************************************************
    // Variables - Constants
    //**************************************************************************
    protected   ArrayList<ObserverLan> listObservers;
    private Socket                      clientSocket;
    private ObjectOutputStream          output;
    private ObjectInputStream           input;
    private int                         state = 0;
    private Thread                      thread;
    
    
    //**************************************************************************
    // Constructor - Initialisation
    //**************************************************************************
    public Network(){
        this.listObservers = new ArrayList();
    }
    
    
    //**************************************************************************
    // METHODS
    //**************************************************************************
    /**
     * Try to establish a connection with the server
     * @param ipServer server IP
     * @throws LanError thrown if unable to connect
     */
    public void connectToIP(String ipServer) throws LanError {
        if(Session.isConnected()){
            DebugTrack.showErrMsg("You are already connected");
            throw new LanError("You are already connected");
        }
        try {
            this.clientSocket   = new Socket(ipServer, 5000);
            this.setOutput(new ObjectOutputStream(clientSocket.getOutputStream()));
            this.getOutput().flush();
            this.setInput(new ObjectInputStream(clientSocket.getInputStream()));
            ListenRequest listen = new ListenRequest(this);
            Session.setNetwork(this);
        } catch (Exception ex) {
            DebugTrack.showErrMsg("Unable to connect to server! "+ex.getMessage());
            throw new LanError("Unable to connect to server! "+ex.getMessage());
        }
    }
    
    
    /**
     * Send a capsule to the server
     * @param cpsl 
     */
    public void sendCapsule(Capsule cpsl){
        try {
            output.writeObject(cpsl);
            output.flush();
        } catch (IOException ex) {
            // MESSAGE D'ERREUR
        }
    }
    
    /**
     * Execute the request received
     * @param cpsl 
     */
    public void execRequest(Capsule cpsl){
        Request rqt = cpsl.getRequest();
        
        if(rqt == Request.LIST_OF_GAMES){
            ArrayList<ServerGame> l = (ArrayList<ServerGame>)cpsl.getObject();
            this.notifyLanObservers(l);
        }
        else if(rqt == Request.JOIN_SUCCEED){
            this.notifyLanObservers(Request.JOIN_SUCCEED);
        }
        else if(rqt == Request.IS_FULL){
            this.notifyLanObservers(Request.IS_FULL);
        }
        else if(rqt == Request.NICKNAME){
            //FONCTION QUI ENREGISTRE LE PSEUDO SI DANS LE BON ETAT
        }
        else if(rqt == Request.FLEET_ENEMY){
            // FONCTION QUI ENREGISTRE LA FLOTTE ENEMIE
        }
        else if(rqt == Request.START){
            this.notifyLanObservers(Request.START);
        }
        else if(rqt == Request.SELECTED_SQUARE){
            //FONCTION QUI MET A JOUR LA POSITION DU CURSEUR ENEMI
        }
        else if(rqt == Request.SHOOT){
            // FONCTION QUI TIRE SUR LA POSITION AVEC UN OBJET PRECISANT L'ARME ET LA POSITION OU UNE GRILLE DE BOOLEAN
        }
        else if(rqt == Request.YOUR_TURN){
            //FONCTION QUI PERMET DE COMMENCER SON PROPRE TOUR
            // QUAND ON TIRE ON TERMINE EGALEMENT SON TOUR
        }
        else if(rqt == Request.END_OF_GAME){
            // AFFICHE MENU DE VICTOIRE OU DEFAITE SUIVANT LA VALEUR DE L'OBJET
            // 1 - VICTOIRE
            // 2 - DEFAITE
            // 3 - ABANDON DE L'ADVERSAIRE = VICTOIRE
            // 4 - PROBLEME etc...
        }
        else if(rqt == Request.MSG_CHAT){
            // REDIRIGER LE MESSAGE VERS LE TCHAT
        }
        else if(rqt == Request.SERVER_CLOSURE){
            // FERMETURE DE OUTPUT ET INPUT ET REDIRECTION VERS MENU PRINCIPAL
            Session.setNetwork(null);
        } 
        else if(rqt == Request.GET_GAME_SERVER){
            ServerGame g = (ServerGame)cpsl.getObject();
            this.notifyLanObservers(g);
        } 
        else if(rqt == Request.PLACE_BOAT){
            int [][] tab = (int[][])cpsl.getObject();
            this.notifyLanObservers(tab);
        }
        else{
            // NE RIEN FAIRE 
        }
    }
    
    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************
    /**
     * Set state
     * @param s 
     */
    public void setState(int s){
        this.state = s;
    }
    
    /**
     * Get the input
     * @return 
     */
    public ObjectInputStream getInput(){
        return this.input;
    }
    
    /**
     * Get the output
     * @return 
     */
    public ObjectOutputStream getOutput(){
        return this.output;
    }
    
    /**
     * Set input
     * @param obj 
     */
    public void setInput(ObjectInputStream obj){
        this.input = obj;
    }
    
    /**
     * Set output
     * @param obj 
     */
    public void setOutput(ObjectOutputStream obj){
        this.output = obj;
    }

    
    //**************************************************************************
    // Observers
    //**************************************************************************
    @Override
    public void addLanObserver(ObserverLan obs){
        if(obs!=null){
            this.listObservers.add(obs);
        }
    }

    @Override
    public void deleteLanObserver(ObserverLan o){
        if(o!=null){
            this.listObservers.remove(o);
        }
    }
    
    @Override
    public void deleteAllLanObserver(){
        this.listObservers = new ArrayList();
    }
    
    @Override
    public void notifyLanObservers(Object obj){
        for(ObserverLan obs : this.listObservers){
            obs.updateLan(this, obj);
        }
    }
}
