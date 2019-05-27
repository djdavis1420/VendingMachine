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
        Transaction transaction = new Transaction();
        List<Coin> validCoins = coins.stream().filter(CurrencyService::isValidCoin).collect(Collectors.toList());
        double totalFunds = currencyService.countFunds(validCoins);
        boolean productIsAvailable = productService.isProductAvailable(productSelection);
        double productCost = productService.getProductCost(productSelection);
        boolean sufficientFundsAvailable = productService.hasSufficientFunds(productCost, totalFunds);

        if(!productIsAvailable) {
            transaction.setMessage("Product Is Unavailable");
            transaction.setChange(coins);
            return transaction;
        }

        if(!sufficientFundsAvailable) {
            transaction.setMessage("Insufficient Funds Provided");
            transaction.setChange(coins);
            return transaction;
        }

        List<Coin> changeToReturn = currencyService.returnCorrectChange(totalFunds, productCost);
        transaction.setMessage("Thank You! Enjoy Your Product!");
        transaction.setChange(changeToReturn);
        transaction.setProduct(productService.getSelectedProduct());
        return transaction;
    }
}
