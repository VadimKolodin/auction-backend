package ru.stud.auc.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends ApiException {

    private static final String TITLE = "Доступ запрещен";

    public ForbiddenException() {
        title = TITLE;
        message = title;
        status = HttpStatus.FORBIDDEN;
    }

    public ForbiddenException(String message) {
        this();
        this.message=message;
    }
}
