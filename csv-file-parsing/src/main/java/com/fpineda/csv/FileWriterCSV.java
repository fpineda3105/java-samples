package com.fpineda.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * @author fpineda
 */
public class FileWriterCSV implements Runnable {

    private final BlockingQueue<Data> queue;
    private final List<String> headers;

    public FileWriterCSV(BlockingQueue queue, List headers) {
        this.queue = queue;
        this.headers = headers;
    }

    @Override
    public void run() {
        System.out.println("Starting Writing file");
        long start = Instant.now().getEpochSecond();
        try {
            FileWriter out = new FileWriter("Crimes_-_2020_to_Present.csv");
            String[] strHeaders = headers.toArray(new String[0]);
            CSVPrinter printer = CSVFormat.DEFAULT.withHeader(strHeaders).print(out);
            while (true) {
                var record = queue.take();
                if (record.isPoison()) {
                    break;
                }

                printer.printRecord(record.getData().toMap().values());
            }
            printer.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        long end = Instant.now().getEpochSecond();
        System.out.println("Execution Writing time in seconds: " + (end - start));
    }
}
