/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.views.app;

import com.battleship.asset.CheatCode;
import com.battleship.asset.Session;
import com.battleship.network.Capsule;
import com.battleship.network.Request;
import com.battleship.observers.ObservableLan;
import com.battleship.observers.ObserverLan;
import com.battleship.views.tools.ContentPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;




/**
 * 
 * 
 * @since   Feb 11. 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class ChatPanel extends ContentPanel implements ObserverLan{
    private     JPanel          p_sentence;
    private     JPanel          p_north;
    private     JTextField      tf_sentence;
    private     JTextArea       ta_chat;
    private     JScrollPane     sp_scroll;
    
    
    
    
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    /**
     * Constructor of the ChatPanel
     * @param pParent parent panel
     */
    public ChatPanel(JPanel pParent) {
        super(pParent);
        this.initComponents();
        this.setSizes();
        this.addEachComponents();
        this.setActions();
        if(Session.isConnected()){
            Session.getNetwork().addLanObserver(this);
        }
    }
    
    
    
    
    
    //**************************************************************************
    // METHODS
    //**************************************************************************
    /**
     * Set the size of the chat
     */
    private void setSizes(){
        tf_sentence.setPreferredSize(new Dimension(180, 25));
    }
    
    /**
     * Init all components
     */
    private void initComponents() {
        p_sentence = new JPanel();
        p_north = new JPanel();
        p_north.setLayout(new BorderLayout());
        //this.setLayout(new GridLayout(2,1));
        this.setLayout(new BorderLayout());
        tf_sentence = new JTextField();
        ta_chat = new JTextArea();
        setupChat();
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.ta_chat        .setMargin(new Insets(10, 10, 10, 10));
        this.tf_sentence    .setMargin(new Insets(0,10,0,10));
        this.setOpaque(false);
    }

    /**
     * Set the chat with the the scroll pane
     */
    private void setupChat() {
        ta_chat = new JTextArea();
        sp_scroll = new JScrollPane(ta_chat);
        ta_chat.setLineWrap(true);
        ta_chat.setEditable(false);
        sp_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
    }

    /**
     * add each components to the panel
     */
    private void addEachComponents() {
        p_north.add(sp_scroll, BorderLayout.CENTER);
        p_sentence.add(tf_sentence);
        this.add(p_north, BorderLayout.CENTER);
        this.add(p_sentence, BorderLayout.SOUTH);
    }

    public void printChatMessage(String string) {
        if(string != null){
            string = string.trim();
            if(!string.isEmpty() && !string.equals("\n")){
                ta_chat.append(string+"\n");
                ta_chat.setCaretPosition(ta_chat.getText().length());
            }
        }
    }

    //Peut être à changer un peu pour eviter d'appuyer sur enter par erreur
    public void setActions() {
        tf_sentence.addKeyListener(
            new KeyListener() {
                public void keyPressed(KeyEvent e) {
                    int key = e.getKeyCode();
                    if (key == KeyEvent.VK_ENTER) {
                        String sentence = tf_sentence.getText();
                        CheatCode.processStrCode(sentence);
                        
                        if(Session.isConnected()){
                            Session.getNetwork().sendCapsule(new Capsule(Request.MSG_CHAT, sentence));
                        }
                        printChatMessage(sentence);
                        tf_sentence.setText("");
                    }
                }

                public void keyReleased(KeyEvent e) {
                }

                public void keyTyped(KeyEvent e) {
                }
            }
        );
    }
    
    
    
    
    
    //**************************************************************************
    // UI Functions
    //**************************************************************************
    @Override
    public void loadUI(){
    
    }
    
    @Override
    public void reloadUI(){
    
    }

    @Override
    public void updateLan(ObservableLan o, Object arg){
        if(arg instanceof String){
            String msg = (String)arg;
            this.printChatMessage(msg);
        }
    }
}
