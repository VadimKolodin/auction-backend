package ru.stud.auc.product.sellerInventory;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.stud.auc.product.model.SellerProductsDto;

import java.util.List;
import java.util.UUID;

import static ru.stud.auc.product.sellerInventory.SellerInventoryApi.SELLER_INVENOTRY_API_PATH;

@RequestMapping(SELLER_INVENOTRY_API_PATH)
@Tag(name = "Методы для работы с ассортиментом продавца", description = SELLER_INVENOTRY_API_PATH)
public interface SellerInventoryApi {
    String SELLER_INVENOTRY_API_PATH = "/api/seller-inventory";

    @GetMapping("/{sellerId}")
    @Operation(summary = "Получение товаров продавца")
    SellerProductsDto getSellerInventory(@PathVariable UUID sellerId);

}
