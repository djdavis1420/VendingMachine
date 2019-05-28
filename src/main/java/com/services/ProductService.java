package com.services;

import com.database.ProductsDatabase;
import com.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {

    private ProductsDatabase database;
    public Product selectedProduct;

    @Autowired
    public ProductService(ProductsDatabase database) {
        this.database = database;
    }

    public boolean isProductAvailable(String productLocation) {
        List<Product> productsAtLocation = database.selectProductByLocation(productLocation);
        selectedProduct = productsAtLocation.stream().findFirst().orElse(null);
        return productsAtLocation.stream().findAny().isPresent();
    }

    public double getProductCost(String productLocation) {
        return (selectedProduct != null)
                ? selectedProduct.getCost()
                : database.selectProductByLocation(productLocation).stream().findFirst().get().getCost();
    }

    public boolean hasSufficientFunds(double productCost, double insertedFunds) {
        return insertedFunds >= productCost;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }
}
