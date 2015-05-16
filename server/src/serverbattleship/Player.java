/* 
 * Creation : 2 mai 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package serverbattleship;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



/**
 * @date    2 mai 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class Player {
    private static int idvalue = 0;
    private final int id;
    
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private String nickname;
    private int currentGame = -1;
    private boolean isReady = false;
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public Player(){
        this.id = ++idvalue;
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************

    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************
    public void setReady(boolean b){
        this.isReady = b;
    }
    
    public boolean getReady(){
        return this.isReady;
    }
    public void setInput(ObjectInputStream input){
        this.input = input;
    }
    
    public void setOutput(ObjectOutputStream output){
        this.output = output;
    }
    
    public ObjectInputStream getInput(){
        return this.input;
    }
    
    public ObjectOutputStream getOutput(){
        return this.output;
    }
    
    public int getPlayerId(){
        return this.id;
    }

    public void setPlayerNickname(String str){
        this.nickname = str;
    }
    
    public String getPlayerNickname(){
        return this.nickname;
    }
    
    public void setCurrentGame(int i){
        this.currentGame = i;
    }
    
    public int getCurrentGame(){
        return this.currentGame;
    }
}
