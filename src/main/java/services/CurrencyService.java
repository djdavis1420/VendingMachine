package services;

import models.Coin;

import java.util.List;

import static models.Coin.validCoins;

public class CurrencyService {

    public boolean isValidCoin(Coin coinToValidate) {
        return validateWeight(coinToValidate) && validateDiameter(coinToValidate);
    }

    private boolean validateWeight(Coin coinToValidate) {
        return validCoins.stream().anyMatch(x -> x.weight == coinToValidate.weight);
    }

    private boolean validateDiameter(Coin coinToValidate) {
        return validCoins.stream().anyMatch(x -> x.diameter == coinToValidate.diameter);
    }

    public double countFunds(List<Coin> validCoins) {
        double totalFunds = 0;
        for (Coin coin : validCoins) {
            totalFunds += coin.value;
        }
        return (double) Math.round(totalFunds * 100) / 100;
    }

}