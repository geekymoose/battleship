/* 
 * Creation : 3 mai 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package serverbattleship;


import com.battleship.network.Capsule;
import com.battleship.network.ServerGame;
import com.battleship.network.Request;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @date 3 mai 2015
 * @author Constantin MASSON
 * @author Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class Controller {

    private Model m;
    private ServerBattleship v;

    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public Controller(Model m, ServerBattleship view) {
        this.m = m;
        this.v = view;
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    /**
     * Execute a request to the server
     *
     * @param cpsl
     * @param p
     */
    public void execRequest(Capsule cpsl, Player p) {
        Request rqt = cpsl.getRequest();
        
        if (rqt == Request.CREATE_GAME) {
            createGame(cpsl, p);
        } 
        else if (rqt == Request.DISCONNECT) {
            if(p.getCurrentGame() != -1){
                sendCapsuletoPlayersInGame(new Capsule(Request.END_OF_GAME, null),p, false);
            }            
            m.removePlayer(p);
        } 
        else if (rqt == Request.ERROR) {

        } 
        else if (rqt == Request.NICKNAME) {
            p.setPlayerNickname((String) cpsl.getObject());
        } 
        else if (rqt == Request.LEAVE_TMP_GAME) {
            leaveTmpGame(cpsl, p);
        } 
        else if (rqt == Request.MSG_CHAT) {
            //printMsg("RECEIVED -> "+(String)cpsl.getObject());
            sendCapsuletoPlayersInGame(cpsl, p, false);
        } 
        else if (rqt == Request.JOIN_GAME) {
            joinGame(cpsl, p);
        } 
        else if (rqt == Request.READY_FOR_PLAYING) {
            p.setReady(true);
            tryStartGame(p);
        } 
        else if(rqt == Request.SHOOT){
            sendCapsuletoPlayersInGame(cpsl,p, false);
        } 
        else if(rqt == Request.SURRENDER){
            surrenderAndleaveGame(p);
        } 
        else if(rqt == Request.LIST_OF_GAMES){
            sendCapsuletoPlayer(new Capsule(Request.LIST_OF_GAMES, m.getCurrentGamesList()), p);
            
        }
        else if(rqt == Request.WAITING_ROOM){
            sendWaitingRoom(cpsl, p);
        }
        else if(rqt == Request.GET_GAME_SERVER){
            getGameServer(cpsl, p);
        }
        else if(rqt == Request.PLACE_BOAT){
            p.setReady(true);
            sendCapsuletoPlayersInGame(cpsl, p, false);
            checkIfReady(p);
        }
    }
    
    /**
     * Check if both player sent the fleet and send a start in this case
     * @param p 
     */
    public void checkIfReady(Player p){
        int gameId = p.getCurrentGame();
        int cmpt = 0;
        
        for(Player player : m.getPlayersList()){
            if(player.getReady() == true && player.getCurrentGame() == gameId){
                cmpt ++;
            }
        }
        
        if(cmpt ==2){
            for(Player player : m.getPlayersList()){
                if(player.getCurrentGame() == gameId){
                    sendCapsuletoPlayer(new Capsule(Request.START, null), player);
                }
            }
        }
    }
    
    /**
     * Get the game of the player
     * @param cpsl
     * @param p 
     */
    public void getGameServer(Capsule cpsl, Player p){
        for(ServerGame g : m.getCurrentGamesList()){
            if(g.getId() == p.getCurrentGame()){
                sendCapsuletoPlayer(new Capsule(Request.GET_GAME_SERVER, g), p);
            }
        }
    }
    
    /**
     * Send the list of players in the waiting room
     * @param cpsl
     * @param p 
     */
    public void sendWaitingRoom(Capsule cpsl, Player p){
        ArrayList<Player> l = new ArrayList();
        for(Player player : m.getPlayersList()){
            if(player.getCurrentGame() == p.getCurrentGame()){
                l.add(player);
            }
        }
        sendCapsuletoPlayer(new Capsule(Request.WAITING_ROOM, l), p);
    }

    /**
     * Surrend and leave a game
     * @param p 
     */
    public void surrenderAndleaveGame(Player p){
        //L'adverser gagne par forfait
        // -> RETOUR au menu principal pour le premier
        // -> Ecran de victoire pour le deuxieme
        sendCapsuletoPlayersInGame(new Capsule(Request.SURRENDER, "Surrender"),p, false);
        
        for(Player l : m.getPlayersList()){
            if(p.getCurrentGame() == l.getCurrentGame()){
                l.setReady(false);
                l.setCurrentGame(-1);
            }
        }
        sendCapsuletoPlayer(new Capsule(Request.LIST_OF_GAMES, m.getCurrentGamesList()), p);
    }
    
    public static int getRandomBetween(int pmin, int pmax){
        return (int)(Math.random() * (pmax +1 -pmin)+ pmin);
    }
    
    /**
     * tryStartGame
     * Try to start the game where the player is ready, if both of players are
     * ready the server send a start capsule to all of them
     * @param p  Player
     */
    public void tryStartGame(Player p){
        int gameId = p.getCurrentGame();
        int cmpt = 0;
        int bool = getRandomBetween(0,1);
        boolean b = false;
        
        if(bool == 1){
            b = true;
        }
        
        for(Player l:m.getPlayersList()){
            if(l.getReady() && l.getCurrentGame()==gameId){
                cmpt++;
            }
        }
        
        //AU moins 2 joueurs sont prêts on lance la partie en envoyant start aux joueurs, 
        // ils devrons choisir leur grille après
        if(cmpt >=2){
            for(Player player : m.getPlayersList()){
                if(player .getCurrentGame() == gameId){
                    sendCapsuletoPlayer(new Capsule(Request.BEGINNER, b), player);
                    sendCapsuletoPlayer(new Capsule(Request.START, null), player);
                    if(b == false){
                        b= true;
                    }
                    else{
                        b = false;
                    }
                }
            }
           
            // Une fois les capsules envoyée on reset a false pour la deuxième phase
            for(Player pla:m.getPlayersList()){
                if(pla.getCurrentGame()==gameId){
                    pla.setReady(false);
                }
            }
        }
    }
    
    
    /**
     * Update the game list for all player who doesn't have a current game which mean 
     * they are in the main menu
     */
    public void updateGameList(){
        for(Player p : m.getPlayersList()){
            if(p.getCurrentGame() == -1){
                sendCapsuletoPlayer(new Capsule(Request.LIST_OF_GAMES, m.getCurrentGamesList()), p);
            }
        }
    }
    
    /**
     * Return a error capsule with the string in param
     *
     * @param str
     * @return
     */
    public Capsule errorCapsule(String str) {
        return new Capsule(Request.ERROR, str);
    }

    /**
     * We check if the player is ingame and if he is, we check if the game is
     * empty we remove the game in this case and we send him back a
     * LEAVE_TMP_SUCCED
     *
     * @param cpsl
     * @param p
     */
    public void leaveTmpGame(Capsule cpsl, Player p) {
        int gameId = p.getCurrentGame();

        if (gameId != -1) {
            for (ServerGame g : m.getCurrentGamesList()) {
                if (g.getId() == gameId) {
                    p.setCurrentGame(-1);
                    g.removePlayer();

                    if (g.getNbPlayers() == 0) {
                        m.getCurrentGamesList().remove(g);
                    }
                    sendCapsuletoPlayer(new Capsule(Request.LEAVE_TMP_SUCCEED, "Partie temporaire quitté avec succes"), p);
                }
            }
        }
    }

    /**
     * Join game if it's possible
     *
     * @param cpsl
     * @param p
     */
    public void joinGame(Capsule cpsl, Player p) {
        int idGame = (Integer) cpsl.getObject();

        for (ServerGame g : m.getCurrentGamesList()) {
            if (g.getId() == idGame) {
                if (g.getNbPlayers() >= 2) {
                    sendCapsuletoPlayer(new Capsule(Request.IS_FULL, null), p);
                } else {
                    g.addPlayer();
                    p.setCurrentGame(idGame);
                    sendCapsuletoPlayer(new Capsule(Request.JOIN_SUCCEED, null), p);
                    updateGameList();
                }
            }
        }
    }

    /**
     * Create a game with one player, the owner
     *
     * @param cpsl
     * @param p
     */
    public void createGame(Capsule cpsl, Player p) {
        ServerGame game = (ServerGame) cpsl.getObject();
        m.addNewGame(game);
        p.setCurrentGame(game.getId());
        game.addPlayer();
        printMsg("******* NEW Game " +game.getId()+" created *******\n");
    }

    /**
     * Print a message on the textarea of the server
     *
     * @param str
     */
    public void printMsg(String str) {
        v.ta_chat.append(str);
        v.ta_chat.setCaretPosition(v.ta_chat.getText().length());
    }

    /**
     * Add a player to the global list
     *
     * @param p
     */
    public void addNewPlayer(Player p) {
        m.getPlayersList().add(p);
    }

    /**
     * Remove a player frem the ArrayList in the model
     *
     * @param p
     */
    public void removeAPlayer(Player p) {
        try {
            p.getInput().close();
            p.getOutput().close();
        } catch (IOException ex) {
            //OSEF EN CAS d'EXCEPTION
        }
        printMsg(p.getPlayerNickname()+" Is now offline");
        m.removePlayer(p);
    }

    /**
     * Send capsule to the player
     *
     * @param cpsl
     * @param p
     */
    public void sendCapsuletoPlayer(Capsule cpsl, Player p) {

        try {
            p.getOutput().writeObject(cpsl);
            p.getOutput().flush();
            printMsg("Server ---[" + cpsl.getRequest().toString() + "]---> Player " + p.getPlayerId() + "\n");
        } catch (IOException ex) {
            printMsg("FAILED ---" + cpsl.getRequest().toString() + "]---> Player " + p.getPlayerId() + "\n");
        }

    }
    
    public void sendCapsuleToAllPlayers(Capsule cpsl){
        for(Player p : m.getPlayersList()){
            sendCapsuletoPlayer(cpsl, p);
        }
    }

    /**
     * Send a capsule to a player ingame Be carefull here we don't test if the
     * player is currently ingame you've got to check it out.
     *
     * @param cpsl Capsule
     * @param p Player
     * @param allPlayers boolean
     */
    public void sendCapsuletoPlayersInGame(Capsule cpsl, Player p, boolean allPlayers) {
        int gameId = p.getCurrentGame();

        for (Player l : m.getPlayersList()) {
            if (l.getCurrentGame() == gameId) {
                if (allPlayers == true) {
                    try {
                        l.getOutput().writeObject(cpsl);
                        l.getOutput().flush();
                        printMsg("Server ---[" + cpsl.getRequest().toString() + "]---> Player " + l.getPlayerId() + "\n");
                    } catch (IOException ex) {
                        printMsg("FAILED Tranfert ---[" + cpsl.getRequest().toString() + "]---> Player " + l.getPlayerId() + "\n");
                    }
                } else {
                    if (l != p) {
                        try {
                            l.getOutput().writeObject(cpsl);
                            l.getOutput().flush();
                            printMsg("Server ---[" + cpsl.getRequest().toString() + "]---> Player " + l.getPlayerId() + "\n");
                        } catch (IOException ex) {
                            printMsg("FAILED Tranfert ---[" + cpsl.getRequest().toString() + "]---> Player " + l.getPlayerId() + "\n");
                        }
                    }
                }

            }
        }

    }
    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************

    /**
     * Get ArrayList of current games
     *
     * @return
     */
    public ArrayList<ServerGame> getCurrentGames() {
        return m.getCurrentGamesList();
    }
}
