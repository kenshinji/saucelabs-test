package com.sauselabs;

import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.util.Collections.singleton;

public class FileWriter {


    @Value("${logfile.path}")
    private String LOGFILE_PATH;

    public void writeToLog(String msg) {
        try {
            Files.write(Paths.get(LOGFILE_PATH), singleton(msg), APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
