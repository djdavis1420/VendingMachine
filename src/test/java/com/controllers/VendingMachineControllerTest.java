package com.controllers;

import com.models.Coin;
import com.models.Product;
import com.models.Transaction;
import com.services.CurrencyService;
import com.services.ProductService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.models.Coin.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class VendingMachineControllerTest {

    private CurrencyService currencyService;
    private ProductService productService;
    private VendingMachineController controller;
    private static final String AVAILABLE_SELECTION = "G8";
    private static final String UNAVAILABLE_SELECTION = "G2";

    @Before
    public void setup() {
        currencyService = mock(CurrencyService.class);
        productService = mock(ProductService.class);
        controller = new VendingMachineController(currencyService, productService);
        when(productService.isProductAvailable(AVAILABLE_SELECTION)).thenReturn(true);
        when(productService.isProductAvailable(UNAVAILABLE_SELECTION)).thenReturn(false);
    }

    @Test
    public void processTransaction_shouldCallCountFundsToCountInsertedCoins() {
        List<Coin> coins = Arrays.asList(DOLLAR, QUARTER, DIME, NICKEL);

        controller.processTransaction(AVAILABLE_SELECTION, coins);

        verify(currencyService, times(1)).countFunds(coins);
    }

    @Test
    public void processTransaction_shouldOnlyAcceptValidCoins() {
        Coin invalidCoin = new Coin(100.0, 100.0, 1000.0);
        List<Coin> coins = Arrays.asList(DOLLAR, QUARTER, DIME, NICKEL, invalidCoin);
        List<Coin> validCoins = Arrays.asList(DOLLAR, QUARTER, DIME, NICKEL);

        controller.processTransaction(AVAILABLE_SELECTION, coins);

        verify(currencyService, times(1)).countFunds(validCoins);
    }

    @Test
    public void processTransaction_shouldCallCheckProductAvailabilityForSelectedProduct() {
        List<Coin> coins = Arrays.asList(DOLLAR, QUARTER, DIME, NICKEL);

        controller.processTransaction(AVAILABLE_SELECTION, coins);

        verify(productService, times(1)).isProductAvailable(AVAILABLE_SELECTION);
    }

    @Test
    public void processTransaction_shouldCallGetProductCostForSelectedProduct() {
        List<Coin> coins = Arrays.asList(DOLLAR, QUARTER, DIME, NICKEL);

        controller.processTransaction(AVAILABLE_SELECTION, coins);

        verify(productService, times(1)).getProductCost(AVAILABLE_SELECTION);
    }

    @Test
    public void processTransaction_shouldCallHasSufficientFundsForSelectedProduct() {
        List<Coin> coins = Arrays.asList(DOLLAR, QUARTER, DIME, NICKEL);
        double productCost = 1.00;
        double totalFunds = 1.40;
        when(productService.getProductCost(AVAILABLE_SELECTION)).thenReturn(productCost);
        when(currencyService.countFunds(coins)).thenReturn(totalFunds);

        controller.processTransaction(AVAILABLE_SELECTION, coins);

        verify(productService, times(1)).hasSufficientFunds(productCost, totalFunds);
    }

    @Test
    public void processTransaction_shouldReturnAllCoinsAndProductUnavailableMessageForUnavailableProduct() {
        List<Coin> coins = Arrays.asList(DOLLAR, QUARTER, DIME, NICKEL);
        String expectedMessage = "Product Is Unavailable";

        Transaction actual = controller.processTransaction(UNAVAILABLE_SELECTION, coins);

        assertEquals(expectedMessage, actual.getMessage());
        assertEquals(coins, actual.getChange());
    }

    @Test
    public void processTransaction_shouldReturnAllCoinsAndInsufficientFundsMessageWhenInsufficientFundsProvided() {
        List<Coin> coins = Arrays.asList(DOLLAR, QUARTER, DIME, NICKEL);
        when(productService.hasSufficientFunds(anyDouble(), anyDouble())).thenReturn(false);
        String expectedMessage = "Insufficient Funds Provided";

        Transaction actual = controller.processTransaction(AVAILABLE_SELECTION, coins);

        assertEquals(expectedMessage, actual.getMessage());
        assertEquals(coins, actual.getChange());
    }

    @Test
    public void processTransaction_shouldReturnChangeAndProductAndSuccessMessageWhenTransactionIsSuccessful() {
        List<Coin> coins = Arrays.asList(DOLLAR, QUARTER, DIME, NICKEL);

        String expectedMessage = "Thank You! Enjoy Your Product!";
        List<Coin> expectedChange = Arrays.asList(QUARTER, DIME, NICKEL);
        Product expectedProduct = new Product("Snickers", "G8", "Candy" ,1);

        when(currencyService.countFunds(coins)).thenReturn(1.40);
        when(currencyService.returnCorrectChange(anyDouble(), anyDouble())).thenReturn(expectedChange);
        when(productService.getProductCost(AVAILABLE_SELECTION)).thenReturn(1.00);
        when(productService.hasSufficientFunds(anyDouble(), anyDouble())).thenReturn(true);
        when(productService.getSelectedProduct()).thenReturn(expectedProduct);

        Transaction actual = controller.processTransaction(AVAILABLE_SELECTION, coins);

        assertEquals(expectedMessage, actual.getMessage());
        assertEquals(expectedChange, actual.getChange());
        assertEquals(expectedProduct, actual.getProduct());
    }
}
