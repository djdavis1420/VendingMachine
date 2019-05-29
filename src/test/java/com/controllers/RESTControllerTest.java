package com.controllers;

import com.models.Coin;
import com.models.ProductRequest;
import com.models.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.models.Coin.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class RESTControllerTest {

    private VendingMachineController vendingMachineController;
    private RESTController restController;
    private ProductRequest productRequest;
    private static final String AVAILABLE_SELECTION = "G8";
    private static final String UNAVAILABLE_SELECTION = "G2";

    @Before
    public void setup() {
        productRequest = new ProductRequest();
        vendingMachineController = mock(VendingMachineController.class);
        restController = new RESTController(vendingMachineController);
    }

    @Test
    public void purchase_shouldCallVendingMachineControllerWithProduct() {
        List<Coin> coins = Arrays.asList(DOLLAR, QUARTER, DIME, NICKEL);
        productRequest.setInsertedCoins(coins);
        productRequest.setProductLocation(AVAILABLE_SELECTION);

        restController.purchase(productRequest);

        verify(vendingMachineController, times(1)).processTransaction(AVAILABLE_SELECTION, coins);
    }

    @Test
    public void purchase_shouldReturnTransaction() {
        List<Coin> coins = Arrays.asList(DOLLAR, QUARTER, DIME, NICKEL);
        productRequest.setProductLocation(AVAILABLE_SELECTION);
        productRequest.setInsertedCoins(coins);
        Transaction expectedTransaction = new Transaction();
        when(vendingMachineController.processTransaction(AVAILABLE_SELECTION, coins)).thenReturn(expectedTransaction);

        Transaction actualTransaction = restController.purchase(productRequest);

        assertEquals(expectedTransaction, actualTransaction);
    }
}
