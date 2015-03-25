/*
 * Creation :   24 mars 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.views.app;

import static com.battleship.constants.GameConstants.CREATE_GAME;
import static com.battleship.constants.GameConstants.JOIN_GAME;
import static com.battleship.constants.Roots.CHOOSE_GAME;
import static com.battleship.constants.Roots.CONFIG;
import static com.battleship.constants.Roots.PLACE_BOATS;
import com.battleship.controllers.LANConfigController;
import com.battleship.exceptions.ExecError;
import com.battleship.main.DebugTrack;
import com.battleship.observers.ObservableModel;
import com.battleship.observers.ObserverModel;
import com.battleship.views.tools.PagePanel;
import com.battleship.views.tools.WindowFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;





/**
 * <h1>LANConfigPanel</h1>
 * <p>
 public class LANConfigPanel<br/>
 * </p>
 * 
 * <p>Description</p>
 * 
 * @date    24 mars 2015
 * @author  Contsantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class LANConfigPanel extends PagePanel implements ObserverModel {
    
    private     LANConfigController     controller;
    
    private     JPanel                  p_gameList;
    private     JPanel                  p_buttons;
    private     JPanel                  p_pages;
    
    private     JTextArea               ta_gameList;
    private     JScrollPane             sp_gameList;
    
    private     JLabel                  l_indication;
    
    private     JButton                 b_join;
    private     JButton                 b_create;
    private     JButton                 b_previous;
    private     JButton                 b_next;
    private     int                     choice;
    
    public LANConfigPanel(WindowFrame pFrame, LANConfigController pController) 
    throws ExecError {
        super(pFrame);
        if(pController==null){
            throw new ExecError();
        }
        this.controller = pController;
        this.initComponents();
    }
    
    private void initComponents(){
        this.p_gameList = new JPanel();
        this.p_buttons  = new JPanel();
        this.p_pages    = new JPanel();
        
        this            .setLayout(new BorderLayout());
        p_gameList      .setLayout(new FlowLayout());
        p_buttons       .setLayout(new GridLayout(3,1));
        p_pages         .setLayout(new GridLayout(1,2));
        
        ta_gameList     = new JTextArea();
        sp_gameList     = new JScrollPane(ta_gameList);
        
        l_indication    = new JLabel("LAN");
        b_join          = new JButton("Rejoindre");
        b_create        = new JButton("Créer");
        b_previous      = new JButton("<");
        b_next          = new JButton(">");
        
        p_pages         .add(b_previous);
        p_pages         .add(b_next);
        p_buttons       .add(b_join);
        p_buttons       .add(b_create);
        p_buttons       .add(p_pages);
        p_gameList      .add(sp_gameList);
        
        this.add(l_indication, BorderLayout.NORTH);
        this.add(p_gameList, BorderLayout.WEST);
        this.add(p_buttons, BorderLayout.EAST);
        
        //A VERIFIER
        choice = CREATE_GAME;
        
        this.setBtnAction();
    }

    @Override
    public void initPage() {
        this.controller.resetDefaultConfig();
    }

    @Override
    protected void goNextPage() {
        if(this.controller.isValidConfig()){
            switch(choice){
                case CREATE_GAME:
                    DebugTrack.showExecMsg("Go to GameConfigPanel");
                    frame.rooting(CONFIG, null);
                    break;
                case JOIN_GAME:
                    DebugTrack.showExecMsg("Go to place boat to join game");
                    frame.rooting(PLACE_BOATS, null);
                    break;
            }
        }
    }

    @Override
    protected void goPreviousPage() {
        JOptionPane opt = new JOptionPane();
        int choice2 = opt.showConfirmDialog(null, 
                              "Are you sure you want to go back? "
                               +"Current configuration could be lost",
                              "Warning",
                              JOptionPane.YES_NO_CANCEL_OPTION, 
                              JOptionPane.QUESTION_MESSAGE);
        
        if(choice2==JOptionPane.OK_OPTION){
            frame.rooting(CHOOSE_GAME, null);
        }
    }

    private void setBtnAction() {
        b_previous.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DebugTrack.showExecMsg("Return back");
                    goPreviousPage();
                }
            }
        );
        b_next.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    DebugTrack.showExecMsg("Go next");
                    goNextPage();
                }
            }
        );
        b_create.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    DebugTrack.showExecMsg("Select create game");
                    choice = CREATE_GAME;
                }
            }
        );
        b_join.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    DebugTrack.showExecMsg("select join game");
                    choice = JOIN_GAME;
                }
            }
        );
    }

    @Override
    public void update(ObservableModel o, Object arg) {
    
    }
    
    

}
