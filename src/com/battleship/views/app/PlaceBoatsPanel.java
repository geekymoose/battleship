/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.controllers.PlaceBoatsController;
import com.battleship.exceptions.ExecError;
import com.battleship.observers.ObservableModel;
import com.battleship.observers.ObserverModel;
import com.battleship.views.tools.PagePanel;
import com.battleship.views.tools.WindowFrame;
import java.awt.BorderLayout;



/**
 *
 * 
 * @date    Feb 11. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class PlaceBoatsPanel extends PagePanel implements ObserverModel{
    private     final PlaceBoatsController      controller;
    
    private     DockPanel                       dock;
    
    
    
    
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    /**
     * 
     * @param pFrame        Frame containing this panel
     * @param pController   Controller for this page
     * @throws ExecError error if unable to create this panel
     */
    public PlaceBoatsPanel(WindowFrame pFrame, PlaceBoatsController pController) 
    throws ExecError{
        super(pFrame);
        if(pController==null){ throw new ExecError(); }
        this.controller = pController;
        this.initComponents();
        this.setPreferredSize(DIM_PLACE_BOAT);
    }
    
    private void initComponents() throws ExecError{
        this.setLayout(new BorderLayout());
        this.dock = new DockPanel(this);
        this.add(dock, BorderLayout.EAST);
    }
    
    
    
    
    
    //**************************************************************************
    // Override from PagePanel
    //**************************************************************************
    @Override
    public void initPage(){
    }

    @Override
    public void update(ObservableModel o, Object arg){
    
    }
    
    @Override
    protected void goNextPage(){
    }
    
    @Override
    protected void goPreviousPage(){
    }
}
