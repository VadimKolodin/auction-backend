package ru.stud.auc.product.sellerInventory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.flowdata.product.inventory.InventoryRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SellerInventoryUpdater {
    private final InventoryRepository inventoryRepository;


    public void setAmount(UUID productId, UUID sellerId, int amount) {
        inventoryRepository.setAmount(productId, sellerId, amount);
    }
}
