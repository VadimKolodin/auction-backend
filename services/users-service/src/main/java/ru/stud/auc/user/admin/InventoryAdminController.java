package ru.stud.auc.user.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.stud.auc.product.inventory.SellerInventoryService;
import ru.stud.auc.product.model.SellerProductsDto;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class InventoryAdminController  implements InventoryAdminApi{
    private final SellerInventoryService sellerInventoryService;

    @Override
    public List<SellerProductsDto> getSellerInventory(UUID sellerId) {
        return sellerInventoryService.getSellerInventory(sellerId);
    }
}
