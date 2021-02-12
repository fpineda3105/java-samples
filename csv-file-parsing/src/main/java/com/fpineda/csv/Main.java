package com.fpineda.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.*;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author fpineda
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BlockingQueue<Data> queue = new LinkedBlockingDeque<>();

        String fileName = "Crimes_-_2001_to_Present.csv";
        ClassLoader classLoader = Main.class.getClassLoader();

        File csvFile = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
        Reader in = null;
        try {
            in = new FileReader(csvFile);
        } catch (FileNotFoundException e) {
            System.err.println("Error reading file: " + fileName);
        }

        CSVParser records = null;
        try {
            assert in != null;
            records = new CSVParser(in, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            System.out.println("headers : " + records.getHeaderNames());

        } catch (IOException e) {
            e.printStackTrace();
        }

        assert records != null;
        new Thread(new FileWriterCSV(queue, records.getHeaderNames())).start();
        new Thread(new FileReaderCSV(queue, records)).start();
    }
}
