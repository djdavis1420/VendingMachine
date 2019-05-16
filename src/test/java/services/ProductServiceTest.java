package services;

import database.ProductDatabase;
import models.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private ProductService service;
    private ProductDatabase database;

    @Before
    public void setup() {
        database = mock(ProductDatabase.class);
        service = new ProductService(database);
    }

    @Test
    public void isProductAvailable_ShouldReturnTrueWhenDatabaseReturnsCollection() {
        String productLocation = "G8";
        List<Product> products = Collections.singletonList(new Product());
        when(database.getProductByLocation(productLocation)).thenReturn(products);

        boolean actual = service.isProductAvailable(productLocation);

        assertTrue(actual);
    }

    @Test
    public void isProductAvailable_ShouldReturnFalseWhenDatabaseReturnsEmptyCollection() {
        String productLocation = "G8";
        List<Product> products = Collections.emptyList();
        when(database.getProductByLocation(productLocation)).thenReturn(products);

        boolean actual = service.isProductAvailable(productLocation);

        assertFalse(actual);
    }

    @Test
    public void isProductAvailable_ShouldCacheProductOnSelectedProductProperty() {
        String productLocation = "G8";
        List<Product> products = Collections.singletonList(new Product("Snickers", "G8", "Candy Bar", 1.25));
        when(database.getProductByLocation(productLocation)).thenReturn(products);

        service.isProductAvailable(productLocation);

        assert service.selectedProduct.getName().equals("Snickers");
    }

}
