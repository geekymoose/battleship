/* 
 * Creation : 3 mai 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package serverbattleship;

import com.battleship.network.ServerGame;
import java.util.ArrayList;



/**
 * @date    3 mai 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class Model {
    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<ServerGame> games = new ArrayList<>();
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public Model(){
        games.add(new ServerGame("Partie de Test",2));
        
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    public void removePlayer(Player p){
        players.remove(p);
    }
    
    public void addNewGame(ServerGame game){
        games.add(game);
    }

    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************
    public ArrayList<Player> getPlayersList(){
        return this.players;
    }
    
    public ArrayList<ServerGame> getCurrentGamesList(){
        return this.games;
    }

}
