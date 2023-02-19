package ru.stud.auc.product;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.stud.auc.flowdata.product.ProductEntity;
import ru.stud.auc.flowdata.product.model.ProductView;
import ru.stud.auc.product.model.ProductDto;
import ru.stud.auc.util.IgnoreUpdateAuditFileds;

@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE, unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductsMapper {

    ProductView toView(ProductEntity p);

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "isDeleted", ignore = true)
    @IgnoreUpdateAuditFileds
    ProductEntity toEntity(ProductDto dto);

}
