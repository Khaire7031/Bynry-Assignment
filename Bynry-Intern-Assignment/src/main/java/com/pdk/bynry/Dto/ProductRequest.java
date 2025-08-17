package com.pdk.bynry.Dto;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String sku;
    private double price;
    private Long warehouseId;
    private int initialQuantity;
}
