/* 
 * Creation : 5 mai 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @date 5 mai 2015
 * @author Constantin MASSON
 * @author Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class NetworkController {

    private Socket clientSocket;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private int state = 0;

    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************

    public NetworkController() {

    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    /**
     * Try to etablish a connection with the server
     * @param ipServer
     * @return 
     */
    public boolean connectToIP(String ipServer) {
        boolean flag = false;
        try {
            clientSocket = new Socket(ipServer, 10000);
            flag = true;
            //CONNEXION REUSSI
        } catch (Exception ex) {
            //ERREUR de CONNEXION DONC MESSAGE ERREUR
            flag = false;
        }
        if (flag == true) {
            Connection connect = new Connection(clientSocket, this); 
        }
        return flag;
    }
    
    /**
     * Execute the request received
     * @param cpsl 
     */
    public void execRequest(Capsule cpsl){
        Request rqt = cpsl.getRequest();
        
        if(rqt == Request.LIST_OF_GAMES){
            //RAFRAICHIR LA PAGE DE REQUETTE SI DANS LE BON ETAT sinon rien
        }
        else if(rqt == Request.JOIN_SUCCEED){
            // FONCTION QUI CHANGE DE PAGE ET D'ETAT -> ETAT 2
        }
        else if(rqt == Request.IS_FULL){
            //FONCTION QUI AFFICHE UN MESSAGE D'ERREUR 'desolé partie pleine'
            // ETAT 1
        }
        else if(rqt == Request.NICKNAME){
            //FONCTION QUI ENREGISTRE LE PSEUDO SI DANS LE BON ETAT
        }
        else if(rqt == Request.FLEET_ENEMY){
            // FONCTION QUI ENREGISTRE LA FLOTTE ENEMIE
        }
        else if(rqt == Request.START){
            // FONCTION QUI LANCE LA PARTIE LE JOUEUR QUI COMMENCE EST PRECISE DANS l'obj
            // PAGE AVEC GRILLES ET TCHAT
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
        }
        else{
            // NE RIEN FAIRE 
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
}
