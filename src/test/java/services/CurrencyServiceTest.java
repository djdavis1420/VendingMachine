package services;

import models.Coin;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static models.Coin.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CurrencyServiceTest {

    private CurrencyService currencyService;

    @Before
    public void setup() {

        currencyService = new CurrencyService();

    }

    @Test
    public void isValidCoin_shouldReturnTrueForDollar() {

        boolean actual = currencyService.isValidCoin(DOLLAR);

        assertTrue(actual);
    }

    @Test
    public void isValidCoin_shouldReturnTrueForNickel() {

        boolean actual = currencyService.isValidCoin(NICKEL);

        assertTrue(actual);
    }

    @Test
    public void isValidCoin_shouldReturnFalseForDollarWithInvalidWeight() {

        Coin coinWithInvalidWeight = new Coin(66, DOLLAR.diameter, DOLLAR.value);

        boolean actual = currencyService.isValidCoin(coinWithInvalidWeight);

        assertFalse(actual);
    }

    @Test
    public void isValidCoin_shouldReturnFalseForDollarWithInvalidDiameter() {

        Coin coinWithInvalidDiameter = new Coin(DOLLAR.weight, 66, DOLLAR.value);

        boolean actual = currencyService.isValidCoin(coinWithInvalidDiameter);

        assertFalse(actual);
    }

    @Test
    public void countFunds_shouldReturnTotalValueOfOneQuarter() {

        List<Coin> validCoins = new ArrayList<>();
        validCoins.add(QUARTER);

        double actual = currencyService.countFunds(validCoins);

        assert actual == 0.25;
    }

    @Test
    public void countFunds_shouldReturnTotalValueOfTwoQuarters() {

        List<Coin> validCoins = new ArrayList<>();
        validCoins.add(QUARTER);
        validCoins.add(QUARTER);

        double actual = currencyService.countFunds(validCoins);

        assert actual == 0.5;
    }

    @Test
    public void countFunds_shouldReturnTotalValueOfThreeQuarters() {

        List<Coin> validCoins = new ArrayList<>();
        validCoins.add(QUARTER);
        validCoins.add(QUARTER);
        validCoins.add(QUARTER);

        double actual = currencyService.countFunds(validCoins);

        assert actual == 0.75;
    }

    @Test
    public void countFunds_shouldReturnTotalValueOfOneDollarAndOneQuarter() {

        List<Coin> validCoins = new ArrayList<>();
        validCoins.add(DOLLAR);
        validCoins.add(QUARTER);

        double actual = currencyService.countFunds(validCoins);

        assert actual == 1.25;
    }

    @Test
    public void countFunds_shouldReturnTotalValueOfOneDimeAndOneNickel() {

        List<Coin> validCoins = new ArrayList<>();
        validCoins.add(DIME);
        validCoins.add(NICKEL);

        double actual = currencyService.countFunds(validCoins);

        assert actual == 0.15;
    }

    @Test
    public void countFunds_shouldReturnTotalValueOfOneOfEachCoinType() {

        List<Coin> validCoins = new ArrayList<>();
        validCoins.add(DOLLAR);
        validCoins.add(QUARTER);
        validCoins.add(DIME);
        validCoins.add(NICKEL);

        double actual = currencyService.countFunds(validCoins);

        assert actual == 1.40;
    }

}
