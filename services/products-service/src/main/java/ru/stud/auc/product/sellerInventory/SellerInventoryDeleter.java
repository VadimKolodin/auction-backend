package ru.stud.auc.product.sellerInventory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.flowdata.product.inventory.InventoryRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SellerInventoryDeleter {
    private final InventoryRepository inventoryRepository;

    public void deleteFromSellerInventoryProduct(UUID productId, UUID userId) {
        inventoryRepository.deleteFromSellerInventory(productId, userId);
    }
}
