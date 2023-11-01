package org.example.Serialization;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainHT1 {
    public static void main(String[] args) {

//      Каталог Games создан вручную, создание остальных файлов осуществляется через написание кода.
//      1. Создаем 4 директории в папке Games.
        File src = new File("E:/Programming/Netology/Games/src");
        File res = new File("E:/Programming/Netology/Games/res");
        File savegames = new File("E:/Programming/Netology/Games/savegames");
        File temp = new File("E:/Programming/Netology/Games/temp");
        src.mkdir();
        res.mkdir();
        savegames.mkdir();
        temp.mkdir();

//      2. Создаем директории main и test в каталоге src.
        File main = new File("E:/Programming/Netology/Games/src/main");
        File test = new File("E:/Programming/Netology/Games/src/test");
        main.mkdir();
        test.mkdir();

//      3. В подкатологе main создаем Main.java, Utils.java.
        File mainJava = new File("E:/Programming/Netology/Games/src/main", "Main.java");
        File utilsJava = new File("E:/Programming/Netology/Games/src/main", "Utils.java");
        try {
            mainJava.createNewFile();
            utilsJava.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//      4. В каталоге res создаем 3 директории.
        File drawables = new File("E:/Programming/Netology/Games/res/drawables");
        File vectors = new File("E:/Programming/Netology/Games/res/vectors");
        File icons = new File("E:/Programming/Netology/Games/res/icons");
        drawables.mkdir();
        vectors.mkdir();
        icons.mkdir();

//      5. В директории temp создаем файл temp.txt.
        StringBuilder sb = new StringBuilder();
        sb.append("Файлы были созданы");
        File tempTXT = new File("E:/Programming/Netology/Games/temp", "temp.txt");
        try {
            tempTXT.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (FileWriter writer = new FileWriter("E:/Programming/Netology/Games/temp/temp.txt",true)) {
            writer.write(String.valueOf(sb));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
