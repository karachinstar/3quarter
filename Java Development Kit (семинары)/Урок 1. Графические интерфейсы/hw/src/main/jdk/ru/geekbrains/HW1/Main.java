package ru.geekbrains.HW1;

import ru.geekbrains.HW1.client.ClientWindow;
import ru.geekbrains.HW1.server.ServerWindow;

public class Main {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow();
        new ClientWindow(serverWindow);
        new ClientWindow(serverWindow);
    }
}
