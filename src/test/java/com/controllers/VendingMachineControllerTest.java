package com.controllers;

import com.models.Coin;
import com.services.CurrencyService;
import com.services.ProductService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.models.Coin.*;
import static org.mockito.Mockito.*;

public class VendingMachineControllerTest {

    private CurrencyService currencyService;
    private ProductService productService;
    private VendingMachineController controller;
    private static final String PRODUCT_SELECTION = "G8";

    @Before
    public void setup() {
        currencyService = mock(CurrencyService.class);
        productService = mock(ProductService.class);
        controller = new VendingMachineController(currencyService, productService);
        when(productService.isProductAvailable(PRODUCT_SELECTION)).thenReturn(true);
    }

    @Test
    public void processTransaction_shouldCallCountFundsToCountInsertedCoins() {
        List<Coin> coins = Arrays.asList(DOLLAR, QUARTER, DIME, NICKEL);

        controller.processTransaction(PRODUCT_SELECTION, coins);

        verify(currencyService, times(1)).countFunds(coins);
    }

    @Test
    public void processTransaction_shouldOnlyAcceptValidCoins() {
        Coin invalidCoin = new Coin(100.0, 100.0, 1000.0);
        List<Coin> coins = Arrays.asList(DOLLAR, QUARTER, DIME, NICKEL, invalidCoin);
        List<Coin> validCoins = Arrays.asList(DOLLAR, QUARTER, DIME, NICKEL);

        controller.processTransaction(PRODUCT_SELECTION, coins);

        verify(currencyService, times(1)).countFunds(validCoins);
    }

    @Test
    public void processTransaction_shouldCallCheckProductAvailabilityForSelectedProduct() {
        List<Coin> coins = Arrays.asList(DOLLAR, QUARTER, DIME, NICKEL);

        controller.processTransaction(PRODUCT_SELECTION, coins);

        verify(productService, times(1)).isProductAvailable(PRODUCT_SELECTION);
    }

    @Test
    public void processTransaction_shouldCallGetProductCostForSelectedProduct() {
        List<Coin> coins = Arrays.asList(DOLLAR, QUARTER, DIME, NICKEL);

        controller.processTransaction(PRODUCT_SELECTION, coins);

        verify(productService, times(1)).getProductCost(PRODUCT_SELECTION);
    }

    @Test
    public void processTransaction_shouldCallHasSufficientFundsForSelectedProduct() {
        List<Coin> coins = Arrays.asList(DOLLAR, QUARTER, DIME, NICKEL);
        double productCost = 1.00;
        double totalFunds = 1.40;
        when(productService.getProductCost(PRODUCT_SELECTION)).thenReturn(productCost);
        when(currencyService.countFunds(coins)).thenReturn(totalFunds);

        controller.processTransaction(PRODUCT_SELECTION, coins);

        verify(productService, times(1)).hasSufficientFunds(productCost, totalFunds);
    }
}
