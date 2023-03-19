package ru.stud.auc.product.cart.model;

import lombok.Data;
import ru.stud.auc.flowdata.product.cart.model.CartProductView;

import java.util.List;
import java.util.UUID;

@Data
public class CartRequestDto {

   private UUID userId;

   private int totalPrice;

   private List<CartProductView> products;

}
