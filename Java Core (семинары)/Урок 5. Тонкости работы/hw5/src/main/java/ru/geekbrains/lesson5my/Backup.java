package ru.geekbrains.lesson5my;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Backup {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ведите директорию на копирование: ./");
        String s = sc.nextLine();
        String sourceDirectory = "./" + s;
        String backupDirectory = "./backup/" + s;

        createBackup(sourceDirectory, backupDirectory);
        System.out.printf("Файлы скопированы в директорию %s\n", backupDirectory);
    }

    public static void createBackup(String sourceDirectory, String backupDirectory) throws IOException {
        // Создаем папку для резервных копий, если ее нет
        File backupDir = new File(backupDirectory);
        if (!backupDir.exists()) {
            backupDir.mkdirs();
        }

        // Получаем список файлов в директории
        File sourceDir = new File(sourceDirectory);
        File[] filesToBackup = sourceDir.listFiles();


        // Копируем каждый файл в папку с резервными копиями
        for (File file : filesToBackup) {
            if(file.isDirectory()){
                createBackup(file.getAbsolutePath(), backupDirectory +"/"+ file.getName());
            }
            else {
                Files.copy(file.toPath(), new File(backupDir.getPath() + "/" + file.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);

            }
        }

    }
}