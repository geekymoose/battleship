/* 
 * Creation : Feb 9, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.models.game;

import com.battleship.asset.RandomManager;
import static com.battleship.behaviors.Sprite.DEAD_BOAT;
import static com.battleship.behaviors.Sprite.DEAD_WATER;
import com.battleship.behaviors.Target;
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
    public static final int     EASY = 1;
    public static final int     HARD = 2;
    public static final int     EXPERT = 3;
    private             int     difficulty;
    private             int[][] grid;
    public static final int     VOID = 0;
    public static final int     PLOUF = 1;
    public static final int     BOAT = 2;
    private             Point   lastHit;
    private             int     orientation;
    
    

    //**************************************************************************
    // Constructor - Initialization
    //**************************************************************************
    /**
     * Create a new AI player
     */
    public PlayerAI(){
        super();
        difficulty = EASY;
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
     * Process a random shoot on enemy grid
     * @param pWhere where to shoot
     */
    public void processAiShoot(Target[][] pWhere){
        int posX = 0, posY = 0;
        switch(difficulty){
            case EASY :
                //Note this implementation is naive
                System.out.println("Coucou c'est moi");
                do{
                    posX = RandomManager.getRandomBetween(0, (pWhere[0].length-1));
                    posY = RandomManager.getRandomBetween(0, (pWhere.length-1));
                } while(pWhere[posY][posX].isValidTarget() == false);
                break;
            case HARD :
                if(lastHit!=null) {
                    System.out.println("lasHit is not null");
                    if(getSurrounding(lastHit)==null){
                        System.out.println("no lasHit surroundings");
                        lastHit = null;
                        orientation = 0;
                        //voir si on ne paut pas retourner au hit original
                        //et avec l'orientation continuer de l'autre côté
                    } else {
                        System.out.println("should hit next to lastHit");
                        Point target = this.getSurrounding(lastHit);
                        posX = (int)target.getX();
                        posY = (int)target.getY();
                        System.out.println("coordinates : "+posY+","+posX);
                    }                    
                }
                if(lastHit==null){
                    System.out.println("lastHit is null : RANDOM");
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
                //tactique de tir du mode expert
                //same as HARD
                //"suppression" des cases grid adjacentes??
                
                this.switchWeaponWith(42);
                break;
        }
        this.shootAt(posX, posY, pWhere);
        int box = ((BoxMap)pWhere[posY][posX]).content.getId();
        if(box==42) { 
            grid[posY][posX] = PLOUF;
            System.out.println("plouf plouf");
        }
        if(box==0||box==1||box==2||box==3||box==4) {
            grid[posY][posX] = BOAT;
            lastHit = new Point(posY, posX);
            System.out.println("last hit initialized to "+posY+","+posX);
        }
        for (int i=0; i<10;i++){
         System.out.print("Ligne "+i+" : ");
            for (int j=0; j<10; j++){
                System.out.print(grid[i][j]);
                
            }
            System.out.println("");
        }
    }
    
    private Point getSurrounding(Point box){
        if(this.fleetGrid instanceof FleetGridHexagon){
            ArrayList<Point> l = getSurroundingsHexa(box);
            if(!l.isEmpty()){
                return l.get(0);
            } else {
                System.out.println("l is empty?");
                return null;
            }
        }else if(this.fleetGrid instanceof FleetGridSquare){
            ArrayList<Point> l = getSurroundingsSquare(box);
            if(!l.isEmpty()){
                return l.get(0);
            } else {
                System.out.println("l is empty?");
                return null;
            }
        }
        //should never be reached
        return null;
    }
    
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
    
    private ArrayList<Point> getSurroundingsHexa(Point box){
        int x = (int)box.getX();
        int y = (int)box.getY();
        
        ArrayList<Point> l = new ArrayList();
        Point b;
        
        if(x%2==0){
            
            if((b = this.getCaseAt(x, y-1))!= null && grid[x][y-1]==VOID){ l.add(b);System.out.println(grid[x][y-1]);}
            if((b = this.getCaseAt(x, y+1))!= null && grid[x][y+1]==VOID){ l.add(b); System.out.println(grid[x][y+1]);}
            if((b = this.getCaseAt(x-1, y-1))!= null && grid[x-1][y-1]==VOID){ l.add(b); System.out.println(grid[x-1][y-1]);}
            if((b = this.getCaseAt(x-1, y))!= null && grid[x-1][y]==VOID){ l.add(b); System.out.println(grid[x-1][y]);}
            if((b = this.getCaseAt(x+1, y-1))!= null && grid[x+1][y-1]==VOID){ l.add(b); System.out.println(grid[x+1][y-1]);}
            if((b = this.getCaseAt(x+1, y))!= null && grid[x+1][y]==VOID){ l.add(b); System.out.println(grid[x+1][y]);}
            /*
            if((b = this.getCaseAt(x, y-1))!= null && grid[y-1][x]==VOID){ l.add(b);System.out.println(grid[y-1][x]);}
            if((b = this.getCaseAt(x, y+1))!= null && grid[y+1][x]==VOID){ l.add(b); System.out.println(grid[y+1][x]);}
            if((b = this.getCaseAt(x-1, y-1))!= null && grid[y-1][x-1]==VOID){ l.add(b); System.out.println(grid[y-1][x-1]);}
            if((b = this.getCaseAt(x-1, y))!= null && grid[y][x-1]==VOID){ l.add(b); System.out.println(grid[y][x-1]);}
            if((b = this.getCaseAt(x+1, y-1))!= null && grid[y-1][x+1]==VOID){ l.add(b); System.out.println(grid[y-1][x+1]);}
            if((b = this.getCaseAt(x+1, y))!= null && grid[y][x+1]==VOID){ l.add(b); System.out.println(grid[y][x+1]);}
            */
        }else{
            if((b = this.getCaseAt(x, y-1))!= null && grid[x][y-1]==VOID){ l.add(b); System.out.println(grid[x][y-1]);}
            if((b = this.getCaseAt(x-1, y))!= null && grid[x-1][y]==VOID){ l.add(b); System.out.println(grid[x-1][y]);}
            if((b = this.getCaseAt(x+1, y))!= null && grid[x+1][y]==VOID){ l.add(b); System.out.println(grid[x+1][y]);}
            if((b = this.getCaseAt(x-1, y+1))!= null && grid[x-1][y+1]==VOID){ l.add(b); System.out.println(grid[x-1][y+1]);}
            if((b = this.getCaseAt(x, y+1))!= null && grid[x][y+1]==VOID){ l.add(b); System.out.println(grid[x][y+1]);}
            if((b = this.getCaseAt(x+1, y+1))!= null && grid[x+1][y+1]==VOID){ l.add(b); System.out.println(grid[x+1][y+1]);}
            /*
            if((b = this.getCaseAt(x, y-1))!= null && grid[y-1][x]==VOID){ l.add(b);System.out.println(grid[y-1][x]);}
            if((b = this.getCaseAt(x-1, y))!= null && grid[y][x-1]==VOID){ l.add(b); System.out.println(grid[y][x-1]);}
            if((b = this.getCaseAt(x+1, y))!= null && grid[y][x+1]==VOID){ l.add(b); System.out.println(grid[y][x+1]);}
            if((b = this.getCaseAt(x-1, y+1))!= null && grid[y+1][x-1]==VOID){ l.add(b); System.out.println(grid[y+1][x-1]);}
            if((b = this.getCaseAt(x, y+1))!= null && grid[y+1][x]==VOID){ l.add(b); System.out.println(grid[y+1][x]);}
            if((b = this.getCaseAt(x+1, y+1))!= null && grid[y+1][x+1]==VOID){ l.add(b); System.out.println(grid[y+1][x+1]);}
            */
        }
        return l;
    }
    
    public Point getCaseAt(int pX, int pY){
        if (pX<0 || pX>=this.fleetGrid.gridWidth 
                 || pY<0 || pY>=this.fleetGrid.gridHeight){
            return null;
        }
        return new Point(pY, pX);
    }
    
}
