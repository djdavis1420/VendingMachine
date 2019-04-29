package models;

import java.util.Arrays;
import java.util.List;

public class Coin {

    public double weight;
    public double diameter;
    public double value;

    public Coin(double weight, double diameter, double value) {
        this.weight = weight;
        this.diameter = diameter;
        this.value = value;
    }

    public static Coin DOLLAR = new Coin(8.1, 26.5, 1.00);
    public static Coin QUARTER = new Coin(5.67, 24.26, 0.25);
    public static Coin DIME = new Coin(2.268, 17.91, 0.10);
    public static Coin NICKEL = new Coin(5.00, 21.21, 0.05);

    public static List<Coin> validCoins = Arrays.asList(DOLLAR, QUARTER, DIME, NICKEL);

}