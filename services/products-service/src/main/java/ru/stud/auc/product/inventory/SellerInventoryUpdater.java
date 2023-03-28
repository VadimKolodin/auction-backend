package ru.stud.auc.product.inventory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.flowdata.product.inventory.InventoryRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SellerInventoryUpdater {

    private final InventoryRepository inventoryRepository;

    public void updateAmount(UUID productId, UUID sellerId, int amount) {
        inventoryRepository.updateAmount(productId, sellerId, amount);
    }
}
