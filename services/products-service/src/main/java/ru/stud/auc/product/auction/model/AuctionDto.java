package ru.stud.auc.product.auction.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.UUID;

public class AuctionDto {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "client_id")
    private UUID clientId;

    @Column(name = "product_id")
    private UUID productId;

}
