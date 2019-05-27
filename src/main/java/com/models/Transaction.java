package com.models;

import java.util.List;

public class Transaction {

    private Product product;
    private List<Coin> change;
    private String message;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Coin> getChange() {
        return change;
    }

    public void setChange(List<Coin> change) {
        this.change = change;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
