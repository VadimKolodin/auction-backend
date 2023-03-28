package ru.stud.auc.user.internal;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.stud.auc.common.enums.ClientRole;
import ru.stud.auc.dto.UserDto;

import java.util.UUID;

@Validated
@RequestMapping(UsersInternalApi.USERS_INTERNAL_API_PATH)
@Tag(name = "Внутренние методы для работы с пользователями", description = UsersInternalApi.USERS_INTERNAL_API_PATH)
public interface UsersInternalApi {

    String USERS_INTERNAL_API_PATH = "/api/internal/user";

    @GetMapping("/{userId}")
    UserDto getUser(@PathVariable UUID userId);

    @GetMapping("/{userId}/role")
    ClientRole getUserRole(@PathVariable UUID userId);

}
