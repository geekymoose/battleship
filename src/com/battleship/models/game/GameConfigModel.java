/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.models.game;

import com.battleship.constants.GameConstants;





/**
 * <h1>GameConfigModel</h1>
 * <p>
 public class GameConfigModel
 </p>
 * 
 * <p>
 * Configuration for a new GameModel: it keeps the game configuration.<br/>
 * A new game will be created later with this configuration class.
 * </p>
 * <h2>Configure options</h2>
 * <p>
 * <ul>
 *  <li>Grid width: int number</li>
 *  <li>Grid height: int number</li>
 *  <li>Grid type: square or hexagon</li>
 *  </ul>
 * </p>
 * 
 *
 * @date    Feb 9, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 * 
 * @see GameModel
 */
public class GameConfigModel implements GameConstants{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private     int             gridWidth;
    private     int             gridHeight;
    private     int             gridType;
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new empty GameConfig. All data are loaded with default values.
     */
    public GameConfigModel(){
        this.defaultConfig();
    }
    
    /**
     * Set configuration to default values
     */
    private void defaultConfig(){
        this.gridWidth  = GRID_DEFAULT_WIDTH;
        this.gridHeight = GRID_DEFAULT_HEIGHT;
        this.gridType   = GRID_TYPE_SQUARE;
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Reset the current configuration. Every value will be set toward default 
     * value.
     */
    public void resetConfig(){
        this.defaultConfig();
    }
    
    
    
    
    

    //**************************************************************************
    // Getters - Setters
    //**************************************************************************
    /**
     * Return the current grid width
     * @return grid with
     */
    public int getGridWidth() {
        return this.gridWidth;
    }

    /**
     * Return the current grid height
     * @return grid height
     */
    public int getGridHeight() {
        return this.gridHeight;
    }
    
    /**
     * Return current grid type (Ex: SQUARE or HEXAGON)
     * @return 
     */
    public int getGridType() {
        return this.gridType;
    }
    
    //**************************************************************************
    /**
     * Set a new grid width, will replace the old one. Must be between 
     * {@value GameConstants#GRID_MIN_WIDTH} and {@value GameConstants#GRID_MAX_WIDTH},
     * otherwise, do nothing
     * @param pValue new width
     */
    public void setGridWidth(int pValue) {
        if(pValue>=GRID_MIN_WIDTH && pValue<=GRID_MAX_WIDTH){
            this.gridWidth = pValue;
        }
    }

    /**
     * Set a new gridWidth, will replace the old one. Must be between 
     * {@value GameConstants#GRID_MIN_HEIGHT} and {@value GameConstants#GRID_MAX_HEIGHT},
     * otherwise, do nothing
     * @param pValue new width
     */
    public void setGridHeight(int pValue) {
        if(pValue>=GRID_MIN_HEIGHT && pValue<=GRID_MAX_HEIGHT){
            this.gridHeight = pValue;
        }
    }

    /**
     * Set a new Grid type. Must be a valid type, otherwise, do nothing.
     * @param pValue new width
     */
    public void setGridType(int pValue) {
        if(pValue==GRID_TYPE_SQUARE || pValue<=GRID_TYPE_HEXAGON){
            this.gridType = pValue;
        }
    }
}
