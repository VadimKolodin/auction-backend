package ru.stud.auc.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UserRegistrationDto {
    private String email;
    private String login;
    private String password;
}
