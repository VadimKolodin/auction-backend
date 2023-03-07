package ru.stud.auc;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.stud.auc.auth.model.UserAuthPojo;
import ru.stud.auc.flowdata.user.UserEntity;

@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE, unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UsersAuthMapper {

    UserAuthPojo toPojo(UserEntity e);

}
