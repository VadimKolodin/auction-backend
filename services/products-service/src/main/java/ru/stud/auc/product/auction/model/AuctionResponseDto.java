package ru.stud.auc.product.auction.model;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class AuctionResponseDto extends AuctionSummaryDto {

    List<AuctionHistoryDto> history;

}
