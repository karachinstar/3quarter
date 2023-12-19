package ru.geekbrains.HW1.server;
import ru.geekbrains.HW1.client.ClientWindowChat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServerWindow extends JFrame {
    private static final int POS_X = 500;
    private static final int POS_Y = 550;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    public static String LOG_FILE = "chat_log.txt";


    List<ClientWindowChat> clientWindowChatList;
    JButton btnStart;
    JButton btnStop;
    JButton openUserChat;

    JTextArea logServer;
    boolean isServerWorking;

    public ServerWindow(){
        clientWindowChatList = new ArrayList<>();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);
        createPanel();
        setVisible(true);
    }

    public boolean connectUser(ClientWindowChat clientWindowChat){
        if(!isServerWorking){
            return false;
        }
        clientWindowChatList.add(clientWindowChat);
        return true;
    }

    public String getLog(){
        return loadChatHistory();
    }

    public void disconnectUser(ClientWindowChat clientWindowChat){
        clientWindowChatList.remove(clientWindowChat);
        if (clientWindowChat != null){
            clientWindowChat.disconnectFromServer();
        }
    }

    public void message(String text){
        if(!isServerWorking){
            return;
        }
        text += "";
        appendLog(text);
        answerAll(text);
        saveMessageToFile(text);
    }

    private void answerAll(String text){
        for (ClientWindowChat clientWindowChat: clientWindowChatList){
            clientWindowChat.answer(text);
        }
    }

    private void saveMessageToFile(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(message);
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String loadChatHistory() {
        File file = new File(LOG_FILE);
        StringBuilder stringBuilder = new StringBuilder();
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                return stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void appendLog(String text){
        logServer.append(text + "\n");
    }

    private void createPanel(){
        logServer = new JTextArea();
        logServer.setEditable(true);
        add(new JScrollPane(logServer));
        add(createButtons(), BorderLayout.SOUTH);
        logServer.append(getLog());
    }

    private Component createButtons(){
        JPanel panButton = new JPanel(new GridLayout(1, 3));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");
        openUserChat = new JButton("Open chat");
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    isServerWorking = false;
                    while (!clientWindowChatList.isEmpty()){
                        disconnectUser(clientWindowChatList.get(clientWindowChatList.size() - 1));
                    }
                    appendLog("Сервер остановлен \n");
                }else{
                    appendLog("Сервер уже остановлен \n");
                }
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isServerWorking) {
                    isServerWorking = true;
                    appendLog("Запуск сервера \n");
                }else{
                    appendLog("Сервер уже работает \n");
                }
            }
        });
        openUserChat.addActionListener(e -> {
            if(isServerWorking)
                new ClientWindowChat(this);
            else appendLog("Сервер остановлен\n");
        });
        panButton.add(btnStart);
        panButton.add(btnStop);
        panButton.add(openUserChat);
        return panButton;

    }
}