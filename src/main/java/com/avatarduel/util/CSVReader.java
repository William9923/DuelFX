package com.avatarduel.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CSVReader is a reader with given filename and separator.
 * IMPORTANT NOTE:
 * We assume that the csv doesn't have separator in the cells.
 * This implementation only split each line using separator
 * into array of values.
 *
 * @author Laboratorium Programming
 */
public class CSVReader {
    private File csvFile;
    private String separator;
    /**
     * If set to true, CSVReader will skip the first line.
     * For example, when the csv has header.
     */
    private boolean skipFirstLine;

    /**
     * Creates a new reader from a file, using a separator
     * @param csvFile file csv
     * @param separator separator of csv file
     */
    public CSVReader(File csvFile, String separator) {
        this.csvFile = csvFile;
        this.separator = separator;
        this.skipFirstLine = false;
    }

    /**
     * Set the reader to skip the first line, because the first line
     * is the header of csv
     * @param isSkippingHeader true if header should be skipped
     */
    public void setSkipHeader(boolean isSkippingHeader) {
        this.skipFirstLine = isSkippingHeader;
    }

    /**
     * Reads the csv file into list of string array.
     * @throws IOException exception when reading csv file.
     *         for example: file not found error
     * @return list of string array. Each list item represent a row.
     */
    public List<String[]> read() throws IOException {
        String line;
        boolean firstLine = true;
        FileReader fileReader = new FileReader(this.csvFile);
        BufferedReader br = new BufferedReader(fileReader);
        ArrayList<String[]> list = new ArrayList<String[]>();

        while ((line = br.readLine()) != null) {
            if (!firstLine || !this.skipFirstLine) {
                String[] row = line.split(this.separator);
                // This code will ignore double quotes, if present as first and last character
                for (int i = 0; i < row.length; i++) {
                    String val = row[i];
                    if (val.length() >= 2 && val.charAt(0) == '"' && val.charAt(val.length() - 1) == '"') {
                        row[i] = val.substring(1, val.length() - 1);
                    }
                }
                list.add(row);
            }
            firstLine = false;
        }
        return list;
    }

}