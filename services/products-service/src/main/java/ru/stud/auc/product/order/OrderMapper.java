package ru.stud.auc.product.order;


import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.stud.auc.flowdata.product.order.OrderEntity;
import ru.stud.auc.product.order.model.OrderDto;
@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE, unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface OrderMapper {
    OrderDto toSimpleDto(OrderEntity order);
}
