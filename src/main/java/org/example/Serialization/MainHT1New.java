package org.example.Serialization;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainHT1New {
    public static void main(String[] args) {

        //Создаем список директорий
        List<String> listOfDirectories = listOfDirectories();
        System.out.println("Список директорий создан!");

        //Создаем директории
        createDirectory(listOfDirectories);
        System.out.println("Директории созданы!");

        //Создаем файлы
        createFile("E:/Programming/Netology/Games/src/main", "Main.java");
        createFile("E:/Programming/Netology/Games/src/main", "Utils.java");
        createFile("E:/Programming/Netology/Games/temp", "temp.txt");
        System.out.println("Файлы созданы!");

        //Записываем лог в созданный файл temp.txt
        String logFileName = "E:/Programming/Netology/Games/temp/temp.txt";
        writeLog(logFileName);
        System.out.println("Работа программы завершена!");


    }

    private static void writeLog(String logFileName) {
        StringBuilder sb = new StringBuilder();
        sb.append("Запись в файле temp.txt." + " Работа программы завершена!");
        try (FileWriter writer = new FileWriter(logFileName,true)) {
            writer.write(String.valueOf(sb));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createFile(String pathname, String fileName) {
        File newFile = new File(pathname, fileName);
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> listOfDirectories() {
        List<String> listOfDirectories = new ArrayList<>();
        listOfDirectories.add("E:/Programming/Netology/Games/src");
        listOfDirectories.add("E:/Programming/Netology/Games/res");
        listOfDirectories.add("E:/Programming/Netology/Games/savegames");
        listOfDirectories.add("E:/Programming/Netology/Games/temp");
        listOfDirectories.add("E:/Programming/Netology/Games/src/main");
        listOfDirectories.add("E:/Programming/Netology/Games/src/test");
        listOfDirectories.add("E:/Programming/Netology/Games/res/drawables");
        listOfDirectories.add("E:/Programming/Netology/Games/res/vectors");
        listOfDirectories.add("E:/Programming/Netology/Games/res/icons");
        return listOfDirectories;
    }

    private static void createDirectory(List<String> listOfDirectories) {
        for (String path : listOfDirectories) {
            File directory = new File(path);
            directory.mkdir();
        }

    }
}
