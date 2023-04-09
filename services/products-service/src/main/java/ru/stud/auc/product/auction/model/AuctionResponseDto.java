package ru.stud.auc.product.auction.model;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class AuctionResponseDto {

    private UUID id;

    private UUID clientId;

    private UUID sellerId;

    private UUID productId;

    private Integer startPrice;

    private Integer currentPrice;

    private Integer minCost;

    private String name;

    private byte[] image;

    private LocalDateTime endTime;

    List<AuctionHistoryDto> historyDtoList;

}
