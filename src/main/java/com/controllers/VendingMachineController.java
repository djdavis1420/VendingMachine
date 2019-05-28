package com.controllers;

import com.models.Coin;
import com.models.Transaction;
import com.services.CurrencyService;
import com.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VendingMachineController {

    private CurrencyService currencyService;
    private ProductService productService;

    @Autowired
    public VendingMachineController(CurrencyService currencyService, ProductService productService) {
        this.currencyService = currencyService;
        this.productService = productService;
    }

    public Transaction processTransaction(String productSelection, List<Coin> coins) {
        Transaction transaction = new Transaction();

        boolean productIsAvailable = productService.isProductAvailable(productSelection);
        if(!productIsAvailable) {
            transaction.setMessage("Product Is Unavailable");
            transaction.setChange(coins);
            return transaction;
        }

        List<Coin> validCoins = coins.stream().filter(CurrencyService::isValidCoin).collect(Collectors.toList());
        double productCost = productService.getProductCost(productSelection);
        double totalFunds = currencyService.countFunds(validCoins);
        boolean sufficientFundsAvailable = productService.hasSufficientFunds(productCost, totalFunds);
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
