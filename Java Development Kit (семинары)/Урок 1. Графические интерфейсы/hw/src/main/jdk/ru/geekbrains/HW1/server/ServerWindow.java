package ru.geekbrains.HW1.server;
import ru.geekbrains.HW1.client.ClientWindowChat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame {
    private static final int POS_X = 500;
    private static final int POS_Y = 550;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");
    private final JButton openUserChat = new JButton("Open chat");
    public static final JTextArea logServer = new JTextArea();
    public static boolean isServerWorking;

    public ServerWindow(){
        isServerWorking = false;
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    isServerWorking = false;
                    logServer.append("Сервер остановлен \n");
                }else{
                    logServer.append("Сервер уже остановлен \n");
                }
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isServerWorking) {
                    isServerWorking = true;
                    logServer.append("Запуск сервера \n");
                }else{
                    logServer.append("Сервер уже работает \n");
                }
            }
        });

        openUserChat.addActionListener(e -> {
            if(isServerWorking)
                new ClientWindowChat();
            else logServer.append("Сервер неактивен\n");
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);
        JPanel panButton = new JPanel(new GridLayout(1, 3));
        //setLayout(new GridLayout(1, 2));
        panButton.add(btnStart);
        panButton.add(btnStop);
        panButton.add(openUserChat);
        add(panButton, BorderLayout.SOUTH);
        add(logServer);


        setVisible(true);
    }
}