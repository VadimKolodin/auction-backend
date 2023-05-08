package ru.stud.auc.product.inventory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stud.auc.auth.util.AuthenticationUtils;
import ru.stud.auc.exception.NotFoundException;
import ru.stud.auc.flowdata.product.inventory.model.InventoryProductView;
import ru.stud.auc.product.ProductsGetter;
import ru.stud.auc.product.inventory.model.InventoryAmountDto;
import ru.stud.auc.product.model.SellerProductsDto;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SellerInventoryService {

    private final ProductsGetter productsGetter;

    private final InventoryMapper inventoryMapper;

    private final InventoryGetter inventoryGetter;
    
    private final InventoryInserter inventoryInserter;

    private final InventoryUpdater inventoryUpdater;

    private final InventoryDeleter inventoryDeleter;

    public List<SellerProductsDto> getSellerInventory(UUID sellerId) {
        return inventoryGetter.getSellerInventory(sellerId).stream().map(inventoryMapper::toInventoryDto).toList();
    }

 

    public void addToInventory(UUID productId) {
        productsGetter.checkExistsAndNotDeleted(productId);
        UUID userId = AuthenticationUtils.getUserId();
        inventoryInserter.addInventory(productId, userId);
    }

    public void setInventoryAmount(UUID productId, int amount) {
        productsGetter.checkExistsAndNotDeleted(productId);
        try {
            InventoryAmountDto inventoryAmountDto = inventoryGetter.getSellerInventory(AuthenticationUtils.getUserId(), productId);
            if (amount == 0){
                deleteInventoryProduct(productId);
            } else {
                inventoryUpdater.setAmount(productId, AuthenticationUtils.getUserId(), amount);
            }
        } catch (NotFoundException e) {
            addToInventory(productId);
        }

    }
    
    public void deleteInventory() {
        inventoryDeleter.deleteAllInventory(AuthenticationUtils.getUserId());
    }

    public void deleteInventoryProduct(UUID productId){
        productsGetter.checkExistsAndNotDeleted(productId);
        inventoryDeleter.deleteInventoryProduct(productId, AuthenticationUtils.getUserId());
    }

}
