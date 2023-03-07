package ru.stud.auc.auth.token.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AuthenticationDto {
    private String token;
    private String refreshToken;
    private LocalDateTime expirationDateTime;
    private LocalDateTime refreshExpirationDateTime;

}
