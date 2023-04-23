package ru.stud.auc.product.auction;

import org.mapstruct.*;
import ru.stud.auc.flowdata.product.auction.AuctionEntity;
import ru.stud.auc.product.auction.model.AuctionDto;

@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE, unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AuctionsMapper {
    AuctionDto toSimpleDto(AuctionEntity auction);

}
