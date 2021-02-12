package com.fpineda.csv;

import org.apache.commons.csv.CSVRecord;

/**
 * @author fpineda
 */
public class Data {

    private boolean poison = false;
    private CSVRecord data;

    public Data() {
        this.poison = true;
    }

    public Data(CSVRecord data) {
        this.data = data;
    }

    public CSVRecord getData() {
        return data;
    }

    public boolean isPoison() {
        return poison;
    }
}
