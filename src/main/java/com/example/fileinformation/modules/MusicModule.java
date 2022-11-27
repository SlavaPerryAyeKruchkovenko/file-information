package com.example.fileinformation.modules;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.tritonus.share.sampled.file.TAudioFileFormat;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

@Component
public class MusicModule extends Module {
    @Override
    public boolean isSuitableExtension(File file) {
        String extension = FilenameUtils.getExtension(file.getName());
        return extension.equals("mp3") || extension.equals("wav");
    }
    
    @Override
    public String getDescription() {
        return "name - Название трека\n" +
            "time - Длительность в секундах\n" +
            "author - Автор\n";
    }
    
    @Override
    public void executeCommand(String command, File file) {
        switch (command.toLowerCase()) {
            case "name":
                printName(file);
                break;
            case "time":
                printTime(file);
                break;
            case "author":
                printAuthor(file);
                break;
        }
    }

    private void printAuthor(File file) {
        try {
            AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
            if (fileFormat instanceof TAudioFileFormat) {
                System.out.println(fileFormat.properties().get("author"));
            }
        } catch (IOException | UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
    }

    private void printName(File file) {
        try {
            AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
            if (fileFormat instanceof TAudioFileFormat) {
                System.out.println(fileFormat.properties().get("title"));
            }
        } catch (IOException | UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
    }
    
    private void printTime(File file) {
        try {
            AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
            if (fileFormat instanceof TAudioFileFormat) {
                System.out.println((long) fileFormat.properties().get("duration") / (1000000));
            }
        } catch (IOException | UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
    }
}
