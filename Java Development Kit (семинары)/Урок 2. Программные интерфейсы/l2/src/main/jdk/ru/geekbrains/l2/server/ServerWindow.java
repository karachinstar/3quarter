package jdk.ru.geekbrains.l2.server;
import jdk.ru.geekbrains.l2.client.Client;
import jdk.ru.geekbrains.l2.client.ClientGUI;
import ru.geekbrains.HW1.client.ClientWindowChat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServerWindow extends JFrame implements ServerInterface{
    private static final int POS_X = 500;
    private static final int POS_Y = 550;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    public static String LOG_FILE = "chat_log.txt";
    JButton btnStart;
    JButton btnStop;
    JButton openUserChat;

    static JTextArea logServer;
    boolean isServerWorking;
    private ServerInterface listener;
    private Server server;

    public ServerWindow(ServerInterface listener, Server server){
        this.listener = listener;
        this.server = server;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);
        createPanel();
        setVisible(true);
    }

//    public void disconnectUser(ClientGUI clientGUI){
//        clientGUIList.remove(clientGUI);
//        if (clientGUI != null){
//            clientGUI.disconnectedFromServer();
//        }
//    }

    private void appendLog(String text){
        logServer.append(text + "\n");
    }

    private void createPanel(){
        logServer = new JTextArea();
        logServer.setEditable(true);
        add(new JScrollPane(logServer));
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons(){
        JPanel panButton = new JPanel(new GridLayout(1, 3));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");
        openUserChat = new JButton("Open chat");
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Server.getStatusServer()) {
                    listener.statusServer(false);
                    appendLog("Сервер остановлен \n");

                }else{
                    appendLog("Сервер уже остановлен \n");
                }
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Server.getStatusServer()) {
                    listener.statusServer(true);
                    appendLog("Запуск сервера \n");
                }else{
                    appendLog("Сервер уже работает \n");
                }
            }
        });
//        openUserChat.addActionListener(e -> {
//            if(Server.getStatusServer())
//                new ClientGUI(this.getConnection());
//                //clientGUIList.add(new ClientGUI(this.getConnection()));
//            else appendLog("Сервер остановлен\n");
//        });
        panButton.add(btnStart);
        panButton.add(btnStop);
        panButton.add(openUserChat);
        return panButton;

    }


    @Override
    public void statusServer(boolean status) {
        isServerWorking = status;
    }

//    public Server getConnection() {
//        return null;
//    }
}