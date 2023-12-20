package ru.geekbrains.HW1.client;

import ru.geekbrains.HW1.server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class ClientWindowChat extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private ServerWindow server;
    boolean isClientLogin;
    private String name;
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




    public ClientWindowChat(ServerWindow serverWindow) {
        this.server = serverWindow;
        setSize(WIDTH, HEIGHT);
        setTitle("Чат клиента - ");
        setLocation(server.getX() - 500, server.getY());

        createPanel();

        setVisible(true);
    }

    public void answer(String text){
        appendLog(text);
    }

    private void connectToServer(){
        if(server.connectUser(this)){
            appendLog("Вы успешно подключились!\n");
            panelTop.setVisible(false);
            isClientLogin = true;
            name = tfLogin.getText();
            logClient.append(server.getLog());
            if(logClient == null){
                appendLog("Подключение не удалось");
            }
        }
    }

    public void disconnectFromServer(){
        if(isClientLogin){
            panelTop.setVisible(true);
            isClientLogin = false;
            server.disconnectUser(this);
            appendLog("Вы были отключены от сервера!");
        }
    }

    public void message(){
        if(isClientLogin){
            String text = tfMassage.getText();
            if(!text.equals("")){
                server.message(name + ": " + text);
                tfMassage.setText("");
            }
        }
        else{
            appendLog("Нет подключения к серверу");
        }
    }

    private void appendLog(String text){
        logClient.append(text + "\n");
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
                connectToServer();
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
            disconnectFromServer();
        }
    }
}
