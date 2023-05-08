package ru.stud.auc.product.inventory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.flowdata.product.inventory.InventoryRepository;

import javax.transaction.Transactional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InventoryUpdater {

    private final InventoryRepository inventoryRepository;

    @Transactional
    public void setAmount(UUID productId, UUID sellerId, int amount) {
        int result = inventoryRepository.setAmount(productId, sellerId, amount);

    }
}
