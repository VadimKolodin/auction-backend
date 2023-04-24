package ru.stud.auc.user.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.stud.auc.product.model.SellerProductsDto;

import java.util.List;
import java.util.UUID;

@Validated
@RequestMapping(UsersAdminApi.USERS_ADMIN_API_PATH)
@Tag(name = "Методы для работы с ассортиментом продавца для админа", description = UsersAdminApi.USERS_ADMIN_API_PATH)
public interface InventoryAdminApi {
    @GetMapping("/{sellerId}")
    @Operation(summary = "Получение товаров продавца")
    List<SellerProductsDto> getSellerInventory(@PathVariable UUID sellerId);
}
