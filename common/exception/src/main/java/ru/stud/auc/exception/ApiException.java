package ru.stud.auc.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class ApiException extends RuntimeException {

    protected HttpStatus status;

    protected String title;

    protected String message;

    public ApiException() {
    }

    public ApiException(String message) {
        super();
        this.message=message;
    }

}
