package services;

import database.ProductDatabase;

public class ProductService {

    private ProductDatabase database;

    public ProductService(ProductDatabase database) {
        this.database = database;
    }

    public boolean isProductAvailable(String productLocation) {
        return !database.getProductByLocation(productLocation).isEmpty();
    }
}
