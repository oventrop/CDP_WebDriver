package utility.logger.logger_impl;

import utility.logger.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {

    private static FileLogger fileLogger;

    public static final String OUTPUT_PATH = "src/test/resources/log.txt";

    private FileLogger() {
    }

    public static FileLogger getFileLogger() {
        if (fileLogger == null) {
            fileLogger = new FileLogger();
        }
        return fileLogger;
    }

    public void log(String logMessage) {
        File file = new File(OUTPUT_PATH);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.append("\n");
            writer.append(logMessage);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}