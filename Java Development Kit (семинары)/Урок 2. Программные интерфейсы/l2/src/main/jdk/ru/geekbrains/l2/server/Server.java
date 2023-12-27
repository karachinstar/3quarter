package jdk.ru.geekbrains.l2.server;

import jdk.ru.geekbrains.l2.client.Client;

import java.util.ArrayList;
import java.util.List;

public class Server implements ServerInterface {
    List<Client> clientGUIList;
    private static boolean isServerWorking;
    private ArrayList<String> allDialogs = new ArrayList<>();
    private ServerRepositoryInterface repository;

    public Server(ServerRepositoryInterface repository){
        new ServerWindow(this, this);
        this.repository = repository;
        clientGUIList = new ArrayList<>();

    }
    public boolean connectUser(Client clientGUI){
        if(!getStatusServer()){
            return false;
        }
        clientGUIList.add(clientGUI);
        return true;
    }

    public void disconnectUser(Client client) {
        clientGUIList.remove(client);
        if (client != null){
            client.disconnectFromServer();
        }
    }

    @Override
    public void statusServer(boolean status) {
        isServerWorking = status;
    }
    public static boolean getStatusServer(){
        return isServerWorking;
    }


    public void sendMessage(String text) {
        if(!isServerWorking){
            return;
        }
        text += "";
        appendLog(text);
        answerAll(text);
    }

    private void answerAll(String text) {
        allDialogs.add(text);
        repository.writeToFile(allDialogs);

        for (Client client : clientGUIList) {
            client.receiveMessageFromServer(text); // Отправляем сообщение каждому клиенту
        }
    }

    private void appendLog(String text) {
        ServerWindow.logServer.append(text + "\n");
    }
}
