package com.pdk.bynry.Dto;

import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank(message = "Product name is required")
    private String name;

    @NotBlank(message = "SKU is required")
    private String sku;

    @Min(value = 1, message = "Price must be greater than 0")
    private double price;

    @NotNull(message = "Warehouse ID is required")
    private Long warehouseId;

    @Min(value = 0, message = "Initial quantity cannot be negative")
    private int initialQuantity;
}
