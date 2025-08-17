package com.pdk.bynry.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Inventory {
    private Long productId;
    private Long warehouseId;
    private int quantity;
}