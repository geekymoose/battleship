/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */

package com.battleship.views.app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;




/**
 * <h1></h1>
 * 
 * 
 * @date 11 févr. 2015
 * @author Constantin MASSON
 * @author Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class ChatPanel extends JPanel {
    private JPanel p_sentence;
    private JPanel p_north;
    private JTextField tf_sentence;
    private JTextArea ta_chat;
    private JScrollPane sp_scroll;

    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public ChatPanel() {
        initComponents();
        setSizes();
        addEachComponents();
        setActions();
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    private void setSizes(){
        tf_sentence.setPreferredSize(new Dimension(200, 25));
    }
    private void initComponents() {
        p_sentence = new JPanel();
        p_north = new JPanel();
        p_north.setLayout(new BorderLayout());
        //this.setLayout(new GridLayout(2,1));
        this.setLayout(new BorderLayout());
        tf_sentence = new JTextField();
        ta_chat = new JTextArea();
        setupChat();
    }

    private void setupChat() {
        ta_chat = new JTextArea();
        sp_scroll = new JScrollPane(ta_chat);
        ta_chat.setLineWrap(true);
        ta_chat.setEditable(false);
        sp_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
    }

    private void addEachComponents() {
        p_north.add(sp_scroll, BorderLayout.CENTER);
        p_sentence.add(tf_sentence);
        this.add(p_north, BorderLayout.CENTER);
        this.add(p_sentence, BorderLayout.SOUTH);
    }

    public void printChatMessage(String string) {
        ta_chat.append(string);
        ta_chat.setCaretPosition(ta_chat.getText().length());
    }

    //Peut être à changer un peu pour eviter d'appuyer sur enter par erreur
    public void setActions() {
        tf_sentence.addKeyListener(
            new KeyListener() {
                public void keyPressed(KeyEvent e) {
                    int key = e.getKeyCode();
                    if (key == KeyEvent.VK_ENTER) {
                        String sentence = tf_sentence.getText();
                        ta_chat.append(sentence+"\n");
                        ta_chat.setCaretPosition(ta_chat.getText().length());
                        
                        //sendToServer(sentence); // Envoyer le message au server pour l'envoyer à l'autre user
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
    // SETTERS / GETTERS
    //**************************************************************************

}
