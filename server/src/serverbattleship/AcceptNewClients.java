/* 
 * Creation : 3 mai 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package serverbattleship;

import com.battleship.network.Capsule;
import com.battleship.network.Request;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @date 3 mai 2015
 * @author Constantin MASSON
 * @author Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class AcceptNewClients implements Runnable {
    
    Thread t;
    Controller c;
    ServerSocket serverSocket;
    Socket socket;
    Request rqt;

    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public AcceptNewClients(String str, Controller c, ServerSocket serverSocket) {
        t = new Thread(this, str);
        this.c = c;
        this.serverSocket = serverSocket;
        t.start();
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    @Override
    public void run() {
        while (true) {
            try {
                c.printMsg("Attente de client...\n");
                try {
                    socket = serverSocket.accept(); // wait for someone to join on server
                } catch (Exception ex) {
                    break;
                }
                c.printMsg("Server ---[NOUVEAU CLIENT]---> " + socket.getInetAddress().getHostName() + "\n");

                Player player = new Player();
                player.setOutput(new ObjectOutputStream(socket.getOutputStream()));
                player.getOutput().flush();
                player.setInput(new ObjectInputStream(socket.getInputStream()));
                player.setPlayerNickname("Player "+player.getPlayerId());
                
                c.addNewPlayer(player);
                c.printMsg("Server ---[" + player.getPlayerNickname() + "]---> ONLINE\n");
                
                //c.sendCapsuletoPlayer(new Capsule(rqt.LIST_OF_GAMES, c.getCurrentGames()), player);
                
                c.sendCapsuletoPlayer(new Capsule(Request.MSG_CHAT, "COUCOU LES PETITS ZIZIS\n"), player); // FONCTIONNE
                //player.getOutput().writeObject(new test(1,"BISOUS")); FONCTIONNE
                
                ReceiveMsgs ob2 = new ReceiveMsgs(player, "StartReceiveMsgs_" + player.getPlayerId(), c, serverSocket, socket); // make new thread for every new client 

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************
}
