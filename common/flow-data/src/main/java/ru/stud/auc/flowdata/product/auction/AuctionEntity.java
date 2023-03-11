package ru.stud.auc.flowdata.product.auction;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "auction")
public class AuctionEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "client_id")
    private UUID clientId;

    @Column(name = "seller_id")
    private UUID sellerId;

    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "price")
    private Integer price;

    @Column(name = "end_time")
    private LocalDateTime endTime;

}
