package com.services;

import com.models.Coin;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.models.Coin.validCoins;

@Component
public class CurrencyService {

    public static boolean isValidCoin(Coin coinToValidate) {
        return validateWeight(coinToValidate) && validateDiameter(coinToValidate);
    }

    private static boolean validateWeight(Coin coinToValidate) {
        return validCoins.stream().anyMatch(x -> x.weight == coinToValidate.weight);
    }

    private static boolean validateDiameter(Coin coinToValidate) {
        return validCoins.stream().anyMatch(x -> x.diameter == coinToValidate.diameter);
    }

    public double countFunds(List<Coin> validCoins) {
        double totalFunds = 0;
        for (Coin coin : validCoins) {
            totalFunds += coin.value;
        }
        return (double) Math.round(totalFunds * 100) / 100;
    }

    public List<Coin> returnCorrectChange(double insertedFunds, double productCost) {
        double change = insertedFunds - productCost;
        List<Coin> returnedCoins = new ArrayList<>();

        for (Coin validCoin : validCoins) {
            change = (double) Math.round(change * 100) / 100;
            while (change >= validCoin.value) {
                returnedCoins.add(validCoin);
                change -= validCoin.value;
            }
        }

        return returnedCoins;
    }

}