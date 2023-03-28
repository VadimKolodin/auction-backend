package ru.stud.auc.product.inventory;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.stud.auc.flowdata.product.inventory.model.SellerInventoryProductView;
import ru.stud.auc.product.model.SellerProductsDto;

@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE, unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface InventoryMapper {

    SellerProductsDto toInventoryDto(SellerInventoryProductView view);

}
