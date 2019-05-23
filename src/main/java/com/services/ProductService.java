package com.services;

import com.database.ProductDatabase;
import com.models.Product;

import java.util.List;

public class ProductService {

    private ProductDatabase database;
    public Product selectedProduct;

    public ProductService(ProductDatabase database) {
        this.database = database;
    }

    public boolean isProductAvailable(String productLocation) {
        List<Product> productsAtLocation = database.getProductByLocation(productLocation);
        selectedProduct = productsAtLocation.stream().findFirst().orElse(null);
        return productsAtLocation.stream().findAny().isPresent();
    }

    public double getProductCost() {
        return selectedProduct.getCost();
    }

    public boolean hasSufficientFunds(double productCost, double insertedFunds) {
        return insertedFunds >= productCost;
    }
}
