package ru.stud.auc.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {

    private static final String TITLE = "Не найдено";

    public NotFoundException() {
        title = TITLE;
        message=title;
        status = HttpStatus.NOT_FOUND;
    }

    public NotFoundException(String message) {
        this();
        this.message = message;
    }
}
