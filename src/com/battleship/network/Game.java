/* 
 * Creation : 30 avr. 2015
 */
package com.battleship.network;



/**
 * @since   30 avr. 2015
 * @author  Anthony CHAFFOT
 */
public class Game {
    private static  int                 idValue = 0;
    private         int                 id;
    private         int                 nbPlayers=0;
    private         String              title;
    private         int                 grid;
    //AJOUTER ARRAYLIST SPECTATEUR
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public Game(String title, int grid){
        this.id         = ++idValue;
        this.title      = title;
        this.grid       = grid;
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    public void addPlayer(){
        nbPlayers++;
    }
    
    public void removePlayer(){
        nbPlayers--;
    }
    
    public boolean isFull(){
        return nbPlayers ==2;
    }

    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************
    public int getId(){
        return id;
    }
    
    public int getNbPlayers(){
        return nbPlayers;
    }

    public String getTitle(){
        return title;
    }
    
    public int getType(){
        return grid;
    }
}
