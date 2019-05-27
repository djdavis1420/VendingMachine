package com.controllers;

import com.models.Coin;
import com.models.Transaction;
import com.services.CurrencyService;
import com.services.ProductService;

import java.util.List;

public class VendingMachineController {

    private CurrencyService currencyService;
    private ProductService productService;

    public VendingMachineController(CurrencyService currencyService, ProductService productService) {
        this.currencyService = currencyService;
        this.productService = productService;
    }

    public Transaction processTransaction(String productSelection, List<Coin> coins) {
        double totalFunds = currencyService.countFunds(coins);
        return null;
    }
}
