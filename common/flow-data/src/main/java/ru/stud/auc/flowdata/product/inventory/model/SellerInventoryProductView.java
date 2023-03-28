package ru.stud.auc.flowdata.product.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class SellerInventoryProductView {
    //TODO:add sellerId

    private UUID productId;

    //TODO:add description

    private String name;

    private int amount;

    private int price;

    private byte[] image;


}
