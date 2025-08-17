package com.pdk.bynry.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.pdk.bynry.Dto.ProductRequest;
import com.pdk.bynry.Exception.ApiException;
import com.pdk.bynry.entity.Inventory;
import com.pdk.bynry.entity.Product;

@Service
public class ProductService {

    // Use RAM to store products and inventories
    private final Map<String, Product> products = new ConcurrentHashMap<>();
    private final Map<Long, Inventory> inventories = new ConcurrentHashMap<>();

    private final AtomicLong productIdCounter = new AtomicLong(1);

    public String addProduct(ProductRequest productRequest) {
        if (products.containsKey(productRequest.getSku())) {
            throw new ApiException("Product with SKU " + productRequest.getSku() + " already exists.",
                    HttpStatus.BAD_REQUEST);
        }
        // Add in products
        Product product = productRequestToProduct(productRequest);

        // Add in inventory
        Inventory inventory = new Inventory(product.getId(), product.getWarehouseId(), product.getInitialQuantity());

        // Add inventory and product to the maps
        products.put(productRequest.getSku(), product);
        inventories.put(product.getId(), inventory);

        return product.getSku();
    }

    public Map<String, Product> getProducts() {
        return products;
    }

    private Product productRequestToProduct(ProductRequest productRequest) {
        long productId = productIdCounter.getAndIncrement();
        return new Product(
                productId,
                productRequest.getName(),
                productRequest.getSku(),
                productRequest.getPrice(),
                productRequest.getWarehouseId(),
                productRequest.getInitialQuantity());
    }
}
