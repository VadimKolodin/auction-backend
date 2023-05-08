package ru.stud.auc.product.inventory;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.consts.StringConsts;
import ru.stud.auc.exception.NotFoundException;
import ru.stud.auc.flowdata.product.inventory.InventoryEntityId;
import ru.stud.auc.flowdata.product.inventory.InventoryRepository;
import ru.stud.auc.flowdata.product.inventory.model.InventoryProductView;
import ru.stud.auc.product.inventory.model.InventoryAmountDto;
import ru.stud.auc.product.model.SellerProductsDto;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InventoryGetter {
    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    public List<InventoryProductView> getSellerInventory(UUID sellerId) {
        return inventoryRepository.findSellerInventory(sellerId);
    }

    public InventoryAmountDto getSellerInventory(UUID sellerId, UUID productId) {

        return inventoryRepository.findById(InventoryEntityId.of(productId, sellerId))
                .map(inventoryMapper::toDto)
                .orElseThrow(() -> new NotFoundException(StringConsts.Inventory.NOT_FOUND));
    }
}
