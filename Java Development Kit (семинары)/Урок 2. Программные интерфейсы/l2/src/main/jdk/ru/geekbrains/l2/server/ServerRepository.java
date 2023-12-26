package jdk.ru.geekbrains.l2.server;

import java.io.*;
import java.util.ArrayList;

public class ServerRepository implements ServerRepositoryInterface{
    private String path;
    public ServerRepository(String path){
        this.path = path;
    }

    @Override
    public String readFromFile() {
        File file = new File(path);
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

    @Override
    public void writeToFile(ArrayList<String> log) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string: log) {
            if (!stringBuilder.isEmpty()) stringBuilder.append("\n");
            stringBuilder.append(string);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.append(stringBuilder);
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
