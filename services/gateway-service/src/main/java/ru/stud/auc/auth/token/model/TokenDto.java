package ru.stud.auc.auth.token.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TokenDto {
    private String token;
    private LocalDateTime expirationDateTime;
}
