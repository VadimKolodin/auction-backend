package ru.stud.auc.common;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.stud.auc.common.model.SubtagDto;
import ru.stud.auc.common.model.TagDto;
import ru.stud.auc.flowdata.product.model.ProductAdminView;

import java.util.List;
import java.util.UUID;

import static ru.stud.auc.common.CommonsApi.COMMONS_API;


@RequestMapping(COMMONS_API)
@Tag(name = "Методы для получения общих вещей", description = COMMONS_API)
public interface CommonsApi {
    String COMMONS_API = "/api/commons";

    @SecurityRequirements
    @GetMapping("/tags")
    @Operation(summary = "Получение тегов")
    List<TagDto> getTags();

    @SecurityRequirements
    @GetMapping("/subtags")
    @Operation(summary = "Получение подтегов")
    List<SubtagDto> getSubtags();
}
