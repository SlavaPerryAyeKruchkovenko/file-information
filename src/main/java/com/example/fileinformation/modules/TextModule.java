package com.example.fileinformation.modules;

import org.springframework.stereotype.Component;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.*;

@Component
public class TextModule extends Module {
    @Override
    public boolean isSuitableExtension(File file) {
        String extension = FilenameUtils.getExtension(file.getName());
        return extension.equals("txt");
    }
    
    @Override
    public String getDescription() {
        return "count - Вывод количества строк\n" +
                "chars-count - Вывод частоты входа каждого символа\n" +
                "word-count - Вывод количества слов\n";
    }
    
    @Override
    public void executeCommand(String command, File file) {
        switch (command.toLowerCase().trim()) {
            case "count":
                printCountLines(file);
                break;
            case "chars-count":
                printCountsChars(file);
                break;
            case "word-count":
                printWordCount(file);
                break;
        }
    }
    
    private void printCountLines(File file) {
        try{
            int lines = 0;
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                lines++;
                scanner.nextLine();
            }
            scanner.close();
            System.out.println("Количество строк: " + lines);
        }
        catch(Exception ex){
            System.out.println("Некоректный файл ");
        }
    }
    
    private void printCountsChars(File file) {
        Map<Character, Integer> charsMap = new HashMap<>();
        try{
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (char c : line.toCharArray()) {
                    charsMap.put(c, charsMap.getOrDefault(c, 0) + 1);
                }
            }
            scanner.close();
            charsMap.forEach((character, count) -> System.out.println("'" + character + "': " + count));
        }
        catch(Exception ex){
            System.out.println("Некоректный файл");
        }

    }
    private static void printWordCount(File file) {
        try{
            Scanner scanner = new Scanner(file);
            int words = 0;
            while (scanner.hasNextLine()) {
                String[] wordsArr = scanner.nextLine().trim().split(" ");
                words = words + wordsArr.length;
            }

            System.out.println("Количество слов: " + words);
            scanner.close();
        }
        catch (Exception ex){
            System.out.println("Некоректный Файл");
        }
    }
}
