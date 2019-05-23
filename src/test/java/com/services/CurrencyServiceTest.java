package com.services;

import com.models.Coin;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.models.Coin.*;
import static org.junit.Assert.*;

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
        List<Coin> validCoins = Arrays.asList(QUARTER);

        double expected = 0.25;
        double actual = currencyService.countFunds(validCoins);

        assertEquals(expected, actual, .0001);
    }

    @Test
    public void countFunds_shouldReturnTotalValueOfTwoQuarters() {
        List<Coin> validCoins = Arrays.asList(QUARTER, QUARTER);

        double expected = 0.5;
        double actual = currencyService.countFunds(validCoins);

        assertEquals(expected, actual, .0001);
    }

    @Test
    public void countFunds_shouldReturnTotalValueOfThreeQuarters() {
        List<Coin> validCoins = Arrays.asList(QUARTER, QUARTER, QUARTER);

        double expected = 0.75;
        double actual = currencyService.countFunds(validCoins);

        assertEquals(expected, actual, .0001);
    }

    @Test
    public void countFunds_shouldReturnTotalValueOfOneDollarAndOneQuarter() {
        List<Coin> validCoins = Arrays.asList(DOLLAR, QUARTER);

        double expected = 1.25;
        double actual = currencyService.countFunds(validCoins);

        assertEquals(expected, actual, .0001);
    }

    @Test
    public void countFunds_shouldReturnTotalValueOfOneDimeAndOneNickel() {
        List<Coin> validCoins = Arrays.asList(DIME, NICKEL);

        double expected = 0.15;
        double actual = currencyService.countFunds(validCoins);

        assertEquals(expected, actual, .0001);
    }

    @Test
    public void countFunds_shouldReturnTotalValueOfOneOfEachCoinType() {
        List<Coin> validCoins = Arrays.asList(DOLLAR, QUARTER, DIME, NICKEL);

        double expected = 1.40;
        double actual = currencyService.countFunds(validCoins);

        assertEquals(expected, actual, .0001);
    }

    @Test
    public void returnCorrectChange_shouldReturnEmptyList() {
        double insertedFunds = 1.50;
        double productCost = 1.50;

        List<Coin> expected = Collections.emptyList();
        List<Coin> actual = currencyService.returnCorrectChange(insertedFunds, productCost);

        assertEquals(expected, actual);
    }

    @Test
    public void returnCorrectChange_shouldReturnOneQuarter() {
        double insertedFunds = 1.50;
        double productCost = 1.25;

        List<Coin> expected = Arrays.asList(QUARTER);
        List<Coin> actual = currencyService.returnCorrectChange(insertedFunds, productCost);

        assertEquals(expected, actual);
    }

    @Test
    public void returnCorrectChange_shouldReturnTwoQuarters() {
        double insertedFunds = 1.50;
        double productCost = 1.00;

        List<Coin> expected = Arrays.asList(QUARTER, QUARTER);
        List<Coin> actual = currencyService.returnCorrectChange(insertedFunds, productCost);

        assertEquals(expected, actual);
    }

    @Test
    public void returnCorrectChange_shouldReturnThreeQuarters() {
        double insertedFunds = 1.50;
        double productCost = 0.75;

        List<Coin> expected = Arrays.asList(QUARTER, QUARTER, QUARTER);
        List<Coin> actual = currencyService.returnCorrectChange(insertedFunds, productCost);

        assertEquals(expected, actual);
    }

    @Test
    public void returnCorrectChange_shouldReturnOneDollarAndOneQuarter() {
        double insertedFunds = 1.50;
        double productCost = 0.25;

        List<Coin> expected = Arrays.asList(DOLLAR, QUARTER);
        List<Coin> actual = currencyService.returnCorrectChange(insertedFunds, productCost);

        assertEquals(expected, actual);
    }

    @Test
    public void returnCorrectChange_shouldReturnOneDimeAndOneNickel() {
        double insertedFunds = 1.50;
        double productCost = 1.35;

        List<Coin> expected = Arrays.asList(DIME, NICKEL);
        List<Coin> actual = currencyService.returnCorrectChange(insertedFunds, productCost);

        assertEquals(expected, actual);
    }

    @Test
    public void returnCorrectChange_shouldReturnOneOfEachCoinType() {
        double insertedFunds = 1.50;
        double productCost = 0.10;

        List<Coin> expected = Arrays.asList(DOLLAR, QUARTER, DIME, NICKEL);
        List<Coin> actual = currencyService.returnCorrectChange(insertedFunds, productCost);

        assertEquals(expected, actual);
    }

}
