package ru.stud.auc.flowdata.product.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.stud.auc.common.enums.OrderStatus;

import java.util.UUID;

@Data
@AllArgsConstructor
public class OrderView {
    private UUID id;

    private UUID sellerId;

    private UUID clientId;

    private UUID productId;

    private Integer price;

    private OrderStatus status;

    private String name;

    private byte[] image;
}
