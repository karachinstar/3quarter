package ru.geekbrains.HW1.client;

import ru.geekbrains.HW1.server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import static ru.geekbrains.HW1.server.ServerWindow.isServerWorking;
import static ru.geekbrains.HW1.server.ServerWindow.logServer;

public class ClientWindowChat extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    public static boolean isClientLogin;
    public static boolean updateLogClient;

    private final JTextArea logClient = new JTextArea();
    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField tfIPAddress = new JTextField("Server IP address");
    private final JTextField tfPort = new JTextField("Server port");
    private final JTextField tfLogin = new JTextField("Login");
    private final JPasswordField tfPassword = new JPasswordField("Password");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JTextField tfMassage = new JTextField();
    private final JButton btnSend = new JButton("Send");

    public static String text;
    public static String text2;
    private static final String LOG_FILE = "/3quarter/Java Development Kit (семинары)/Урок 1. Графические интерфейсы/hw/src/main/jdk/ru/geekbrains/HW1/chat_log.txt";


    public ClientWindowChat() {
        Timer timer = new Timer(100, new ActionListener() { //creating timer with delay of 1 second
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    text = logServer.getText();
                    String[] lines = text.split("\n");
                    text = lines[lines.length - 1];
                    text2 = logClient.getText();
                    String[] lines2 = text2.split("\n");
                    text2 = lines2[lines2.length - 1];

                    if(text.equals(text2)){


                    }else {
                        logClient.append(text + "\n");
                        //text2 = "";
                        System.out.println(tfLogin.getText() + ": Я добавил");

                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        isClientLogin = false;
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ServerWindow.isServerWorking) {
                    if (tfIPAddress.getText().equals("") || tfPort.getText().equals("")
                            || tfLogin.getText().equals("") || tfPassword.getText().equals("")) {
                        isClientLogin = false;
                        logClient.append("Необходимо заполнить все поля\n");
                    } else {
                        timer.stop();
                        isClientLogin = true;
                        logClient.append("Вы успешно подключились к серверу\n");
                        logServer.append("Пользователь " + tfLogin.getText() + " подключился к беседе\n");
                        loadChatHistory();
                        timer.start();
                    }
                }else{
                    logClient.append("Сервер неактивен\n");
                }
            }
        });
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle(tfLogin.getText());

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (isClientLogin && isServerWorking) {
                    timer.stop();
                    logServer.append(tfLogin.getText() + ": " + tfMassage.getText() + "\n");

                    //text2 = tfLogin.getText() + ": " + tfMassage.getText();
                    logClient.append(tfLogin.getText() + ": " + tfMassage.getText() + "\n");
                    saveMessageToFile(logClient.getText());
                    timer.start();
                    //System.out.println(logClient.getText() + tfLogin.getText());
                }else{
                    logClient.append("Вы не подключены к серверу\n");
                }
                tfMassage.setText("");
            }
        });


        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        add(panelTop, BorderLayout.NORTH);

        panelBottom.add(tfMassage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);
        //timer.start();
        logClient.setEnabled(true);
        //logClient.
        JScrollPane scrollPane = new JScrollPane(logClient);
        add(scrollPane);

        setVisible(true);

    }

    private void saveMessageToFile(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, false))) {
            writer.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadChatHistory() {
        File file = new File(LOG_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    logClient.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
