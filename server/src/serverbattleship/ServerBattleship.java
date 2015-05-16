
package serverbattleship;

import com.battleship.network.Capsule;
import com.battleship.network.Request;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Anthony
 */
public class ServerBattleship extends JFrame{
    
    Model model = new Model();
    Controller controller = new Controller(model, this);
    
    JLabel l_port;
    JLabel l_ip;
    JTextField tf_port;
    JTextField tf_ip;
    JTextField tf_nickname;
    JTextField tf_sentence;
    JButton b_startserver;
    JButton b_disconnect;
    JTextArea ta_chat;
    JScrollPane sp_scroll;
    
    static ServerSocket serverSocket; // we need this line to open port on our computer
    static Socket socket;  // and this socket to accept connections
    int id = 0; // truc s'inscrment avec les clients et est unique

    static String stringReceived; // string received is for reciving and sending text
    static boolean signal; // signal is true when port is created
    static boolean start = true;

    ServerBattleship() {
        super("Server");
        setSize(430, 540);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        initComponents();
        addScrollBar();
        setComponentsSizes();
        addEachComponents();
        setBtnActions();

        setVisible(true);
    }

    public void initComponents() {
        l_port = new JLabel("Port");
        tf_port = new JTextField("5000");
        l_ip = new JLabel("IP");
        tf_ip = new JTextField("localhost");
        //tf_nickname = new JTextField("Nickname");
        tf_sentence = new JTextField();
        b_startserver = new JButton("Start Server");
        b_disconnect = new JButton("Disconnect");
    }

    public void addScrollBar() {
        ta_chat = new JTextArea();
        ta_chat.setLineWrap(true);
        ta_chat.setEditable(false);
        sp_scroll = new JScrollPane(ta_chat);
        sp_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    public void setComponentsSizes() {
        l_port.setBounds(10, 10, 50, 20);
        tf_port.setBounds(40, 10, 100, 20);
        l_ip.setBounds(150, 10, 20, 20);
        tf_ip.setBounds(170, 10, 240, 20);
        b_startserver.setBounds(10, 40, 190, 20);
        b_disconnect.setBounds(220, 40, 190, 20);
        //tf_nickname.setBounds(250, 40, 160, 20);
        sp_scroll.setBounds(10, 70, 400, 400);
        tf_sentence.setBounds(10, 480, 400, 20);
    }

    public void addEachComponents() {
        Container container = getContentPane();
        container.setLayout(null);
        container.add(l_port);
        container.add(tf_port);
        container.add(l_ip);
        container.add(tf_ip);
        container.add(b_startserver);
        container.add(b_disconnect);
        b_disconnect.setEnabled(false);
        //container.add(tf_nickname);
        container.add(sp_scroll);
        container.add(tf_sentence);
    }

    public void setBtnActions() {
        b_startserver.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            InetAddress thisIp = InetAddress.getLocalHost();
                            String ip = thisIp.getHostAddress().toString();
                            tf_ip.setText(ip);

                        } catch (Exception ex) {
                            tf_ip.setText("Offline!");
                        }

                        int p = 0;
                        try {
                            String port = tf_port.getText();
                            p = Integer.parseInt(port);
                            ta_chat.append("Server en attente de clients sur le port : " + p + "...\n");
                            ta_chat.setCaretPosition(ta_chat.getText().length());
                            serverSocket = new ServerSocket(p);
                            signal = true;
                        } catch (Exception ex) {
                            ta_chat.append("Error: Port " + p + " injoignable\n");
                            ta_chat.setCaretPosition(ta_chat.getText().length());
                            signal = false;
                        }

                        if (signal == true) {
                            AcceptNewClients ob1 = new AcceptNewClients("RunServer", controller, serverSocket);
                            b_startserver.setEnabled(false);
                            b_disconnect.setEnabled(true);
                        }
                    }
                });
        b_disconnect.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        ta_chat.setCaretPosition(ta_chat.getText().length());
                        try {
                            serverSocket.close();
                        } catch (Exception ex) {
                        }

                        for (int i = 0; i < model.getPlayersList().size(); i++) {
                            try {
                                model.getPlayersList().get(i).getInput().close();
                                model.getPlayersList().get(i).getOutput().close();
                            } catch (Exception ex) {
                            }
                        }
                        ta_chat.append("Server disconnected!\n");
                        b_startserver.setEnabled(true);
                        b_disconnect.setEnabled(false);
                    }
                }
        );
        
          tf_sentence.addKeyListener(
            new KeyListener() {
                public void keyPressed(KeyEvent e) {
                    int key = e.getKeyCode();
                    if (key == KeyEvent.VK_ENTER) {
                        String sentence = tf_sentence.getText();
                        
                        ta_chat.append(sentence+"\n");
                        controller.sendCapsuleToAllPlayers(new Capsule(Request.MSG_SERVER, sentence));
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


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerBattleship server = new ServerBattleship();
    }
    
}
