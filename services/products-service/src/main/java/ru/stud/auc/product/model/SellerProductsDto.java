package ru.stud.auc.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerProductsDto {
    private UUID productId;

    private String description;

    private String name;

    private int amount;

    private int price;

    private byte[] image;
}
