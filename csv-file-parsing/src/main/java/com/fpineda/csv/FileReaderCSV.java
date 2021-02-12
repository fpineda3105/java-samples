package com.fpineda.csv;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;

/**
 * @author fpineda
 */
public class FileReaderCSV implements Runnable {

    private final BlockingQueue<Data> queue;
    private final CSVParser parser;

    public FileReaderCSV(final BlockingQueue queue, final CSVParser parser) {
        this.queue = queue;
        this.parser = parser;
    }

    public void run() {
        System.out.println("Starting Reading file");
        long start = Instant.now().getEpochSecond();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a", Locale.US);
        for (CSVRecord record : this.parser) {
            LocalDateTime dateTime = LocalDateTime.parse(record.get("Date"), formatter);
            if (dateTime.getYear() >= 2020) {
                queue.add(new Data(record));
            }
        }
        queue.add(new Data());
        long end = Instant.now().getEpochSecond();
        System.out.println("Execution Reading time in seconds: " + (end - start));
    }
}
