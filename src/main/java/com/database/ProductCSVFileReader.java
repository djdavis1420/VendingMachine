package com.database;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ProductCSVFileReader {

    private static final String PRODUCT_CSV = "./src/main/java/com/database/ProductCSV.csv";

    public List<String[]> getProductListing() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(PRODUCT_CSV));
            CSVReader csvReader = new CSVReader(reader);
            return csvReader.readAll();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return null;
    }
}
