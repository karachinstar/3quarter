package jdk.ru.geekbrains.l2.client;

import jdk.ru.geekbrains.l2.server.Server;
import jdk.ru.geekbrains.l2.server.ServerRepository;

public class Client {
    private View view;
    private String name;
    private Server server;
    private ServerRepository repository;
    boolean connected;

    public Client(View view, Server server, ServerRepository repository) {
        this.view = view;
        this.server = server;
        this.repository = repository;
    }

    public boolean connectToServer(String name){
        this.name = name;
        if (server.connectUser(this)){
            showOnWindow("Вы успешно подключились");
            String log = repository.readFromFile();
            if(log != null){
                showOnWindow(log);
            }
            return true;
        } else{
            showOnWindow("Подключение не удалось");
            return false;
        }
    }

    public void disconnectFromServer(){
        if (connected){
            connected = false;
            server.disconnectUser(this);
            view.disconnectedFromServer();
            showOnWindow("Вы были отключены от сервера!");
        }
    }

    public void answerFromServer(String messageFromServer){
        showOnWindow(messageFromServer);
    }

    public void sendMessage(String message){
        if (connected){
            if(!message.isEmpty()){
                //server.sendMessage(name + ": " + message);
            }

        }else{
            showOnWindow("Нет подключения к серверу");
        }
    }

    private void showOnWindow(String text){
        view.sendMessage(text + "\n");
    }
}
