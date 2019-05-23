package com.database;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class DatabaseFileReaderTest {

    private DatabaseFileReader DatabaseFileReader;

    @Before
    public void setup() {
        DatabaseFileReader = new DatabaseFileReader();
    }

    @Test
    public void getProductListing_shouldRetrieveProductListingFromDatabaseFile() {
        List<String[]> databaseListing = DatabaseFileReader.getProductListing();
        List<String> productNames = databaseListing.stream().map(x -> x[0]).collect(Collectors.toList());
        assert productNames.contains("Snickers");
    }
}
