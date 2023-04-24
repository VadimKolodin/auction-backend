package ru.stud.auc.product.inventory;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.stud.auc.annotation.hasroles.HasRole;
import ru.stud.auc.product.model.SellerProductsDto;

import java.util.List;
import java.util.UUID;

import static ru.stud.auc.product.inventory.SellerInventoryApi.SELLER_INVENOTRY_API_PATH;

@RequestMapping(SELLER_INVENOTRY_API_PATH)
@Tag(name = "Методы для работы с ассортиментом продавца", description = SELLER_INVENOTRY_API_PATH)
//TODO:yбрать Seller
public interface SellerInventoryApi {
    String SELLER_INVENOTRY_API_PATH = "/api/seller-inventory";



    //TODO:сделать метод, просто get (без доп маппинга пути), а айдишник селлера получить из AuthenticationUtils
    @GetMapping
    @HasRole("SELLER")
    @Operation(summary = "Получение своих товаров")
    List<SellerProductsDto> getSellerInventory();
}
