/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import static com.battleship.constants.Roots.*;
import com.battleship.controllers.PlaceBoatsController;
import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import com.battleship.observers.ObservableModel;
import com.battleship.observers.ObserverModel;
import com.battleship.views.tools.PagePanel;
import com.battleship.views.tools.WindowFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;



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
    
    private     JPanel                          p_buttonPanel;
    private     JButton                         b_valide;
    private     JButton                         b_reset;
    private     JButton                         b_back;
    
    
    
    
    
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
        this.dock           = new DockPanel(this);
        this.p_buttonPanel  = new JPanel();
        this.b_valide       = new JButton("Valide");
        this.b_reset        = new JButton("Reset");
        this.b_back         = new JButton("Back");
        
        
        this.setLayout(new BorderLayout());
        this.p_buttonPanel.setLayout(new FlowLayout());
        
        this.p_buttonPanel.add(b_valide);
        this.p_buttonPanel.add(b_reset);
        this.p_buttonPanel.add(b_back);
        
        this.add(p_buttonPanel, BorderLayout.SOUTH);
        this.add(dock, BorderLayout.EAST);
        this.setBtnActions();
    }
    
    
    /*
     * Create actionListener for the buttons
     * Each button call the new JPanel for this application (Parent)
     */
    private void setBtnActions() {
        b_valide.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DebugTrack.showExecMsg("Valid Place Boats");
                    goNextPage();
                }
            }
        );
        
        b_reset.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DebugTrack.showExecMsg("Reset Place Boats");
                    //To Do
                }
            }
        );
        
        b_back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DebugTrack.showExecMsg("Return before Place Boats");
                    goPreviousPage();
                }
            }
        );
    }//End setBtnActions
    
    
    
    
    
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
        this.frame.rooting(GAME, null);
    }
    
    @Override
    protected void goPreviousPage(){
    }
}
