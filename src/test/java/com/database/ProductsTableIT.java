package com.database;

import com.Application;
import com.models.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductsTableIT {

    @Autowired
    private ProductsTable productsTable;

    @Test
    public void selectProductByLocation_shouldReturnListOfTwoProductsWhenGivenLocationOfG8() {
            String productLocation = "G8";

            List<Product> actual = productsTable.selectProductByLocation(productLocation);
            long expectedCount = 2;
            long actualCount = actual.stream().filter(x -> x.getName().equals("Snickers")).count();

            assertEquals(expectedCount, actualCount);
    }

    @Test
    public void selectProductByLocation_shouldReturnEmptyListWhenProductNotFound() {
        String productLocation = "G2";
        List expected = Collections.emptyList();
        List<Product> actual = productsTable.selectProductByLocation(productLocation);
        assertEquals(expected, actual);
    }
}
