/* 
 * Creation : 5 mai 2015
 * Project Computer Science L2 Semester 4 - BattleShip
 */
package com.battleship.views.app;

import com.battleship.asset.Config;
import com.battleship.asset.Session;
import com.battleship.asset.ThemeManager;
import com.battleship.constants.GameConstants;
import com.battleship.exceptions.ExecError;
import com.battleship.exceptions.LanError;
import com.battleship.main.DebugTrack;
import com.battleship.network.Network;
import com.battleship.uibutton.ImgButton;
import com.battleship.uibutton.UiButton;
import com.battleship.uibutton.ZozoDecorator;
import static com.battleship.views.app.ApplicationFrame.LIST_GAMES;
import com.battleship.views.tools.PagePanel;
import com.battleship.views.tools.UiDialog;
import com.battleship.views.tools.UiElement;
import com.battleship.views.tools.WindowFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * @since   5 mai 2015
 * @author  Constantin MASSON
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class ConnectPanel extends PagePanel implements GameConstants, UiElement {
    private HeadBar                 p_hb;
    private JPanel                  p_container;
    private JPanel                  p_buttons;
    private JPanel                  p_connect;
    private UiButton                b_validate;
    private UiButton                b_reset;
    private UiButton                b_back;
    private ContainerPanel          p_centeredPane;
    private JPanel                  p_bigCont;
    private JTextField              tf_ipserver;
    private JLabel                  l_ipserver;

    //Images
    private Image background;
    
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public ConnectPanel(WindowFrame pFrame) throws ExecError {
        super(pFrame);
        this.setPreferredSize(Config.getDimValues_dim("default-dim-appframe"));
        this.initComponents();
        this.setBtnActions();
        this.defaultValues();
        this.loadUI();
    }
    
    private void initComponents() throws ExecError {
        p_buttons           = new JPanel();
        p_hb                = new HeadBar(this);
        tf_ipserver         = new JTextField("127.0.0.1");
        l_ipserver          = new JLabel("IP Server : ");
        p_container         = new JPanel();
        p_bigCont           = new JPanel();
        p_connect           = new JPanel(); 
        p_centeredPane      = new ContainerPanel();

        
        this                .setLayout(new BorderLayout());
        p_connect           .setLayout(new FlowLayout());
        p_container         .setLayout(new BorderLayout());
        p_bigCont           .setLayout(new GridBagLayout());
        p_centeredPane      .setLayout(new BorderLayout());
        
        tf_ipserver         .setPreferredSize(new Dimension(100,20));
        p_centeredPane      .setPreferredSize(new Dimension(400,400));

        b_validate = new ZozoDecorator(new ImgButton(406100, 406200, 406300)).getUiButton();
        b_reset    = new ZozoDecorator(new ImgButton(405100, 405200, 405300)).getUiButton();
        b_back     = new ZozoDecorator(new ImgButton(404100, 404200, 404300)).getUiButton();
        
        l_ipserver          .setForeground(Color.WHITE);
        l_ipserver          .setFont(new Font("Arial", Font.BOLD, 20));
        p_buttons           .add(b_back);
        p_buttons           .add(b_reset);
        p_buttons           .add(b_validate);
        
        p_bigCont           .setOpaque(false);
        p_container         .setOpaque(false);
        p_centeredPane      .setOpaque(false);
        p_buttons           .setOpaque(false);
        p_connect           .setOpaque(false);
    
        p_connect           .add(l_ipserver);
        p_connect           .add(tf_ipserver);
        
        p_centeredPane      .add(p_connect,BorderLayout.CENTER);
        p_centeredPane      .add(p_buttons, BorderLayout.SOUTH);
        p_bigCont           .add(p_centeredPane);
        p_container         .add(p_bigCont,BorderLayout.CENTER);
        
        this.add(p_hb,          BorderLayout.NORTH);
        this.add(p_container,   BorderLayout.CENTER);
    }
    
    private void defaultValues(){
        tf_ipserver.setText("localhost");
        //tf_ipserver.setText("192.168.1.55");
    }
    
    private void setBtnActions() {
        b_validate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        connection();
                        frame.rooting(LIST_GAMES, null);
                        UiDialog.showWarning("Yeeaah!!", "Successfully connected");
                    } catch(LanError ex) {
                        UiDialog.showError("Connection Error", ex.getMessage());
                    }
                }
            }
        );
        b_reset.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("DEBUG in ConnectPanel");
                    defaultValues();
                }
            }
        );
        b_back.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        DebugTrack.showExecMsg("Return back");
                        Session.disconnect();
                        goPreviousPage();
                    } catch(LanError ex) {
                        UiDialog.showError("Connection Error", ex.getMessage());
                    }
                    
                }
            }
        );
        
    }//End setBtnActions
    

    //**************************************************************************
    // METHODS
    //**************************************************************************
    /**
     * Connect to server
     * @throws LanError if unable to connect
     */
    private void connection() throws LanError{
        Network c = new Network();
        c.connectToIP(this.tf_ipserver.getText());
    }
    
    
    //**************************************************************************
    // OVERRIDE METHODS
    //**************************************************************************
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.background, 0, 0, this.getWidth(), this.getHeight(), this);
    }
    
    @Override
    public void initPage() throws ExecError {
    }
    
    @Override
    protected void goNextPage() {
    }
    
    @Override
    protected void goPreviousPage(){
        String msg      = "Are you sure you want to go back? You will be disconnected";
        String title    = "Warning";
        int choice = UiDialog.showYesNoWarning(title, msg);
        if(choice == JOptionPane.OK_OPTION){
            frame.rooting(ApplicationFrame.CHOOSE_GAME, null);
        }
    }

    @Override
    public void loadUI() {
        this.reloadUI();
    }
    
    @Override
    public void reloadUI() {
        this.background = ThemeManager.getTheme().getImg(417000);
        this.p_hb       .reloadUI();
        this.b_back     .reloadUI();
        this.b_reset    .reloadUI();
        this.b_validate .reloadUI();
        this.repaint();
    }
}
