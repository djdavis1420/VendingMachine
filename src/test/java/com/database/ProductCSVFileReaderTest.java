package com.database;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class ProductCSVFileReaderTest {

    private ProductCSVFileReader ProductCSVFileReader;

    @Before
    public void setup() {
        ProductCSVFileReader = new ProductCSVFileReader();
    }

    @Test
    public void getProductListing_shouldRetrieveProductListingFromCSVFile() {
        List<String[]> productListing = ProductCSVFileReader.getProductListing();
        List<String> productNames = productListing.stream().map(x -> x[0]).collect(Collectors.toList());
        assert productNames.contains("Snickers");
    }
}
