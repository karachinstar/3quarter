package ru.geekbrains.HW1.client;

import ru.geekbrains.HW1.server.ServerWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientWindow extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    public static boolean isClientLogin;

    public static JTextArea getLogClient() {
        return logClient;
    }

    public static void setLogClient(String logClient) {
        ClientWindow.logClient.append(logClient);
    }

    private static final JTextArea logClient = new JTextArea();

    public JButton getBtnSend() {
        return btnSend;
    }

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

    public ClientWindow(ServerWindow serverWindow) {
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
                        isClientLogin = true;
                        logClient.append("Вы успешно подключились к серверу\n");
                        ServerWindow.logServer.append("Пользователь " + tfLogin.getText() + " подключился к беседе\n");
                    }
                }else{
                    logClient.append("Сервер не запущен\n");
                }
            }
        });
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Client");

//        btnSend.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (isClientLogin) {
//                    ServerWindow.logServer.append(tfLogin.getText() + ": " + tfMassage.getText() + "\n");
//                    text = ServerWindow.logServer.getText();
//                    String[] lines = text.split("\n");
//                    text = lines[lines.length - 1];
//                    logClient.append(text + "\n");
//                   // logClient.append(tfLogin.getText() + ": " + tfMassage.getText() + "\n");
//                }else{
//                    logClient.append("Вы не подключены к серверу\n");
//                }
//                //logClient.setText("");
//                tfMassage.setText("");
//            }
//        });
        //panelTop.add(labelServerIp);
        panelTop.add(tfIPAddress);
       // panelTop.add(labelServerPort);
        panelTop.add(tfPort);
        //panelTop.add(labelLogin);
        panelTop.add(tfLogin);
        //panelTop.add(labelPassword);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        add(panelTop, BorderLayout.NORTH);

        panelBottom.add(tfMassage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);

        logClient.setEnabled(true);
        JScrollPane scrollPane = new JScrollPane(logClient);
        add(scrollPane);

        setVisible(true);

    }
}
