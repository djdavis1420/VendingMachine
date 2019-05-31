package com.controllers;

import com.models.ProductRequest;
import com.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.web.bind.annotation.RestController
public class RESTController {

    private VendingMachineController vendingMachineController;

    @Autowired
    public RESTController(VendingMachineController vendingMachineController) {
        this.vendingMachineController = vendingMachineController;
    }

    @PostMapping("/purchase")
    public Transaction purchase(@RequestBody ProductRequest productRequest) {
        return vendingMachineController.processTransaction(productRequest.getProductLocation(), productRequest.getInsertedCoins());
    }
}
