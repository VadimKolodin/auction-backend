package ru.stud.auc.user;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.stud.auc.annotation.mapstruct.IgnoreUpdateAuditFileds;
import ru.stud.auc.dto.UserDto;
import ru.stud.auc.dto.UserRegistrationDto;
import ru.stud.auc.flowdata.user.UserEntity;
import ru.stud.auc.flowdata.user.model.UserView;


@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE, unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UsersMapper {

    UserView toView(UserEntity u);

    UserDto toDto(UserEntity u);

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "isDeleted", ignore = true)
    @Mapping(target = "role", constant = "CLIENT")
    @Mapping(target = "credit", constant = "0")
    @Mapping(target = "image", ignore = true)
    @IgnoreUpdateAuditFileds
    UserEntity toEntity(UserRegistrationDto dto);

}
