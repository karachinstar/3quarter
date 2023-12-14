package ru.geekbrains.HW1.client;

import ru.geekbrains.HW1.server.ServerWindow;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import static ru.geekbrains.HW1.server.ServerWindow.logServer;

public class ClientWindow extends JFrame {
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


    public ClientWindow() {
        Timer timer = new Timer(2000, new ActionListener() { //creating timer with delay of 1 second
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    text = logServer.getText();
                    String[] lines = text.split("\n");
                    text = lines[lines.length - 1];

                    if(text.equals(text2)){
                        logClient.append(text2 + "\n");
                        text2 = "";
                        System.out.println("я сделалъ");
                        //return;
                    }else {

                        updateLogClient = true;
                        System.out.println(text + " " +  text2);
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
                        timer.start();
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

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (isClientLogin) {
                    timer.stop();
                    logServer.append(tfLogin.getText() + ": " + tfMassage.getText() + "\n");

                    //text2 = tfLogin.getText() + ": " + tfMassage.getText();
                    logClient.append(text2 + "\n");
                    timer.start();

//                    text = logServer.getText();
//                    String[] lines = text.split("\n");
//                    text = lines[lines.length - 1];
//                    logClient.append(text + "\n");

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
}

