package com.services;

import com.database.ProductsDatabase;
import com.models.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private ProductService service;
    private ProductsDatabase database;

    @Before
    public void setup() {
        database = mock(ProductsDatabase.class);
        service = new ProductService(database);
    }

    @Test
    public void isProductAvailable_ShouldReturnTrueWhenDatabaseReturnsCollection() {
        String productLocation = "G8";
        List<Product> products = Collections.singletonList(new Product());
        when(database.selectProductByLocation(productLocation)).thenReturn(products);

        boolean actual = service.isProductAvailable(productLocation);

        assertTrue(actual);
    }

    @Test
    public void isProductAvailable_ShouldReturnFalseWhenDatabaseReturnsEmptyCollection() {
        String productLocation = "G8";
        List<Product> products = Collections.emptyList();
        when(database.selectProductByLocation(productLocation)).thenReturn(products);

        boolean actual = service.isProductAvailable(productLocation);

        assertFalse(actual);
    }

    @Test
    public void isProductAvailable_ShouldCacheProductOnSelectedProductProperty() {
        String productLocation = "G8";
        List<Product> products = Collections.singletonList(new Product("Snickers", "G8", "Candy Bar", 1));
        when(database.selectProductByLocation(productLocation)).thenReturn(products);

        service.isProductAvailable(productLocation);

        assert service.selectedProduct.getName().equals("Snickers");
    }

    @Test
    public void getProductCost_ShouldReturnCostOfSelectedProduct() {
        String productLocation = "G8";
        double expectedCost = 1;
        service.selectedProduct = new Product("Snickers", "G8", "Candy Bar", expectedCost);

        double actualCost = service.getProductCost(productLocation);

        assertEquals(expectedCost, actualCost, .001);
    }

    @Test
    public void getProductCost_ShouldReturnCostOfProductNotCachedOnSelectedProductProperty() {
        String productLocation = "H2";
        double expectedCost = 1.25;
        List<Product> products = Collections.singletonList(new Product("Coke", "H2", "Beverage", expectedCost));
        when(database.selectProductByLocation(productLocation)).thenReturn(products);

        double actualCost = service.getProductCost(productLocation);

        assertEquals(expectedCost, actualCost, .001);
    }

    @Test
    public void hasSufficientFunds_shouldReturnFalseIfProductCostGreaterThanInsertedFunds() {
        double productCost = 1.25;
        double insertedFunds = 1.00;

        boolean actual = service.hasSufficientFunds(productCost, insertedFunds);

        assertFalse(actual);
    }

    @Test
    public void hasSufficientFunds_shouldReturnTrueIfInsertedFundsAreEqualToOrGreaterThanProductCost() {
        double productCost = 1.25;
        double insertedFunds = 1.25;

        boolean actual = service.hasSufficientFunds(productCost, insertedFunds);

        assertTrue(actual);
    }
}
