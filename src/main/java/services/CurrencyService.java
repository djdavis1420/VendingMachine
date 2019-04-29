package services;

import models.Coin;

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
}