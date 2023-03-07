package ru.stud.auc.auth;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stud.auc.auth.model.UserAuthDto;
import ru.stud.auc.auth.model.UserRegistrationDto;
import ru.stud.auc.auth.token.model.AuthenticationDto;
import ru.stud.auc.auth.token.model.RefreshTokenDto;
import ru.stud.auc.auth.token.model.TokenDto;

@RestController
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationApi {

    private final AuthenticationService service;

    @Override
    public ResponseEntity<String> register(@RequestBody UserRegistrationDto request) {
        service.register(request);
        return ResponseEntity.ok("Регистрация прошла успешно");
    }

    @Override
    public ResponseEntity<AuthenticationDto> authenticate(@RequestBody UserAuthDto request) {
        return ResponseEntity.ok(service.generateNewTokens(request));
    }

    @Override
    public ResponseEntity<TokenDto> authenticate(@RequestBody RefreshTokenDto refreshToken) {
        return ResponseEntity.ok(service.generateNewToken(refreshToken.getRefreshToken()));
    }

}
