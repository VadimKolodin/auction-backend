package ru.stud.auc.exceptionhandling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.ThrowableProblem;
import org.zalando.problem.spring.web.advice.AdviceTrait;
import ru.stud.auc.exception.ApiException;

public interface IExceptionHandler extends AdviceTrait {

    default ResponseEntity<Problem> create(ApiException ex, NativeWebRequest req) {
        ThrowableProblem problem = Problem.builder()
                                          .withStatus(Status.valueOf(ex.getStatus().value()))
                                          .withTitle(ex.getTitle())
                                          .withDetail(ex.getMessage())
                                          .build();
        return create(problem, req);
    }

    default ResponseEntity<Problem> create(Status status, Throwable t, String title, NativeWebRequest req) {
        return create(status, t, title, t.getMessage(), req);
    }

    default ResponseEntity<Problem> create(Status status, Throwable t, String title, String detail,
                                           NativeWebRequest req) {
        String title2 = switch (status) {

            case BAD_REQUEST -> "Ошибка запроса";
            case FORBIDDEN -> "Доступ запрещен";
            default -> {
                if (status.getStatusCode() >=500) {
                    yield "Необрабатываемая ошибка";
                } else {
                    yield title;
                }
            }
        };
        ThrowableProblem problem = Problem.builder()
                                          .withStatus(status)
                                          .withTitle(title2)
                                          .withDetail(detail)
                                          .withCause(createCause(t.getCause()))
                                          .build();
        return create(problem, req);
    }

    default ResponseEntity<Problem> create(Status status, String title, String detail, NativeWebRequest req) {
        ThrowableProblem problem = Problem.builder()
                                          .withStatus(status)
                                          .withTitle(title)
                                          .withDetail(detail)
                                          .build();
        return create(problem, req);
    }

    default ThrowableProblem createCause(Throwable throwable) {
        if (throwable == null) {
            return null;
        }
        return Problem.builder()
                      .withDetail(throwable.getMessage())
                      .withCause(createCause(throwable.getCause()))
                      .build();
    }
}
