package ru.stud.auc.flowdata.product.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class InventoryProductView {
    private UUID productId;

    private String description;

    private String name;

    private int amount;

    private int price;

    private byte[] image;


}
