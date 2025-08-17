package com.pdk.bynry.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.pdk.bynry.Dto.ProductRequest;
import com.pdk.bynry.entity.Inventory;
import com.pdk.bynry.entity.Product;

@Service
public class ProductService {

    // Use RAM to store products and inventories
    private final Map<Long, Product> products = new HashMap<>();
    private final Map<Long, Inventory> inventories = new HashMap<>();
    private final AtomicLong productIdCounter = new AtomicLong(1);

    public Long addProduct(ProductRequest productRequest) {
        // Add product to database
        return addToDatabase(productRequest);
    }

    public long addToDatabase(ProductRequest request) {
        long productId = productIdCounter.getAndIncrement();

        // Create product
        Product product = new Product(
                request.getName(),
                request.getSku(),
                request.getPrice(),
                request.getWarehouseId(),
                request.getInitialQuantity());

        products.put(productId, product);

        // Store product
        Inventory inventory = new Inventory(
                productId,
                request.getWarehouseId(),
                request.getInitialQuantity());

        inventories.put(productId, inventory);
        return productId;
    }
}
