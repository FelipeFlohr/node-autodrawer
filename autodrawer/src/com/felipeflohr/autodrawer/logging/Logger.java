package com.felipeflohr.autodrawer.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    public final static Logger logger = new Logger();

    private String folderPath;
    private File logFile;
    private FileWriter fileWriter;
    private final boolean loggingToFile;

    // Regular colors
    private final String RESET = "\u001B[0m";
    private final String RED = "\u001B[31m";
    private final String GREEN = "\u001B[32m";
    private final String YELLOW = "\u001B[33m";
    private final String WHITE = "\u001B[37m";

    // High Intensity
    private final String RED_BRIGHT = "\033[0;91m";
    private final String CYAN_BRIGHT = "\033[0;96m";

    public Logger() {
        loggingToFile = false;
        log(LogLevel.INFO, "Log created");
    }

    public Logger(String folderPath) {
        this.folderPath = folderPath;
        this.logFile = new CreateLogFile(folderPath).getFile();

        try {
            this.fileWriter = new FileWriter(logFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        loggingToFile = true;
        log(LogLevel.INFO, "Log created");
    }

    public void log(LogLevel level, String msg) {
        switch (level) {
            case OK -> System.out.print(GREEN + okMessage(msg) + RESET);
            case INFO -> System.out.print(WHITE + infoMessage(msg) + RESET);
            case CONFIG -> System.out.print(CYAN_BRIGHT + configMessage(msg) + RESET);
            case WARNING -> System.out.print(YELLOW + warningMessage(msg) + RESET);
            case ERROR -> System.out.print(RED + errorMessage(msg) + RESET);
            case FATAL -> System.out.print(RED_BRIGHT + fatalMessage(msg) + RESET);
        }

        if (isLoggingToFile()) {
            try {
                fileWriter.write(logString(level, msg));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String logString(LogLevel level, String msg) {
        return switch (level) {
            case OK -> okMessage(msg); // Green
            case INFO -> infoMessage(msg); // White
            case CONFIG -> configMessage(msg); // Cyan Bright
            case WARNING -> warningMessage(msg); // Yellow
            case ERROR -> errorMessage(msg); // Red
            case FATAL -> fatalMessage(msg); // Red Bright
        };
    }

    private String okMessage(String msg) {
        return "\n[OK] " + msg + " - " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:S").format(new Date());
    }

    private String infoMessage(String msg) {
        return "\n[INFO] " + msg + " - " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:S").format(new Date());
    }

    private String configMessage(String msg) {
        return "\n[CONFIG] " + msg + " - " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:S").format(new Date());
    }

    private String warningMessage(String msg) {
        return "\n[WARNING] " + msg + " - " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:S").format(new Date());
    }

    private String errorMessage(String msg) {
        return "\n[ERROR] " + msg + " - " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:S").format(new Date());
    }

    private String fatalMessage(String msg) {
        return "\n[FATAL] " + msg + " - " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:S").format(new Date());
    }

    // Getters and Setters
    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public File getLogFile() {
        return logFile;
    }

    public void setLogFile(File logFile) {
        this.logFile = logFile;
    }

    public boolean isLoggingToFile() {
        return this.loggingToFile;
    }
}
