package ru.stud.auc.exception;

import org.springframework.http.HttpStatus;

public class InternalException extends ApiException {

    private static final String TITLE = "Ошибка сервера";

    public InternalException() {
        title = TITLE;
        message = title;
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public InternalException(String message) {
        this();
        this.message = message;
    }
}