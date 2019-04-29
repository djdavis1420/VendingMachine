package models;

public class Product {

    public String name;
    public String location;
    public String type;
    public double cost;

    public Product() {}

    public Product(String name, String location, String type, double cost) {
        this.name = name;
        this.location = location;
        this.type = type;
        this.cost = cost;
    }

}
