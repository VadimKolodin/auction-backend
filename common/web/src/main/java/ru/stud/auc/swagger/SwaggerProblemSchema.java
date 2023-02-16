package ru.stud.auc.swagger;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SwaggerProblemSchema {

    @Schema(description = "Код ответа", example = "500")
    private int status;

    @Schema(description = "Заголовок ошибки", example = "Произошла необрабатываемая ошибка во время запроса")
    private String title;

    @Schema(description = "Детальное описание ошибки", example = "Произошла необрабатываемая ошибка во время запроса")
    private String detail;
}
