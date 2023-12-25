package jdk.ru.geekbrains.l2.client;

import jdk.ru.geekbrains.l2.server.Server;
import jdk.ru.geekbrains.l2.server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class ClientGUI extends JFrame implements View{
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private Client client;
    JTextArea logClient;
    JPanel panelTop;
    JTextField tfIPAddress;
    JTextField tfPort;
    JTextField tfLogin;
    JPasswordField tfPassword;
    JButton btnLogin;

    JPanel panelBottom;
    JTextField tfMassage;
    JButton btnSend;




    public ClientGUI(Server serverWindow) {
       setting(serverWindow);
       createPanel();
       setVisible(true);
    }

    private void setting(Server server){
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat client");
       // setLocation(server.getX() - 500, server.getY());
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        client = new Client(this, server);
    }

    private void hideHeaderPanel(boolean visible){
        panelTop.setVisible(visible);
    }

    public void message(){
        client.sendMessage(tfMassage.getText());
        tfMassage.setText("");
    }

    private void createPanel(){
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createLog());
        add(createPanelButton(), BorderLayout.SOUTH);
    }

    private Component createHeaderPanel(){
        panelTop = new JPanel(new GridLayout(2, 3));
        tfIPAddress = new JTextField("127.0.0.1");
        tfPort = new JTextField("8189");
        tfLogin = new JTextField("Ivan Ivanovich");
        tfPassword = new JPasswordField("123456");
        btnLogin = new JButton("login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectedToServer();
            }
        });
        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(new JPanel());
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);

        return panelTop;
    }

    private Component createLog(){
        logClient = new JTextArea();
        logClient.setEditable(false);
        return new JScrollPane(logClient);
    }

    private Component createPanelButton(){
        panelBottom = new JPanel(new BorderLayout());
        tfMassage = new JTextField();
        btnSend = new JButton("Send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                message();
            }
        });
        panelBottom.add(tfMassage);
        panelBottom.add(btnSend, BorderLayout.EAST);
        return panelBottom;
    }

    @Override
    protected void processWindowEvent(WindowEvent e){
        super.processWindowEvent(e);
        if(e.getID() == WindowEvent.WINDOW_CLOSED){
            disconnectedFromServer();
        }
    }

    @Override
    public void sendMessage(String message) {
        logClient.append(message);
    }

    @Override
    public void connectedToServer() {
        if(client.connectToServer(tfLogin.getText())){
            hideHeaderPanel(false);
        }
    }

    @Override
    public void disconnectedFromServer() {
        hideHeaderPanel(true);
        client.disconnectFromServer();
    }
}
