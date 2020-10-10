package com.fpineda.samples.choreographypattern.core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Product {

    private long id;
    private String name;
    private String description;
    private double price;
    private int inventoryQuantity;

    public boolean isInStockForQuantity(int quantity) {
        return this.inventoryQuantity >= quantity;
    }

}
