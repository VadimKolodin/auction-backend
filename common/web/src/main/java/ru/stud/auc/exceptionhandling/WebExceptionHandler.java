package ru.stud.auc.exceptionhandling;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;

import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.ThrowableProblem;
import org.zalando.problem.spring.web.advice.validation.ConstraintViolationAdviceTrait;
import org.zalando.problem.spring.web.advice.validation.MethodArgumentNotValidAdviceTrait;
import ru.stud.auc.exception.ApiException;
import ru.stud.auc.exception.ForbiddenException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class WebExceptionHandler implements IExceptionHandler, ConstraintViolationAdviceTrait, MethodArgumentNotValidAdviceTrait {

    private final ObjectMapper objectMapper;

    @ExceptionHandler({InsufficientAuthenticationException.class})
    protected ResponseEntity<Problem> handleException(InsufficientAuthenticationException ex, NativeWebRequest request) {
        log.debug(ex.getMessage());
        String title = "Пользователь не авторизован";
        String message = "Для доступа к ресурсу необходима авторизация";
        return create(Status.UNAUTHORIZED, title, message, request);
    }

    @ExceptionHandler(ThrowableProblem.class)
    protected ResponseEntity<Problem> handleProblem(ThrowableProblem ex, NativeWebRequest request) {
        return create(ex, request);
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Problem> handleException(Throwable ex, NativeWebRequest request) {
        log.error("Ошибка при обработке запроса", ex);
        return create(Status.INTERNAL_SERVER_ERROR, ex, "Необрабатываемая ошибка", request);
    }

    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<Problem> handleException(ApiException ex, NativeWebRequest request) {
        log.warn(ex.getMessage(), ex);
        return create(ex, request);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<Problem> handleException(MissingServletRequestParameterException ex, NativeWebRequest request) {
        log.debug("Не указан обязательный параметр {}", ex.getParameterName());
        return create(Status.BAD_REQUEST,
                      "Не указан обязательный параметр",
                      String.format("Не указан обязательный параметр %s", ex.getParameterName()),
                      request);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    protected ResponseEntity<Problem> handleException(MissingRequestHeaderException ex, NativeWebRequest request) {
        log.debug("Не указан обязательный заголовок {}", ex.getHeaderName());
        return create(Status.BAD_REQUEST,
                      "Не указан обязательный заголовок",
                      String.format("Не указан обязательный заголовок %s", ex.getHeaderName()),
                      request);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Problem> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, NativeWebRequest request) {
        Class<?> type = e.getRequiredType();
        String message = "Указано некорректное значение параметра " + e.getName();
        if (type.isEnum()) {
            message += ". Должно быть одно из: " + StringUtils.join(type.getEnumConstants(), ", ");
        }
        return create(Status.BAD_REQUEST, "Некорректное значение параметра", message, request);
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<Problem> handleMethodArgumentTypeMismatchException(MissingServletRequestPartException e, NativeWebRequest request) {
        log.debug("Не указан обязательный параметр {}", e.getRequestPartName());
        return create(Status.BAD_REQUEST,
                      "Не указан обязательный параметр",
                      String.format("Не указан обязательный параметр %s", e.getRequestPartName()),
                      request);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Problem> handleException(HttpMessageNotReadableException e, NativeWebRequest request) {
        String message = e.getMessage();
        if (e.getCause() instanceof InvalidFormatException ife) {
            message = "Некорректное значение %s".formatted(ife.getValue());
            if (ife.getTargetType().equals(UUID.class)) {
                message += ". Формат значения должен быть UUID";
            }
        }
        return create(Status.BAD_REQUEST, "Ошибка при чтении запроса", message, request);
    }


    @ExceptionHandler({AccessDeniedException.class})
    protected ResponseEntity<Problem> handleException(AccessDeniedException ex, NativeWebRequest request) {
        log.warn(ex.getMessage(), ex);
        if (ex.getMessage() == null || ex.getMessage().contains("Access") || ex.getMessage().equals("Доступ запрещен")) {
            return create(Status.FORBIDDEN, "Доступ запрещен", "Недостаточно прав", request);
        }
        return create(Status.FORBIDDEN, ex, "Доступ запрещен", request);
    }

    @ExceptionHandler({ExpiredJwtException.class})
    protected ResponseEntity<Problem> handleException(ExpiredJwtException ex, NativeWebRequest request) {
        return create(Status.UNAUTHORIZED, ex, "Токен устарел", request);
    }

    @ExceptionHandler({ForbiddenException.class})
    protected ResponseEntity<Problem> handleException(ForbiddenException ex, NativeWebRequest request) {
        log.warn(ex.getMessage(), ex);
        return create(Status.FORBIDDEN, ex, "Доступ запрещен", request);
    }
}
