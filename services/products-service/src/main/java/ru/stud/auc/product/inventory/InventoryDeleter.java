package ru.stud.auc.product.inventory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.flowdata.product.inventory.InventoryRepository;

import javax.transaction.Transactional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InventoryDeleter {
    private final InventoryRepository inventoryRepository;
    @Transactional
    public void deleteInventoryProduct(UUID productId, UUID userId) {
        inventoryRepository.deleteInventoryProduct(productId, userId);
    }
    @Transactional
    public void deleteAllInventory(UUID userId) {
        inventoryRepository.deleteAllInventory(userId);
    }
}
