package ru.stud.auc.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stud.auc.auth.model.UserAuthDto;
import ru.stud.auc.auth.model.UserRegistrationDto;
import ru.stud.auc.auth.token.model.TokenDto;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegistrationDto request) {
        service.register(request);
        return ResponseEntity.ok("Регистрация прошла успешно");
    }

    @PostMapping("/token")
    public ResponseEntity<TokenDto> authenticate(@RequestBody UserAuthDto request) {
        return ResponseEntity.ok(service.generateNewToken(request));
    }

}
