package jdk.ru.geekbrains.l2;

import jdk.ru.geekbrains.l2.client.Client;
import jdk.ru.geekbrains.l2.client.ClientGUI;
import jdk.ru.geekbrains.l2.server.Server;
import jdk.ru.geekbrains.l2.server.ServerRepository;
import jdk.ru.geekbrains.l2.server.ServerRepositoryInterface;
import jdk.ru.geekbrains.l2.server.ServerWindow;

public class Main {
    public static void main(String[] args) {
        ServerRepositoryInterface repository =
                new ServerRepository("dialogsBase.txt");
        Server server = new Server(repository);
        ServerRepository serverRepository
                = new ServerRepository("dialogsBase.txt");
        new ClientGUI(server, serverRepository);
        new ClientGUI(server, serverRepository);


    }
}