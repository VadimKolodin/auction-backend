package ru.stud.auc.users;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.stud.auc.auth.model.UserAuthPojo;
import ru.stud.auc.auth.token.model.AuthenticationDto;
import ru.stud.auc.auth.token.model.TokenDto;


@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE, unmappedSourcePolicy = ReportingPolicy.WARN, unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UsersAuthMapper {

    UserAuthPojo toPojo(UserAuthEntity e);

    @Mapping(target = "token", source = "token.token")
    @Mapping(target = "expirationDateTime", source = "token.expirationDateTime")
    @Mapping(target = "refreshToken", source = "refreshToken.token")
    @Mapping(target = "refreshExpirationDateTime", source = "refreshToken.expirationDateTime")
    AuthenticationDto toDto(TokenDto token, TokenDto refreshToken);

}
