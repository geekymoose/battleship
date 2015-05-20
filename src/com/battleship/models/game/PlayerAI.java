/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.models.game;

import com.battleship.asset.RandomManager;
import com.battleship.behaviors.Target;
import com.battleship.main.DebugTrack;
import com.battleship.models.weapons.Torpedo;
import java.awt.Point;
import java.util.ArrayList;





/**
 * <h1>PlayerAI</h1>
 * <p>
 * public class PlayerAI<br/>
 * extends Player
 * </p>
 * <p>Player is controlled by artificial intelligence.</p>
 *
 * @since   Mar 24, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public class PlayerAI extends Player{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    private             Difficulty      difficulty;
    private             int[][]         grid;
    public static final int             VOID = 0;
    public static final int             PLOUF = 1;
    public static final int             BOAT = 2;
    private             int             orientation;
    private             Point           lastHit;
    
    
    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new AI player
     */
    public PlayerAI(){
        super();
        difficulty = Difficulty.HARD;
        //height et width pas implémentés dès le début => erreur null pointer
        //grid = new int [this.fleetGrid.gridHeight][this.fleetGrid.gridWidth];
        grid = new int [10][10];
        lastHit = null;
        orientation = 0;
    }
    
    
    
    
    
    //**************************************************************************
    // Functions
    //**************************************************************************
    /**
     * Process to shoot on enemy grid with some intelligence or not depending on
     * the difficulty
     * @param pWhere where to shoot
     */
    public void processAiShoot(Target[][] pWhere){
        int posX = 0, posY = 0;
        switch(difficulty){
            case EASY :
                //Note this implementation is naive
                do{
                    posX = RandomManager.getRandomBetween(0, (pWhere[0].length-1));
                    posY = RandomManager.getRandomBetween(0, (pWhere.length-1));
                } while(pWhere[posY][posX].isValidTarget() == false);
                break;
            case HARD :
                if(lastHit!=null) {
                    if(getSurrounding(lastHit)==null){
                        lastHit = null;
                        orientation = 0;
                    } else {
                        Point target = this.getSurrounding(lastHit);
                        posX = (int)target.getX();
                        posY = (int)target.getY();
                        //System.out.println("coordinates : "+posY+","+posX);
                    }                    
                }
                if(lastHit==null){
                    do{
                        posX = RandomManager.getRandomBetween(0, (pWhere[0].length-1));
                        posY = RandomManager.getRandomBetween(0, (pWhere.length-1));
                    } while(pWhere[posY][posX].isValidTarget() == false);
                }
                break;
            case EXPERT :
                int russianRoulette = RandomManager.getRandomBetween(0, 10);
                if(russianRoulette==5){
                    this.listWeapons.add(new Torpedo(this, 1));
                    this.switchWeaponWith(404);
                }
                if(lastHit!=null) {
                    if(getSurrounding(lastHit)==null){
                        lastHit = null;
                        orientation = 0;
                    } else {
                        Point target = this.getSurrounding(lastHit);
                        posX = (int)target.getX();
                        posY = (int)target.getY();
                        //System.out.println("coordinates : "+posY+","+posX);
                    }                    
                }
                if(lastHit==null){
                    do{
                        posX = RandomManager.getRandomBetween(0, (pWhere[0].length-1));
                        posY = RandomManager.getRandomBetween(0, (pWhere.length-1));
                    } while(pWhere[posY][posX].isValidTarget() == false);
                }
                this.switchWeaponWith(42);
                break;
        }
        this.shootAt(posX, posY, pWhere);
        int box = ((BoxMap)pWhere[posY][posX]).content.getId();
        if(box==42) { 
            grid[posY][posX] = PLOUF;
        }
        if(box==0||box==1||box==2||box==3||box==4) {
            grid[posY][posX] = BOAT;
            lastHit = new Point(posY, posX);
        }
    }
    
    /**
     * 
     * @param box the case we need to look around in the grid
     * @return the coordinates of one of the case that is not water around in the grid
     */
    private Point getSurrounding(Point box){
        if(this.fleetGrid instanceof FleetGridHexagon){
            ArrayList<Point> l = getSurroundingsHexa(box);
            if(!l.isEmpty()){
                return l.get(0);
            } else {
                return null;
            }
        }else if(this.fleetGrid instanceof FleetGridSquare){
            ArrayList<Point> l = getSurroundingsSquare(box);
            if(!l.isEmpty()){
                return l.get(0);
            } else {
                return null;
            }
        }
        //should never be reached
        return null;
    }
    
    /**
     * 
     * @param box the case we need to look around in the grid
     * @return an arraylist of the water cases next to the param box
     */
    private ArrayList<Point> getSurroundingsSquare(Point box){
        int x = (int)box.getX();
        int y = (int)box.getY();
        ArrayList<Point> l = new ArrayList();
        for(int i=-1; i<2; i++){
            for(int j=-1; j<2; j++){
                if(Math.abs(i)==Math.abs(j)) { continue; }
                Point b = this.getCaseAt(x+i, y+j);
                if(b!=null&&grid[(int)b.getY()][(int)b.getX()]==VOID){
                    l.add(b);               
                }
            }
        }
        return l;
    }
    /**
     * 
     * @param box the case we need to look around in the grid
     * @return an arraylist of the water cases next to the param box
     */
    private ArrayList<Point> getSurroundingsHexa(Point box){
        int x = (int)box.getX();
        int y = (int)box.getY();
        
        ArrayList<Point> l = new ArrayList();
        Point b;
        
        if(x%2==0){
            
            if((b = this.getCaseAt(x, y-1))!= null && grid[x][y-1]==VOID){ l.add(b);}
            if((b = this.getCaseAt(x, y+1))!= null && grid[x][y+1]==VOID){ l.add(b);}
            if((b = this.getCaseAt(x-1, y-1))!= null && grid[x-1][y-1]==VOID){ l.add(b);}
            if((b = this.getCaseAt(x-1, y))!= null && grid[x-1][y]==VOID){ l.add(b);}
            if((b = this.getCaseAt(x+1, y-1))!= null && grid[x+1][y-1]==VOID){ l.add(b);}
            if((b = this.getCaseAt(x+1, y))!= null && grid[x+1][y]==VOID){ l.add(b);}
        }else{
            if((b = this.getCaseAt(x, y-1))!= null && grid[x][y-1]==VOID){ l.add(b);}
            if((b = this.getCaseAt(x-1, y))!= null && grid[x-1][y]==VOID){ l.add(b);}
            if((b = this.getCaseAt(x+1, y))!= null && grid[x+1][y]==VOID){ l.add(b);}
            if((b = this.getCaseAt(x-1, y+1))!= null && grid[x-1][y+1]==VOID){ l.add(b);}
            if((b = this.getCaseAt(x, y+1))!= null && grid[x][y+1]==VOID){ l.add(b);}
            if((b = this.getCaseAt(x+1, y+1))!= null && grid[x+1][y+1]==VOID){ l.add(b);}
        }
        return l;
    }
    /**
     * Use to check if the coordinates are valid or not
     * 
     * @param pX coordinates of the case (abcissa)
     * @param pY coordinates of the case (ordinate)
     * @return the coordinates of the case if it exists or null if it does not
     */
    public Point getCaseAt(int pX, int pY){
        if (pX<0 || pX>=this.fleetGrid.gridWidth 
                 || pY<0 || pY>=this.fleetGrid.gridHeight){
            return null;
        }
        return new Point(pY, pX);
    }
    
    
    /*
     * Enum with difficulty 
     */
    public enum Difficulty{
        EASY("easy"),
        HARD("hard"),
        EXPERT("expert");
        
        private String description;
        Difficulty(String pDescription){
            this.description = pDescription;
        }
        
        public String getDescription(){
            return this.description;
        }
    }
    
    
    
    

    //**************************************************************************
    // Getter - Setters for AI Player
    //**************************************************************************
    public Difficulty getCurrentDifficulty(){
        return this.difficulty;
    }
    
    public void setDifficulty(Difficulty pValue){
        this.difficulty = pValue;
        DebugTrack.showDebugMsg("AI PLayer : diff set to "+pValue.getDescription());
    }
}
