package ru.stud.auc.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.stud.auc.flowdata.product.model.ProductAdminView;
import ru.stud.auc.flowdata.product.model.ProductView;
import ru.stud.auc.product.model.ProductDto;

import javax.validation.Valid;
import java.util.UUID;

import static ru.stud.auc.product.ProductsAdminApi.PRODUCTS_ADMIN_API_PATH;



@Validated
@RequestMapping(PRODUCTS_ADMIN_API_PATH)
@Tag(name = "Методы для работы с товарами для админа", description = PRODUCTS_ADMIN_API_PATH)
public interface ProductsAdminApi {
    String PRODUCTS_ADMIN_API_PATH = "/api/admin/product";

    @GetMapping("/{productId}")
    @Operation(summary = "Получение товара")
    ProductAdminView getProduct(@PathVariable UUID productId);

    @PostMapping
    @Operation(summary = "Создание товара")
    ProductView createProduct(@Valid @RequestBody ProductDto request);

    @DeleteMapping("/{productId}")
    @Operation(summary = "Удаление товара")
    void deleteProduct(@PathVariable UUID productId);

    @PatchMapping("/{productId}")
    @Operation(summary = "Восстановление товара из удаленных")
    void restoreProduct(@PathVariable UUID productId);


}
