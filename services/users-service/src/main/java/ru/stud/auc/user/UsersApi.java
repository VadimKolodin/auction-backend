package ru.stud.auc.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.stud.auc.dto.UserRegistrationDto;
import ru.stud.auc.flowdata.user.model.UserView;

import javax.validation.Valid;
import java.util.UUID;

@Validated
@RequestMapping(UsersApi.USERS_API_PATH)
@Tag(name = "Методы для работы с пользователями", description = UsersApi.USERS_API_PATH)
public interface UsersApi {
    String USERS_API_PATH = "/api/user";

    @PostMapping("/register")
    @Operation(summary = "Внутренний метод для регистрации пользователя")
    UserView create(@Valid @RequestBody UserRegistrationDto request);



}
