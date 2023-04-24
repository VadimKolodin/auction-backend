package ru.stud.auc.product.auction;

import org.mapstruct.*;
import ru.stud.auc.flowdata.product.auction.AuctionEntity;
import ru.stud.auc.flowdata.product.auction.model.AuctionHistoryView;
import ru.stud.auc.flowdata.product.auction.model.AuctionProductView;
import ru.stud.auc.product.auction.model.AuctionDto;
import ru.stud.auc.product.auction.model.AuctionHistoryDto;
import ru.stud.auc.product.auction.model.AuctionResponseDto;
import ru.stud.auc.product.auction.model.AuctionSummaryDto;

import java.util.List;

@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE, unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AuctionsMapper {
    AuctionDto toSimpleDto(AuctionEntity auction);

    AuctionSummaryDto toSummaryDto(AuctionProductView view);

    AuctionHistoryDto toHistoryDto(AuctionHistoryView view);

    AuctionResponseDto toDto(AuctionProductView view, List<AuctionHistoryDto> history);

}
