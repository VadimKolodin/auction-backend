package ru.stud.auc.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.stud.auc.flowdata.product.model.ProductView;
import ru.stud.auc.product.model.ProductDto;

import javax.validation.Valid;

import static ru.stud.auc.product.ProductsApi.PRODUCTS_API_PATH;

//TODO: методами может пользоваться только админ
@Validated
@RequestMapping(PRODUCTS_API_PATH)
@Tag(name = "Методы для работы с товарами", description = PRODUCTS_API_PATH)
public interface ProductsApi {
    String PRODUCTS_API_PATH = "/api/product";

    @PostMapping
    @Operation(summary = "Создание товара")
    ProductView create(@Valid @RequestBody ProductDto request);

}
