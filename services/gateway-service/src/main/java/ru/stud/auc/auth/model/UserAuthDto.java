package ru.stud.auc.auth.model;

import lombok.Data;

@Data
public class UserAuthDto {

    private String login;

    private String password;
}
