package ru.stud.auc.flowdata.product.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CartProductView {

    private UUID productId;

    private String name;

    private int amount;

    private int price;

    private byte[] image;
}
