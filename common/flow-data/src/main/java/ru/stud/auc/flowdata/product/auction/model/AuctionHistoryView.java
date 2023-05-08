package ru.stud.auc.flowdata.product.auction.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class AuctionHistoryView {

    private UUID id;

    private UUID auctionId;

    private UUID sellerId;

    private Integer price;

    private LocalDateTime requestDate;

}
