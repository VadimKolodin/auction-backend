package ru.stud.auc.product.order.model;


import lombok.Data;
import ru.stud.auc.common.enums.OrderStatus;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.UUID;

@Data
public class OrderDto {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "client_id")
    private UUID clientId;

    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "seller_id")
    private UUID sellerId;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name="auction_id")
    private UUID auctionId;
}
