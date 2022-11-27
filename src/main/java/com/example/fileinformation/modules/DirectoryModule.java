package com.example.fileinformation.modules;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DirectoryModule extends Module {
    @Override
    public boolean isSuitableExtension(File file) {
        return file.isDirectory() && file.exists();
    }
    
    @Override
    public String getDescription() {
        return "files - Список файлов\n" + "size - Размер всех файлов\n" + "modify - последня модификация файла";
    }
    
    @Override
    public void executeCommand(String command, File file) {
        switch (command.toLowerCase()) {
            case "files":
                printFiles(file);
                break;
            case "size":
                printSize(file);
                break;
            case "modify":
                printModify(file);
                break;
        }
    }

    private void printModify(File directory) {
        System.out.println(directory.lastModified());
    }
    
    private void printFiles(File directory) {
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            System.out.println(file.getName());
        }
    }
    private long printSize(File directory) {
        long length = 0;
        File[] files = directory.listFiles();
        if(files != null){
            for (File file : files) {
                if (file.isFile())
                    length += file.length();
                else
                    length += printSize(file);
            }
        }
        return length;
    }
}
