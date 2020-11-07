package com.sauselabs.schedulingtasks;

import com.sauselabs.FileWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import static java.util.Collections.singleton;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.APPEND;

@Component
public class ServerMonitor {

    @Autowired
    private RestTemplate restTemplate;

    public void setFileWriter(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    @Autowired
    private FileWriter fileWriter;

    @Value("${server.url}")
    private String SERVER_URL;


    private static final Logger log = LoggerFactory.getLogger(ServerMonitor.class);

    @Scheduled(fixedRateString = "${schedule.interval}")
    public void monitorServer() {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(SERVER_URL, String.class);
            HttpStatus statusCode = response.getStatusCode();
            if (statusCode == HttpStatus.OK) {
                log.info("INFO Server is working.");
                fileWriter.writeToLog("INFO Server is working.");
            } else {
                log.error("ERROR Server returned status code: "+ statusCode);
                fileWriter.writeToLog("ERROR Server returned status code "+ statusCode);
            }
        } catch (RestClientException e) {
            log.error("Failed to connect to server");
            fileWriter.writeToLog("ERROR Failed to connect to server.");
        }

    }



}