package services;

import models.Coin;
import org.junit.Before;
import org.junit.Test;

import static models.Coin.DOLLAR;
import static models.Coin.NICKEL;
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
}
