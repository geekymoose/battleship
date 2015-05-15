/* 
 * Creation : 30 avr. 2015
 */
package com.battleship.network;

import java.io.Serializable;



/**
 * @date    30 avr. 2015
 * @author  Anthony CHAFFOT
 */
public class ServerGame implements Serializable{
    private static  int                 idValue = 0;
    private         int                 id;
    private         int                 nbPlayers=0;
    private         String              title;
    private         int                 grid;
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    /**
     * Constructor of game
     * @param title
     * @param grid 
     */
    public ServerGame(String title, int grid){
        this.id         = ++idValue;
        this.title      = title;
        this.grid       = grid;
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    /**
     * Increment of 1 the number of players
     */
    public void addPlayer(){
        nbPlayers++;
    }
    
    /**
     * remove 1 to the number of players
     */
    public void removePlayer(){
        nbPlayers--;
    }
    
    /**
     * Retrun true the game if full
     * @return 
     */
    public boolean isFull(){
        return nbPlayers ==2;
    }

    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************
    /**
     * get the id
     * @return 
     */
    public int getId(){
        return id;
    }
    
    /**
     * get the number of players
     * @return 
     */
    public int getNbPlayers(){
        return nbPlayers;
    }

    /**
     * get the title
     * @return 
     */
    public String getTitle(){
        return title;
    }
    
    /**
     * get the type of grid
     * @return 
     */
    public int getType(){
        return grid;
    }
}
