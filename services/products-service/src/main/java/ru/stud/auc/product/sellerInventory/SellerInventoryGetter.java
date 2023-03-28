package ru.stud.auc.product.sellerInventory;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.flowdata.product.inventory.InventoryRepository;
import ru.stud.auc.flowdata.product.inventory.model.SellerInventoryProductView;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SellerInventoryGetter {
    private final InventoryRepository inventoryRepository;

    public List<SellerInventoryProductView> getSellerInventory(UUID sellerId) {
        return inventoryRepository.getSellerInventory(sellerId);
    }
}
