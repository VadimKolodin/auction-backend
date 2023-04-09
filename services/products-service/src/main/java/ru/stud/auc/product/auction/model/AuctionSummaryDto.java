package ru.stud.auc.product.auction.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AuctionSummaryDto {

    private UUID id;

    private UUID sellerId;

    private UUID clientId;

    private UUID productId;

    private Integer startPrice;

    private Integer currentPrice;

    private Integer minCost;

    private String name;

    private byte[] image;

    private LocalDateTime endTime;


}
