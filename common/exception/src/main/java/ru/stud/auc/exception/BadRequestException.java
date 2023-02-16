package ru.stud.auc.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ApiException {

    private static final String TITLE = "Ошибка запроса";

    public BadRequestException() {
        title = TITLE;
        message = title;
        status = HttpStatus.BAD_REQUEST;
    }

    public BadRequestException(String message) {
        this();
        this.message = message;
    }
}
