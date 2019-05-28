package com.controllers;

import com.database.ProductsDatabase;
import com.models.Coin;
import com.models.Product;
import com.models.Transaction;
import com.services.CurrencyService;
import com.services.ProductService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.models.Coin.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VendingMachineControllerIT {

    private ProductsDatabase database;
    private VendingMachineController controller;
    private ProductService productService;
    private CurrencyService currencyService;

    @Before
    public void setup() {
        database = mock(ProductsDatabase.class);
        currencyService = new CurrencyService();
        productService = new ProductService(database);
        controller = new VendingMachineController(currencyService, productService);
    }

    @Test
    public  void processTransaction_shouldReturnTransactionForProductIsUnavailable() {
        String productLocation = "G2";
        List<Coin> coins = Arrays.asList(DOLLAR, QUARTER, DIME, NICKEL);
        when(database.selectProductByLocation(productLocation)).thenReturn(Collections.emptyList());

        String expectedMessage = "Product Is Unavailable";
        List<Coin> expectedChange = coins;
        Product expectedProduct = null;

        Transaction actual = controller.processTransaction(productLocation, coins);

        assertEquals(expectedMessage, actual.getMessage());
        assertEquals(expectedChange, actual.getChange());
        assertEquals(expectedProduct, actual.getProduct());
    }

    @Test
    public void processTransaction_shouldReturnTransactionForInsufficientFundsProvided() {
        String productLocation = "G8";
        List<Coin> coins = Arrays.asList(DIME, NICKEL);
        Product product = new Product("Snickers", "G8", "Candy", 1);
        when(database.selectProductByLocation(productLocation)).thenReturn(Collections.singletonList(product));

        String expectedMessage = "Insufficient Funds Provided";
        List<Coin> expectedChange = coins;
        Product expectedProduct = null;

        Transaction actual = controller.processTransaction(productLocation, coins);

        assertEquals(expectedMessage, actual.getMessage());
        assertEquals(expectedChange, actual.getChange());
        assertEquals(expectedProduct, actual.getProduct());
    }

    @Test
    public void processTransaction_shouldReturnTransactionForSuccessfulPurchase() {
        String productLocation = "G8";
        List<Coin> coins = Arrays.asList(DOLLAR, QUARTER, DIME, NICKEL);
        Product product = new Product("Snickers", "G8", "Candy", 1);
        when(database.selectProductByLocation(productLocation)).thenReturn(Collections.singletonList(product));

        String expectedMessage = "Thank You! Enjoy Your Product!";
        List<Coin> expectedChange = Arrays.asList(QUARTER, DIME, NICKEL);
        Product expectedProduct = product;

        Transaction actual = controller.processTransaction(productLocation, coins);

        assertEquals(expectedMessage, actual.getMessage());
        assertEquals(expectedChange, actual.getChange());
        assertEquals(expectedProduct, actual.getProduct());
    }

}
