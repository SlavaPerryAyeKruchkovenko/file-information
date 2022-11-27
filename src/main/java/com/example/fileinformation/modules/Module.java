package com.example.fileinformation.modules;

import java.io.File;

public abstract class Module {
    public abstract boolean isSuitableExtension(File file);
    
    public abstract String getDescription();
    
    public abstract void executeCommand(String command, File file);
}
