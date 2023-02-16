package ru.stud.auc.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI();
    }

    @Bean
    public OpenApiCustomiser openApiCustomiser() {
        return openApi -> openApi.getPaths().values().forEach(item -> item.readOperations().forEach(o -> {
            ApiResponses responses = o.getResponses();
            responses.addApiResponse("200", success());
            responses.addApiResponse("400", badRequest());
            responses.addApiResponse("401", unauthorized());
            responses.addApiResponse("403", forbidden());
            responses.addApiResponse("404", notFound());
            responses.addApiResponse("500", internalError());
        }));
    }

    private ApiResponse success() {
        ApiResponse response = new ApiResponse();
        response.setDescription("Запрос выполнен успешно");
        return response;
    }

    private ApiResponse badRequest() {
        ApiResponse response = new ApiResponse().description("Некорректный запрос. Не указаны обязательные параметры/указаны некорректные значения параметров");
        MediaType mediaType = new MediaType().schema(new Schema<SwaggerProblemSchema>());
        response.content(new Content().addMediaType("*/*", mediaType));
        return response;
    }

    private ApiResponse unauthorized() {
        ApiResponse response = new ApiResponse().description("Пользователь не авторизован");
        MediaType mediaType = new MediaType().schema(new Schema<SwaggerProblemSchema>());
        response.content(new Content().addMediaType("*/*", mediaType));
        return response;
    }

    private ApiResponse notFound() {
        ApiResponse response = new ApiResponse().description("Не найдено");
        MediaType mediaType = new MediaType().schema(new Schema<SwaggerProblemSchema>());
        response.content(new Content().addMediaType("*/*", mediaType));
        return response;
    }

    private ApiResponse forbidden() {
        ApiResponse response = new ApiResponse().description("Доступ к ресурсу запрещен");
        SwaggerProblemSchema s = new SwaggerProblemSchema();
        s.setTitle("Недостаточно прав");
        s.setStatus(403);
        s.setDetail("Недостаточно прав");
        MediaType mediaType = new MediaType().schema(new Schema<SwaggerProblemSchema>().example(s));
        response.content(new Content().addMediaType("*/*", mediaType));
        return response;
    }

    private ApiResponse internalError() {
        ApiResponse response = new ApiResponse().description("Ошибка сервиса при обработке запроса");
        SwaggerProblemSchema s = new SwaggerProblemSchema();
        s.setTitle("Ошибка при обработке запроса");
        s.setStatus(500);
        s.setDetail("Детальное описание ошибки");
        MediaType mediaType = new MediaType().schema(new Schema<SwaggerProblemSchema>().example(s));
        response.content(new Content().addMediaType("*/*", mediaType));
        return response;
    }
}
