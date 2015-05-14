/* 
 * Creation : Mar 12, 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.models.game;

import com.battleship.observers.ObservableModel;
import com.battleship.observers.ObserverModel;
import java.util.ArrayList;





/**
 * <h1>Model</h1>
 * <p>
 * public abstract class Model<br/>
 * implements ObservableModel
 * </p>
 * 
 * <p>
 * All class extending Model are a model class used in Model-View-Controller 
 * architecture. Model is an Observable object 
 * </p>
 *
 * @since   Feb 9, 2015
 * @author  Constantin MASSON
 * @author  Jessica FAVIN
 * @author  Anthony CHAFFOT
 */
public abstract class Model implements ObservableModel{
    //**************************************************************************
    // Constants - Variables
    //**************************************************************************
    protected   ArrayList<ObserverModel> listObservers;
    
    
    
    
    
    //**************************************************************************
    // Constructor - Initialisation
    //**************************************************************************
    protected Model(){
        this.listObservers = new ArrayList();
    }
    
    
    
    
    
    //**************************************************************************
    // Observers
    //**************************************************************************
    @Override
    public void addObserver(ObserverModel obs){
        if(obs!=null){
            this.listObservers.add(obs);
        }
    }

    @Override
    public void deleteObserver(ObserverModel o){
        if(o!=null){
            this.listObservers.remove(o);
        }
    }
    
    @Override
    public void notifyObservers(Object obj){
        for(ObserverModel obs : this.listObservers){
            obs.update(this, obj);
        }
    }
}
