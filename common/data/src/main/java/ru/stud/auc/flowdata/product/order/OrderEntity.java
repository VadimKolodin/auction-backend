package ru.stud.auc.flowdata.product.order;

import ru.stud.auc.common.SoftDeleteEntity;
import ru.stud.auc.common.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "products_orders")
public class OrderEntity extends SoftDeleteEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name="auction_id")
    private UUID auctionId;

    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "client_id")
    private UUID clientId;

    @Column(name = "seller_id")
    private UUID sellerId;

    @Column(name = "price")
    private Integer price;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}
