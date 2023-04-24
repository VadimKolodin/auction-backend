package ru.stud.auc.product.inventory;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.flowdata.product.inventory.InventoryRepository;
import ru.stud.auc.flowdata.product.inventory.model.InventoryProductView;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InventoryGetter {
    private final InventoryRepository inventoryRepository;

    public List<InventoryProductView> getSellerInventory(UUID sellerId) {
        return inventoryRepository.findSellerInventory(sellerId);
    }
}
