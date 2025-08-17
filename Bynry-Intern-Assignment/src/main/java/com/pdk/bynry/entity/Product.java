package com.pdk.bynry.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String name;
    private String sku;
    private double price;
    private Long warehouseId;
    private int initialQuantity;
}
