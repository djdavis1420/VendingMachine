package com.database;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DatabaseFileReader {

    private static final String PRODUCT_DATABASE = "./src/main/java/com/database/ProductDatabase.csv";

    public List<String[]> getProductListing() {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(PRODUCT_DATABASE));
            CSVReader csvReader = new CSVReader(reader);
            return csvReader.readAll();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
        return null;
    }
}
