package com.controllers;

import com.models.Coin;
import com.models.Transaction;
import com.services.CurrencyService;
import com.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

public class VendingMachineController {

    private CurrencyService currencyService;
    private ProductService productService;

    public VendingMachineController(CurrencyService currencyService, ProductService productService) {
        this.currencyService = currencyService;
        this.productService = productService;
    }

    public Transaction processTransaction(String productSelection, List<Coin> coins) {
        List<Coin> validCoins = coins.stream().filter(CurrencyService::isValidCoin).collect(Collectors.toList());
        double totalFunds = currencyService.countFunds(validCoins);
        double productCost = productService.getProductCost(productSelection);
        return null;
    }
}
