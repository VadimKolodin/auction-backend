package ru.stud.auc.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ApiException {

    private static final String TITLE = "Не авторизован";

    public UnauthorizedException() {
        title = TITLE;
        message = title;
        status = HttpStatus.UNAUTHORIZED;
    }

    public UnauthorizedException(String message) {
        this();
        this.message = message;
    }
}
