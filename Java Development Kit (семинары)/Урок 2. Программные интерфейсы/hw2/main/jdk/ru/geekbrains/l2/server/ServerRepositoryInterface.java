package jdk.ru.geekbrains.l2.server;

import java.util.ArrayList;

public interface ServerRepositoryInterface {
    String readFromFile();
    void writeToFile(ArrayList<String> log);
}
