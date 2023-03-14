package ru.stud.auc.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.stud.auc.flowdata.product.model.ProductView;


import java.util.UUID;

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

    @RequestMapping(value = "{productId}" , method = RequestMethod.GET,
            produces = MediaType.IMAGE_PNG_VALUE)
    @Operation(summary = "Получение картинки товара")
    ResponseEntity<byte[]> getImageProduct(@PathVariable UUID productId);


}
