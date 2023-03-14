package ru.stud.auc.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.stud.auc.auth.model.UserAuthDto;
import ru.stud.auc.dto.UserRegistrationDto;
import ru.stud.auc.auth.token.model.AuthenticationDto;
import ru.stud.auc.auth.token.model.RefreshTokenDto;
import ru.stud.auc.auth.token.model.TokenDto;

import javax.validation.Valid;

import static ru.stud.auc.auth.AuthenticationApi.AUTH_API_PATH;

@Validated
@RequestMapping(AUTH_API_PATH)
@Tag(name = "Методы для работы с токенами", description = AUTH_API_PATH)
public interface AuthenticationApi {
    String AUTH_API_PATH = "/api/auth";

    @PostMapping("/register")
    @SecurityRequirements // аннотация сваггера, тк пустая, указывает что как раз никакие SecurityRequirements не нужны
    @Operation(summary = "Регистрация пользователя")
    ResponseEntity<String> register(@Valid  @RequestBody UserRegistrationDto request);

    @PostMapping("/token")
    @SecurityRequirements // аннотация сваггера, тк пустая, указывает что как раз никакие SecurityRequirements не нужны
    @Operation(summary = "Получение токенов")
    ResponseEntity<AuthenticationDto> authenticate(@Valid @RequestBody UserAuthDto request);

    @PostMapping("/refresh")
    @SecurityRequirements // аннотация сваггера, тк пустая, указывает что как раз никакие SecurityRequirements не нужны
    @Operation(summary = "Обновление токена")
    ResponseEntity<TokenDto> authenticate(@Valid @Parameter(description = "refresh-токен") @RequestBody RefreshTokenDto refreshToken);

}
