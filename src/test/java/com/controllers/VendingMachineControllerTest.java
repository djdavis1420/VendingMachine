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
}
