package ru.stud.auc.product.auction.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AuctionHistoryDto {

    private UUID id;

    private UUID auctionId;

    private UUID sellerId;

    private Integer price;

    private LocalDateTime requestDate;

}
