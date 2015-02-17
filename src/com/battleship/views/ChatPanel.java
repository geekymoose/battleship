/* 
 * Creation : 11 févr. 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @date 11 févr. 2015
 * @author Constantin MASSON
 * @author Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class ChatPanel extends JPanel {

    private JTextField tf_sentence;
    private JTextArea ta_chat;
    private JScrollPane sp_scroll;

    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public ChatPanel() {
        this.setLayout(null);
        initComponents();
        setupChat();
        addEachComponents();
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    private void initComponents() {
        tf_sentence = new JTextField();
        ta_chat = new JTextArea();
        sp_scroll = new JScrollPane();
    }

    private void setupChat() {
        ta_chat = new JTextArea();
        ta_chat.setLineWrap(true);
        ta_chat.setEditable(false);
        sp_scroll = new JScrollPane(ta_chat);
        sp_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    private void addEachComponents() {
        this.add(ta_chat);
        this.add(sp_scroll);
        this.add(tf_sentence);
    }

    public void printChatMessage(String string) {
        ta_chat.append(string);
        ta_chat.setCaretPosition(ta_chat.getText().length());
    }

    //Peut être à changer un peu pour eviter d'appuyer sur enter par erreur
    public void sendMessage() {
        tf_sentence.addKeyListener(
            new KeyListener() {
                public void keyPressed(KeyEvent e) {
                    int key = e.getKeyCode();
                    if (key == KeyEvent.VK_ENTER) {
                        String sentence = tf_sentence.getText();
                        ta_chat.append(sentence);
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
