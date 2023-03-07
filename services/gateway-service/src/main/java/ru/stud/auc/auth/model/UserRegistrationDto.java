package ru.stud.auc.auth.model;

import lombok.Builder;
import lombok.Data;

@Data
public class UserRegistrationDto {
    private String email;
    private String login;
    private String password;
}
