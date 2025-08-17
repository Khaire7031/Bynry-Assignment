package com.pdk.bynry.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pdk.bynry.Dto.ProductRequest;
import com.pdk.bynry.Dto.Response;
import com.pdk.bynry.entity.Product;
import com.pdk.bynry.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    // POST: create new product
    @PostMapping("/api/products")
    public ResponseEntity<Response> addProduct(@RequestBody @Valid ProductRequest productRequest) {
        String skuId = productService.addProduct(productRequest);
        Response response = new Response("Product added successfully", skuId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/api/getproducts")
    public Map<String, Product> getMethodName() {
        return productService.getProducts();
    }

}
