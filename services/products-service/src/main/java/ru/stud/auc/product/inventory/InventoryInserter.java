package ru.stud.auc.product.inventory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.flowdata.product.cart.CartEntity;
import ru.stud.auc.flowdata.product.cart.CartEntityId;
import ru.stud.auc.flowdata.product.cart.CartsRepository;
import ru.stud.auc.flowdata.product.inventory.InventoryEntity;
import ru.stud.auc.flowdata.product.inventory.InventoryEntityId;
import ru.stud.auc.flowdata.product.inventory.InventoryRepository;

import javax.transaction.Transactional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InventoryInserter {
    private final InventoryRepository inventoryRepository;

    @Transactional
    public void addInventory(UUID productId, UUID sellerId) {
        InventoryEntityId inventoryEntityId = new InventoryEntityId();
        inventoryEntityId.setProductId(productId);
        inventoryEntityId.setSellerId(sellerId);
        InventoryEntity inventory = new InventoryEntity();
        inventory.setId(inventoryEntityId);
        inventory.setAmount(1);
        inventoryRepository.persist(inventory);

    }
}
