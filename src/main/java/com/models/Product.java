package com.models;

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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
