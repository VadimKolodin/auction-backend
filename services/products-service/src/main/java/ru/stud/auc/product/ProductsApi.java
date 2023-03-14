package ru.stud.auc.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.stud.auc.common.enums.SubTag;
import ru.stud.auc.flowdata.product.model.ProductView;


import java.util.UUID;

import java.util.List;
import java.util.Optional;

import static ru.stud.auc.product.ProductsApi.PRODUCTS_API_PATH;


/**
 * Тут методы для общего пользования,
 * !!!ни в коем случае!!!
 * не должно быть ничего кроме GET запросов без аутентификации
 */
@RequestMapping(PRODUCTS_API_PATH)
@Tag(name = "Методы для работы с товарами", description = PRODUCTS_API_PATH)
public interface ProductsApi {
    String PRODUCTS_API_PATH = "/api/product";

    @SecurityRequirements
    @GetMapping("/{productId}")
    @Operation(summary = "Получение товара")
    ProductView getProduct(@PathVariable UUID productId);


    @GetMapping
    @SecurityRequirements
    @Operation(summary = "Поиск товаров по параметрам")
    List<ProductView> searchProducts(
            @RequestParam(name = "maxResult", required = true)   int maxResult,
            @RequestParam(name = "offset", required = true) int offset,
            @RequestParam(name = "nameSearchString") Optional<String> nameSearchString,
            @RequestParam(name = "nameAsc",  defaultValue = "true")   Optional<Boolean> nameAsc,
            @RequestParam(name = "costAsc",  defaultValue = "false")  Optional<Boolean> costAsc,
            @RequestParam(name = "tags", required = false)   List<ru.stud.auc.common.enums.Tag> tags,
            @RequestParam(name = "subTags", required = false)  List<SubTag> subTags
    );
}
