package com.pdk.bynry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pdk.bynry.Dto.ProductRequest;
import com.pdk.bynry.Dto.Response;
import com.pdk.bynry.service.ProductService;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    // POST: create new product
    @PostMapping("/api/products")
    public Response createProduct(@RequestBody ProductRequest productRequest) {
        long productId = productService.addProduct(productRequest);
        Response response = new Response("Product created successfully", productId);
        return response;
    }
}
